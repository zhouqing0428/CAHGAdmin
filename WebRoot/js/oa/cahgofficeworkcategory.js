$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgofficeworkcategory/list',
        datatype: "json",
        colModel: [			
			{ label: 'officeWorkCategoryId', name: 'officeWorkCategoryId', width: 50, key: true,hidden:true },
			{ label: '名称', name: 'name', width: 80 }, 			
			{ label: '备注', name: 'remark', width: 80 }			
		/*	{ label: '', name: 'createDate', width: 80 }, 			
			{ label: '', name: 'createUserId', width: 80 }		*/	
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
		cahgOfficeWorkCategory: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgOfficeWorkCategory = {};
		},
		update: function (event) {
			var officeWorkCategoryId = getSelectedRow();
			if(officeWorkCategoryId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(officeWorkCategoryId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgOfficeWorkCategory.officeWorkCategoryId == null ? "../cahgofficeworkcategory/save" : "../cahgofficeworkcategory/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgOfficeWorkCategory),
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
			var officeWorkCategoryIds = getSelectedRows();
			if(officeWorkCategoryIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgofficeworkcategory/delete",
				    data: JSON.stringify(officeWorkCategoryIds),
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
		getInfo: function(officeWorkCategoryId){
			$.get("../cahgofficeworkcategory/info/"+officeWorkCategoryId, function(r){
                vm.cahgOfficeWorkCategory = r.cahgOfficeWorkCategory;
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