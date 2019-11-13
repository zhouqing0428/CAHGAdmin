$(function () {
/*    $("#jqGrid").jqGrid({
        url: '../cahgletterattentive/list',
        datatype: "json",
        colModel: [			
			{ label: 'letterAttentiveId', name: 'letterAttentiveId', width: 50, key: true },
			{ label: '', name: 'content', width: 80 }			
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
    });*/
	
	vm.getInfo(1);
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		cahgLetterAttentive: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgLetterAttentive = {};
		},
		update: function (event) {
			var letterAttentiveId = getSelectedRow();
			if(letterAttentiveId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "主任信箱注意事项";
            
            vm.getInfo(letterAttentiveId)
		},
		saveOrUpdate: function (event) {
			var content=UE.getEditor('editor').getContent();  //内容
			vm.cahgLetterAttentive.content=content;
			var url = vm.cahgLetterAttentive.letterAttentiveId == null ? "../cahgletterattentive/save" : "../cahgletterattentive/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgLetterAttentive),
			    success: function(r){
			    	if(r.code === 0){
						/*alert('操作成功', function(index){
							vm.reload();
							
						});*/
			    		alert("操作成功")
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var letterAttentiveIds = getSelectedRows();
			if(letterAttentiveIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgletterattentive/delete",
				    data: JSON.stringify(letterAttentiveIds),
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
		getInfo: function(letterAttentiveId){
			$.get("../cahgletterattentive/info/"+letterAttentiveId, function(r){
                vm.cahgLetterAttentive = r.cahgLetterAttentive;
                ue.ready(function(){  //编辑器加载好后执行
                    UE.getEditor('editor').setContent(vm.cahgLetterAttentive.content);  //回显编辑内容
                }) ;
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