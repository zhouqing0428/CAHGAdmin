$(function () {
	 //vm.getInfo(1) //默认长第一条数据
    $("#jqGrid").jqGrid({
        url: '../cahgintro/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'introId', width: 50, key: true },
			{ label: '备注', name: 'remark', width: 80 }			
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
		cahgIntro: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		/*add: function(){
		   vm.showList = false;
			vm.title = "新增";
			vm.cahgIntro = {};
		},*/
		update: function (event) {
			var introId = getSelectedRow();
			if(introId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(introId)
		},
		saveOrUpdate: function (event) {
			var content=UE.getEditor('editor').getContent();  //内容
			vm.cahgIntro.content=content;
			var url = vm.cahgIntro.introId == null ? "../cahgintro/save" : "../cahgintro/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgIntro),
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
			var introIds = getSelectedRows();
			if(introIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgintro/delete",
				    data: JSON.stringify(introIds),
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
		getInfo: function(introId){
			$.get("../cahgintro/info/"+introId, function(r){
                vm.cahgIntro = r.cahgIntro;
                ue.ready(function(){  //编辑器加载好后执行
                    UE.getEditor('editor').setContent(vm.cahgIntro.content);  //回显编辑内容
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