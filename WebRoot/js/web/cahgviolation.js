$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgviolation/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 200 }, 
			{ label: '文件名', name: 'fileOldName', width: 60 }, 
			{ label: '分类', name: 'violationCategory', width: 40,formatter:function(value, options, row){
				if(value == '1'){
					return '违纪曝光';  
				}
				if(value =='2'){
					return '内务检查通报';  
				}
			}  },
			{ label: '创建人', name: 'userName', width: 30 },
			{ label: '创建时间', name: 'createDate', width: 55 }
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
		q:{
			title:""
		},
		info: {}
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
			vm.info = {};
			UE.getEditor('editor').setContent('');  //编辑内容为空
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.tips =false;
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var title = $("#noticeTitle").val();
			if(title == null || title == ""){
				alert("请填写标题");
				return;
			}
			
			var content = UE.getEditor('editor').getContent();
			if(content == null || content == ""){
				alert("请填写内容");
				return;
			}
			
			var cate = $("#violationCategory").val();
			if(cate == null || cate == ""){
				alert("请填写分类");
				return;
			}
			
			var url = vm.info.id == null ? "../cahgviolation/save" : "../cahgviolation/update";
		    var content=UE.getEditor('editor').getContent();  //新闻内容
	
		    vm.info.content=content;
			vm.info.fileName=$("#fileName").val();
			vm.info.fileOldName=$("#fileOldName").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.info),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgviolation/delete",
				    data: JSON.stringify(ids),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的附件吗？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgviolation/delFile",
				    data: JSON.stringify(ids),
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
			    url: "../cahgviolation/upFile",
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
		getInfo: function(id){
			$.get("../cahgviolation/info/"+id, function(r){
				UE.getEditor('editor').setContent(r.info.content);  //回显编辑内容
                vm.info = r.info;
                $("#fileName").val(vm.info.fileName);
    			$("#fileOldName").val(vm.info.fileOldName);
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData : {
					'title' : $("#title").val()
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});