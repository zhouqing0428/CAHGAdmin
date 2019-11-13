$(function () {
   /* $("#jqGrid").jqGrid({
        url: '../cahgdutyinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'dutyInfoId', name: 'dutyInfoId', width: 50, key: true },
			{ label: '标题', name: 'title', width: 80 }, 			
			{ label: '内容', name: 'content', width: 80 }, 			
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
    });*/
	
    vm.update();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: false,
		title: null,
		cahgDutyInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgDutyInfo = {};
		},
		update: function (event) {
			/*var dutyInfoId = getSelectedRow();
			if(dutyInfoId == null){
				return ;
			}*/
			vm.showList = false;
            vm.title = "值班通知信息编辑";
            
            //vm.getInfo(dutyInfoId)
            vm.getInfo(1)  //默认为查询id为1的
		},
		saveOrUpdate: function (event) {
		   var content=UE.getEditor('editor').getContent();  //内容
		   vm.cahgDutyInfo.content=content;
			var url = vm.cahgDutyInfo.dutyInfoId == null ? "../cahgdutyinfo/save" : "../cahgdutyinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgDutyInfo),
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
		del: function (event) {
			var dutyInfoIds = getSelectedRows();
			if(dutyInfoIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgdutyinfo/delete",
				    data: JSON.stringify(dutyInfoIds),
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
		getInfo: function(dutyInfoId){
			$.get("../cahgdutyinfo/info/"+dutyInfoId, function(r){
                vm.cahgDutyInfo = r.cahgDutyInfo;
                ue.ready(function(){  //编辑器加载好后执行
                    UE.getEditor('editor').setContent(vm.cahgDutyInfo.content);  //回显编辑内容
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