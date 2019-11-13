$(function () {
	var deptID = $("#deptID").val();
	window.onload=function(){
			$.get("../CAHGAdmin/sysDept/selectListContent/"+deptID, function(r){
	            vm.sysDept = r.sysDept;
	        });
	
	}

});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		sysDept: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		update: function (event) {
			var deptId = getSelectedRow();
			if(deptId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(deptId)
		},
		saveOrUpdate: function (event) {
			var deptId=$("#deptId").val();
			vm.sysDept.deptId=deptId;
			var url = "";
			if(vm.sysDept.deptId !=null){
				url = "../CAHGAdmin/sysdept/updateContent"
			}
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
				}
			});
		},
		getInfo: function(deptId){
			$.get("../CAHGAdmin/sysDept/selectListContent/"+deptId, function(r){
                vm.sysDept = r.sysDept;
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