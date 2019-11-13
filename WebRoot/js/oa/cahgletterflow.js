$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgletterflow/list',
        datatype: "json",
        colModel: [			
			{ label: 'letterFlowId', name: 'letterFlowId', width: 50, key: true,hidden: true },
			{ label: '信封标题', name: 'letterTitle', width: 80 },
			{ label: '内容详细', name: 'letterId', width: 80 , formatter: function(value, options, row){
				return  '<button  class="btn btn-primary btn-xs" onclick="detail(\''+value+'\')" >查看</button>';
			} }, 	
			{ label: '转交主任', name: 'userName', width: 80 },
			{ label: '处理时间', name: 'disposeTime', width: 80 }, 			
			{ label: '要求', name: 'require', width: 80 }, 			
	//		{ label: '', name: 'createUserId', width: 80 }, 			
			{ label: '转交时间', name: 'createTime', width: 80 }, 			
			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">未处理</span>';  
				}
				if(value =='1'){
					return '<span class="label label-success">已处理</span>';  
				}
			}   }, 
		//	{ label: '信封id', name: 'letterId', width: 80,hidden:true }			
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

function detail(letterId){
	vm.showList = false;
    vm.getLetterInfo(letterId)
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		cahgLetter: {},
		cahgLetterFlow: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgLetterFlow = {};
		},
		update: function (event) {
			var letterFlowId = getSelectedRow();
			if(letterFlowId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(letterFlowId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgLetterFlow.letterFlowId == null ? "../cahgletterflow/save" : "../cahgletterflow/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgLetterFlow),
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
			var letterFlowIds = getSelectedRows();
			if(letterFlowIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgletterflow/delete",
				    data: JSON.stringify(letterFlowIds),
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
		hadDeal: function(){
			var letterFlowIds = getSelectedRows();
			if(letterFlowIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgletterflow/hadDeal",
			    data: JSON.stringify(letterFlowIds),
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
		getInfo: function(letterFlowId){
			$.get("../cahgletterflow/info/"+letterFlowId, function(r){
                vm.cahgLetterFlow = r.cahgLetterFlow;
            });
		},
		getLetterInfo: function(letterId){
			$.get("../cahgletter/info/"+letterId, function(r){
				$("#content").html(r.cahgLetter.content);
                vm.cahgLetter = r.cahgLetter;
             
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