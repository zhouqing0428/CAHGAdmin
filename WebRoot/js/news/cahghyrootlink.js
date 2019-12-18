$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahghyperlink/rootList',
        datatype: "json",
        colModel: [			
			{ label: 'linkId', name: 'linkId', width: 50, key: true,hidden:true },
			{ label: '名称', name: 'name', width: 80 }, 			
			{ label: '链接地址', name: 'url', width: 80 }, 			
			{ label: '备注', name: 'remark', width: 80 }, 			
			{ label: '类别', name: 'type', width: 80, formatter:function(value, options, row){
				if(value == '1'){
					return '<span class="label label-primary">部门网站</span>';  
				}
				if(value =='2'){
					return '<span class="label label-danger">分关网站</span>';  
				}
				if(value == '3'){
					return '<span class="label label-info">应用系统</span>';  
				}
				if(value =='4'){
					return '<span class="label label-warning">相关链接</span>';  
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
		cahgHyperlink: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgHyperlink = {type:1};
		},
		update: function (event) {
			var linkId = getSelectedRow();
			if(linkId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(linkId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgHyperlink.linkId == null ? "../cahghyperlink/save" : "../cahghyperlink/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgHyperlink),
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
			var linkIds = getSelectedRows();
			if(linkIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahghyperlink/delete",
				    data: JSON.stringify(linkIds),
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
		getInfo: function(linkId){
			$.get("../cahghyperlink/info/"+linkId, function(r){
                vm.cahgHyperlink = r.cahgHyperlink;
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