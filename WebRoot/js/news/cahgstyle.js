$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgstyle/list',
        datatype: "json",
        colModel: [			
			{ label: 'styleId', name: 'styleId', width: 50, key: true,hidden:true },
			{ label: '风采图片标题', name: 'styleTitle', width: 120 }, 			
			{ label: '作者', name: 'author', width: 50 }, 			
			{ label: '发布时间', name: 'createDate', width: 80,formatter:'date',
				formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d'} },
			{ label: '发布科室', name: 'deptName', width: 50 },
			{ label: '最后修改时间', name: 'lastUpdateDate', width: 80,hidden:true }, 
			{ label: '图片分类', name: 'styleCategory', width: 40,formatter:function(value, options, row){
				if(value == '1'){
					return '活动剪影';  
				}
				if(value =='2'){
					return '摄影作品';  
				}
			}  },
			{ label: '状态', name: 'styleStatus', width: 40,formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}  }, 			
			{ label: '风采图片', name: 'styleUrl', width: 80,formatter:function(value, options, row){
					if(value != ''){
						return '<img class="style-responsive" src="/file/upStyle/style/'+value+'" height="50" width="100">';
					}else{
						return '';
					}
				} 
			}
        ],
		viewrecords: true,
        height: 530,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		tips : false,
		q : {
			title : null,
			author : null
		},
		title: null,
		deptList:[],
		cahgStyle: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips = false;
			vm.title = "新增";
			vm.cahgStyle = {styleStatus:0};
			$("#styleUrl").val("");
			$("#styleUrlName").val("");
			//获取部门信息
			this.getDeptList();
		},
		update: function (event) {
			var styleId = getSelectedRow();
			if(styleId == null){
				return ;
			}
			$("#selectedDept").attr("selected","selected");
			vm.showList = false;
            vm.title = "修改";
            vm.tips=false;
            $("#styleUrl").val("");
			$("#styleUrlName").val("");
            this.getDeptList();
            vm.getInfo(styleId)
		},
		saveOrUpdate: function (event) {
			
			var title = $("#styleTitle").val();
			if(title == null || title == ""){
				alert("请填写风采标题");
				return;
			}
			var category = $("#styleCategory").val();
			if(category == null || category == ""){
				alert("请选择图片分类");
				return;
			}
			
			
			if(!$("#styleUrlName").val()){
				alert("请先上传图片");
				return;
			}
			
			$("#selectedDept").removeAttr("selected");
			
		    vm.cahgStyle.deptId=$("#deptId").val();
		    vm.cahgStyle.styleUrl=$("#styleUrlName").val();
		    vm.cahgStyle.styleRank=$("#styleRank").val();
		    vm.cahgStyle.createDate=$("#createDate").val();//时间
		    vm.cahgStyle.styleCategory=$("#styleCategory").val();//分类
			var url = vm.cahgStyle.styleId == null ? "../cahgstyle/save" : "../cahgstyle/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgStyle),
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
			var styleIds = getSelectedRows();
			if(styleIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgstyle/delete",
				    data: JSON.stringify(styleIds),
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
		
		//显示
		show:function(event){
			var styleIds = getSelectedRows();
			if(styleIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgstyle/show",
			    data: JSON.stringify(styleIds),
			    success: function(r){
					if(r.code == 0){
						alert('操作成功', function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		//不显示
		unshow:function(event){
			var styleIds = getSelectedRows();
			if(styleIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgstyle/unshow",
			    data: JSON.stringify(styleIds),
			    success: function(r){
					if(r.code == 0){
						alert('操作成功', function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
	
      stick	:function(){
    	  var styleId = getSelectedRow();
			if(styleId == null){
				return ;
			}
			$.get("../cahgstyle/stick/" + styleId, function(r) {
				if(r.code == 0){
					alert('操作成功', function(index){
						$("#jqGrid").trigger("reloadGrid");
					});
				}else{
					alert(r.msg);
				}
			});
      },
	  upStyle: function(){
		  if($("#styleUrl").val()==null||$("#styleUrl").val()==''){
				alert("选择图片才能上传");
				return ;
			}
		   $.ajaxFileUpload({
					type: "POST",
					secureuri: false, //一般设置为false
					fileElementId: 'styleUrl', //文件上传的ID
				    url: "../cahgstyle/upStyleUrl",
				    //timeout : 50000, //超时时间设置，单位毫秒
				    async: false,
				    //maxFileSize:1024*1024, //大小限制1M
			        //sizeErrorStr:"上传文件不能大于1M", 
				    success: function(name){
					 	if(name!="err"){
					 		vm.tips = true;
							var path="../upStyle/style/"+name;
							$("#styleUrlName").val(name);
				            $("#showStyle").attr('src',path); 
						}else{
							alert("上传图片失败");
						} 
					},
		   			error: function (data, status, e)//服务器响应失败处理函数
	               {
	                   alert('上传图片失败,服务器响应失败(可能是文件过大导致)');
	                }
			}); 
		},
		
		getInfo: function(styleId){
			$.get("../cahgstyle/info/"+styleId, function(r){
                vm.cahgStyle = r.cahgStyle;
			    var path="../upStyle/style/"+vm.cahgStyle.styleUrl;
				$("#styleUrlName").val(vm.cahgStyle.styleUrl);
			    $("#showStyle").attr('src',path);
            });
		},
		//部门列表
		getDeptList: function(){
//			$.get("../sysdept/selectList", function(r){
//				vm.deptList = r.list;
//			});
			$.get("../cahgstyle/selectList", function(r){
				vm.deptList = r.list;
			});
			
		},
		reload: function (event) {
			$("#selectedDept").removeAttr("selected");
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'styleTitle' : vm.q.title,
					'author' : vm.q.author
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});