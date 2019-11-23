$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgfloatnews/list',
        datatype: "json",
        colModel: [			
			{ label: 'floatNewId', name: 'floatNewId', width: 50, key: true,hidden:true },
			{ label: '弹窗标题', name: 'floatNewTitle', width: 120 }, 			
			/*{ label: '图标新闻内容', name: 'floatNewContent', width: 80 }, */			
			/*{ label: '创建人', name: 'createUserId', width: 80 }, 			
			{ label: '最后修改人', name: 'lastUpdateUserId', width: 80 },*/ 			
			{ label: '作者', name: 'author', width: 50 }, 			
			/*{ label: '科室ID', name: 'deptId', width: 80 }, 	*/	
			/*{ label: '发布时间', name: 'createDate', width: 60 }, */
			{ label: '发布时间', name: 'createDate', width: 80,formatter:'date',
				formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d'} },
			{ label: '发布科室', name: 'deptName', width: 50 },
			{ label: '最后修改时间', name: 'lastUpdateDate', width: 80,hidden:true }, 
			{ label: '状态', name: 'floatNewStatus', width: 40,formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}  }, 			
		/*	{ label: '置顶状态0:表示置顶', name: 'floatNewsStick', width: 80 }, */			
			{ label: '排序号', name: 'floatNewsRank', width: 40,formatter:function(value, options, row){
				if(value != null && value != ''&&value!='default'){
					return value;  
				}
				else{
					return "";
				}
			}  },
			{ label: '弹窗图片', name: 'floatUrl', width: 80,formatter:function(value, options, row){
				return '<img class="float-responsive" src="/file/upFloat/floatNews/'+value+'">';} }
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
		cahgFloatNews: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips = false;
			vm.title = "新增";
			vm.cahgFloatNews = {floatNewStatus:0};
			UE.getEditor('editor').setContent('');  //编辑内容为空
			$("#floatUrl").val("");
			$("#floatUrlName").val("");
			//获取部门信息
			this.getDeptList();
		},
		update: function (event) {
			var floatNewId = getSelectedRow();
			if(floatNewId == null){
				return ;
			}
			$("#selectedDept").attr("selected","selected");
			vm.showList = false;
            vm.title = "修改";
            vm.tips=false;
            $("#floatUrl").val("");
			$("#floatUrlName").val("");
            this.getDeptList();
            vm.getInfo(floatNewId)
		},
		saveOrUpdate: function (event) {
			
			$("#selectedDept").removeAttr("selected");
		    var content=UE.getEditor('editor').getContent();  //新闻内容
		    vm.cahgFloatNews.floatNewContent=content;
		    vm.cahgFloatNews.deptId=$("#deptId").val();
		    vm.cahgFloatNews.floatUrl=$("#floatUrlName").val();
		    vm.cahgFloatNews.floatNewsRank=$("#floatNewsRank").val();
		    vm.cahgFloatNews.createDate=$("#createDate").val();//时间
			var url = vm.cahgFloatNews.floatNewId == null ? "../cahgfloatnews/save" : "../cahgfloatnews/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgFloatNews),
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
			var floatNewIds = getSelectedRows();
			if(floatNewIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgfloatnews/delete",
				    data: JSON.stringify(floatNewIds),
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
	
      stick	:function(){
    	  var floatNewId = getSelectedRow();
			if(floatNewId == null){
				return ;
			}
			$.get("../cahgfloatnews/stick/" + floatNewId, function(r) {
				if(r.code == 0){
					alert('操作成功', function(index){
						$("#jqGrid").trigger("reloadGrid");
					});
				}else{
					alert(r.msg);
				}
			});
      },
	  upFloat: function(){
		  if($("#floatUrl").val()==null||$("#floatUrl").val()==''){
				alert("选择图片才能上传");
				return ;
			}
		   $.ajaxFileUpload({
					type: "POST",
					secureuri: false, //一般设置为false
					fileElementId: 'floatUrl', //文件上传的ID
				    url: "../cahgfloatnews/upFloatUrl",
				    //timeout : 50000, //超时时间设置，单位毫秒
				    async: false,
				    //maxFileSize:1024*1024, //大小限制1M
			        //sizeErrorStr:"上传文件不能大于1M", 
				    success: function(name){
					 	if(name!="err"){
					 		vm.tips = true;
							var path="../upFloat/floatNews/"+name;
							$("#floatUrlName").val(name);
				            $("#showFloat").attr('src',path); 
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
		
		getInfo: function(floatNewId){
			$.get("../cahgfloatnews/info/"+floatNewId, function(r){
			    UE.getEditor('editor').setContent(r.cahgFloatNews.floatNewContent);  //回显编辑内容 
                vm.cahgFloatNews = r.cahgFloatNews;
			    var path="../upFloat/floatNews/"+vm.cahgFloatNews.floatUrl;
				$("#floatUrlName").val(vm.cahgFloatNews.floatUrl);
			    $("#showFloat").attr('src',path);
            });
		},
		//部门列表
		getDeptList: function(){
//			$.get("../sysdept/selectList", function(r){
//				vm.deptList = r.list;
//			});
			$.get("../cahgfloatnews/selectList", function(r){
				vm.deptList = r.list;
			});
			
		},
		reload: function (event) {
			$("#selectedDept").removeAttr("selected");
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'floatNewTitle' : vm.q.title,
					'author' : vm.q.author
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});