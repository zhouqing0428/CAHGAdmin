$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgstylecategory/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '风采分类名称', name: 'cateName', width: 20 }, 			
			{ label: '排序', name: 'orderNum', width: 50 }		
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
		cahgStyleCategory: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips = false;
			vm.title = "新增";
			vm.cahgStyleCategory = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.tips=false;
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			
			var cateName = $("#cateName").val();
			if(cateName == null || cateName == ""){
				alert("请填写风采分类名称");
				return;
			}
			
		    vm.cahgStyleCategory.cateName=$("#cateName").val();
		    vm.cahgStyleCategory.orderNum=$("#orderNum").val();
			var url = vm.cahgStyleCategory.id == null ? "../cahgstylecategory/save" : "../cahgstylecategory/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgStyleCategory),
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
				    url: "../cahgstylecategory/delete",
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
		getInfo: function(id){
			$.get("../cahgstylecategory/info/"+id, function(r){
                vm.cahgStyleCategory = r.cahgStyleCategory;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'title' : vm.q.title
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});