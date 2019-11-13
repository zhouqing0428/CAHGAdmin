$(function () {
	/* $("#jqGrid").jqGrid({
	        url: '../ipLimit/list',
	        datatype: "json",
	        colModel: [			
				{ label: 'ipLimitId', name: 'ipLimitId', width: 50, key: true,hidden : true },
				{ label: '开始', name: 'ipStart', width: 80 },
				{ label: '结束', name: 'ipEnd', width: 80 },
				{ label: '显示状态', name: 'status', width: 80, formatter:function(value, options, row){
					if(value == '0'){
						return '<span class="label label-primary">开启</span>';  
					}
					if(value =='1'){
						return '<span class="label label-danger">关闭</span>';  
					}
				}   }
	        ],
			viewrecords: true,
	        height: 536,
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
//	        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
	        }
	    });*/
	   vm.update();
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: false,
		title: null,
		ipLimitEntity: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		saveOrUpdate: function (event) {
			var url = "../ipLimit/update";
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(vm.ipLimitEntity),
				    success: function(r){
				    	if(r.code === 0){
							/*alert('操作成功', function(index){
								vm.reload();
							});*/
				    		alert("操作成功");
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
		getInfo: function(ipLimitId){
			$.get("../ipLimit/info/"+ipLimitId, function(r){
                vm.ipLimitEntity = r.ipLimitEntity;
            });
		},
		update: function (event) {
            vm.getInfo(1)  //默认为查询id为1的
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