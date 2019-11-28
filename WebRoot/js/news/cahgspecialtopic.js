$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgspecialtopic/list',
        datatype: "json",
        colModel: [			
			{ label: 'specialTopicId', name: 'specialTopicId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 140 }, 			
			{ label: '作者', name: 'author', width: 25 }, 			
			{ label: '发布时间', name: 'createDate', width: 55 },
			{ label: '专题类别', name: 'categoryName', width: 80 },
			{ label: '文件名', name: 'fileOldName', width: 60 }, 
			{ label: '状态', name: 'status', width: 25,formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}  }, 			
			{ label: '科室', name: 'deptName', width: 80 }, 		
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 30, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    vm.getCategoryList();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		tips:false,
		title: null,
		deptList:[],
		categoryList:[],
		q:{
			title:""
		},
		cahgSpecialTopic: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.tips =false;
			vm.showList = false;
			vm.title = "新增";
			vm.cahgSpecialTopic = {status:0};
			UE.getEditor('editor').setContent('');  //编辑内容为空
			//获取部门信息
			this.getDeptList();
			//专题类别
			this.getCategoryList();
		},
		update: function (event) {
			var specialTopicId = getSelectedRow();
			if(specialTopicId == null){
				return ;
			}
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.tips =false;
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(specialTopicId);
        	//获取部门信息
			this.getDeptList();
			//专题类别
			this.getCategoryList();
		},
		saveOrUpdate: function (event) {
			var title = $("#topTitle").val();
			if(title == null || title == ""){
				alert("请填写标题");
				return;
			}
			
			var content = UE.getEditor('editor').getContent();
			if(content == null || content == ""){
				alert("请填写内容");
				return;
			}
			
		    var specialTopicCategoryId=$("#specialTopicCategoryId").val();
		    if(specialTopicCategoryId==null || specialTopicCategoryId==""){
		    	alert("请选择专题类别");
		    	return ;
		    }
		    
		    var deptId=$("#deptId").val();
		    if(deptId==null || deptId==""){
		    	alert("请选择科室");
		    	return ;
		    }
			var url = vm.cahgSpecialTopic.specialTopicId == null ? "../cahgspecialtopic/save" : "../cahgspecialtopic/update";
		    var content=UE.getEditor('editor').getContent();  //新闻内容
	
		    vm.cahgSpecialTopic.content=content;
			vm.cahgSpecialTopic.specialTopicCategoryId=specialTopicCategoryId;
			vm.cahgSpecialTopic.deptId=$("#deptId").val();
			vm.cahgSpecialTopic.fileName=$("#fileName").val();
			vm.cahgSpecialTopic.fileOldName=$("#fileOldName").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgSpecialTopic),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				},
				complete : function(XMLHttpRequest, textStatus) { 
					if(textStatus!="success"){
						location.reload(true);
					};
				}
			});
		},
		del: function (event) {
			var specialTopicIds = getSelectedRows();
			if(specialTopicIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgspecialtopic/delete",
				    data: JSON.stringify(specialTopicIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					},
					complete : function(XMLHttpRequest, textStatus) { 
						if(textStatus!="success"){
							location.reload(true);
						};
					}
				});
			});
		},
		delFile:function(){
			var specialTopicIds = getSelectedRows();
			if(specialTopicIds == null){
				return ;
			}
			confirm('确定要删除选中的附件吗？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgspecialtopic/delFile",
				    data: JSON.stringify(specialTopicIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					},
					complete : function(XMLHttpRequest, textStatus) { 
						if(textStatus!="success"){
							location.reload(true);
						};
					}
				});
			});
		},
		upfile: function(){
			if($("#file").val()==null||$("#file").val()==''){
				alert("选择文件才能上传");
				return ;
			}
			var file=$("#file").val();
		    $.ajaxFileUpload({
				type: "POST",
				fileElementId: 'file', //文件上传的ID
			    url: "../cahgspecialtopic/upFile",
			    async: false,
			    success: function(name){
				 	if(name!="err"){
				 		$("#fileName").val(name);
				 		$("#fileOldName").val(vm.getFileName(file));  //附件原名
				 		vm.tips = true;
						alert("上传成功");
					}else{
						vm.tips = false;
						alert("上传失败");
					} 
				}
		   }); 
		},
		getFileName: function(o){
			var pos=o.lastIndexOf("\\");
			return o.substring(pos+1);  
		},
		getInfo: function(specialTopicId){
			$.get("../cahgspecialtopic/info/"+specialTopicId, function(r){
				UE.getEditor('editor').setContent(r.cahgSpecialTopic.content);  //回显编辑内容
                vm.cahgSpecialTopic = r.cahgSpecialTopic;
                $("#fileName").val(vm.cahgSpecialTopic.fileName);
    			$("#fileOldName").val(vm.cahgSpecialTopic.fileOldName);
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		//专题类别列表
		getCategoryList: function(){
			$.get("../cahgspecialtopiccategory/selectList", function(r){
				vm.categoryList = r.list;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData : {
					'specialTopicCategoryId' : $("#category_id").val(),
					'title' : $("#title").val()
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});