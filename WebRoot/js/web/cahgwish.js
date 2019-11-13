$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgwish/list',
        datatype: "json",
        colModel: [			
			{ label: 'wishId', name: 'wishId', width: 50, key: true,hidden:true },
			{ label: '问候语', name: 'wishCenter', width: 80 }, 			
			{ label: '时间', name: 'wishDate', width: 80,formatter:'date',
				formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d'} }, 			
			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">开启</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">关闭</span>';  
				}
			}  }			
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
		cahgWish: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgWish = {status:0};
		},
		update: function (event) {
			var wishId = getSelectedRow();
			if(wishId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(wishId)
		},
		saveOrUpdate: function (event) {
			var wishDate=$("#wishDate").val();
			if(wishDate==null||wishDate==""){
				alert("时间必须设置");
				return ;
			}
			vm.cahgWish.wishDate=wishDate;
			var url = vm.cahgWish.wishId == null ? "../cahgwish/save" : "../cahgwish/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgWish),
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
			var wishIds = getSelectedRows();
			if(wishIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgwish/delete",
				    data: JSON.stringify(wishIds),
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
		getInfo: function(wishId){
			$.get("../cahgwish/info/"+wishId, function(r){
                vm.cahgWish = r.cahgWish;
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