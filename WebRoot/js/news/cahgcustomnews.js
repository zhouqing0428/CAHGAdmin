$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgcustomnews/list',
        datatype: "json",
        colModel: [			
			{ label: 'customNewsId', name: 'customNewsId', width: 40, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 120 }, 			
//			{ label: '添加人', name: 'createUserId', width: 80 }, 			
			{ label: '发布时间', name: 'createDate', width: 50 }, 	
			{ label: '作者', name: 'author', width: 50 },
			{ label: '发布科室', name: 'deptName', width: 50 },
			{ label: '状态', name: 'status', width: 40, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}  },
			
			{ label: '排序号', name: 'rank', width: 40 },
			{ label: '部门id', name: 'deptId', width: 40,hidden:true }			
        ],
		viewrecords: true,
        height: 385,
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
		title: null,
		q : {
			title : null,
			author : null
		},
		deptList:[],
		cahgCustomNews: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgCustomNews = {status:0};
			UE.getEditor('editor').setContent('');  //编辑内容为空
			//获取部门信息
			this.getDeptList();
		},
		update: function (event) {
			var customNewsId = getSelectedRow();
			if(customNewsId == null){
				return ;
			}
			$("#selectedDept").attr("selected","selected");
			vm.showList = false;
            vm.title = "修改";
            //获取部门信息
			this.getDeptList();
            vm.getInfo(customNewsId)
		},
		saveOrUpdate: function (event) {
		   $("#selectedDept").removeAttr("selected");
		   var content=UE.getEditor('editor').getContent();  //新闻内容
		   vm.cahgCustomNews.content=content;
		   vm.cahgCustomNews.deptId=$("#deptId").val();
		   vm.cahgCustomNews.rank=$("#rank").val();
		   var url = vm.cahgCustomNews.customNewsId == null ? "../cahgcustomnews/save" : "../cahgcustomnews/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgCustomNews),
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
			var customNewsIds = getSelectedRows();
			if(customNewsIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgcustomnews/delete",
				    data: JSON.stringify(customNewsIds),
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
		getInfo: function(customNewsId){
			$.get("../cahgcustomnews/info/"+customNewsId, function(r){
				UE.getEditor('editor').setContent(r.cahgCustomNews.content)  //回显编辑内容
                vm.cahgCustomNews = r.cahgCustomNews;
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		reload: function (event) {
			$("#selectedDept").removeAttr("selected");
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'title' : vm.q.title,
					'author' : vm.q.author
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});