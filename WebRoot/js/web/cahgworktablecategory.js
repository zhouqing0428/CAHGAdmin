$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgworktablecategory/list',
        datatype: "json",
        colModel: [			
			{ label: 'workTableCategoryId', name: 'workTableCategoryId', width: 50, key: true,hidden:true },
			{ label: '名称', name: 'name', width: 80 }, 			
			{ label: '备注', name: 'remark', width: 80 },
			{ label: '排序号', name: 'rank', width: 50 }, 
			{ label: '创建时间', name: 'createTime', width: 50 }			
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
		cahgWorkTableCategory: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgWorkTableCategory = {};
		},
		update: function (event) {
			var workTableCategoryId = getSelectedRow();
			if(workTableCategoryId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(workTableCategoryId)
		},
		saveOrUpdate: function (event) {
			vm.cahgWorkTableCategory.rank=$("#rank").val();
			var url = vm.cahgWorkTableCategory.workTableCategoryId == null ? "../cahgworktablecategory/save" : "../cahgworktablecategory/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgWorkTableCategory),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var workTableCategoryIds = getSelectedRows();
			if(workTableCategoryIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgworktablecategory/delete",
				    data: JSON.stringify(workTableCategoryIds),
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
			});
		},
		getInfo: function(workTableCategoryId){
			$.get("../cahgworktablecategory/info/"+workTableCategoryId, function(r){
                vm.cahgWorkTableCategory = r.cahgWorkTableCategory;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});