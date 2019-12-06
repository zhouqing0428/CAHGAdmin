$(function () {
    $("#jqGrid").jqGrid({
        url: '../sysdept/list',
        datatype: "json",
        colModel: [			
			{ label: 'deptId', name: 'deptId', width: 50, key: true,hidden : true },
			{ label: '科室编码', name: 'number', width: 30},		
			{ label: '科室名称', name: 'name', width: 80 },
			{ label: '上级科室', name: 'parentName', width: 80}, 		
		/*	{ label: '科室职责', name: 'duty', width: 80 }, 			
			{ label: '科室制度', name: 'regime', width: 80 }, 			
			{ label: '操作规范', name: 'workStandard', width: 80 }, 	
			{ label: '岗位', name: 'role', width: 80 },	*/
			{ label: '排序号', name: 'sysRank', width: 30 },
			{ label: '备注', name: 'remark', width: 100 }
        ],
		viewrecords: true,
        height: 536,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 30, 
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
//        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "deptId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	}
};

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		deptList:[],
		sysDept: {
			parentName:null,
			parentId:0
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		
		getDept: function(deptId){
			//加载菜单树
			$.get("../sysdept/select", function(r){
				vm.deptList = r.deptList;
			})
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysDept = {parentName:null,parentId:0};

			vm.getDept();
		},
		update: function (event) {
			var deptId = getSelectedRow();
			if(deptId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
//            $("#duty").val('');
//            $("#regime").val('');
//            $("#workStandard").val('');
            vm.getDept();
            vm.getInfo(deptId);
		},
		saveOrUpdate: function (event) {
			var url = vm.sysDept.deptId == null ? "../sysdept/save" : "../sysdept/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.sysDept),
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
			var deptIds = getSelectedRows();
			if(deptIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sysdept/delete",
				    data: JSON.stringify(deptIds),
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
		getInfo: function(deptId){
			$.get("../sysdept/info/"+deptId, function(r){
                vm.sysDept = r.sysDept;
//                $("#duty").val(vm.sysDept.duty);
//                $("#regime").val(vm.sysDept.regime);
//                $("#workStandard").val(vm.sysDept.workStandard);
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