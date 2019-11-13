$(function () {
    $("#jqGrid").jqGrid({
        url: '../CAHGAdmin/sysdeptworkstandard/list',
        datatype: "json",
        postData:{'deptId':$("#deptId").val()}, //发送数据  
        colModel: [			
			{ label: 'workStandardId', name: 'workStandardId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 80 }, 			
		/*	{ label: '', name: 'fileName', width: 80 }, */			
			{ label: '附件名称', name: 'fileOldName', width: 80 }, 			
			{ label: '添加时间', name: 'createDate', width: 80 }, 			
			/*{ label: '', name: 'createUserId', width: 80 }, 			
			{ label: '', name: 'lastUpdateDate', width: 80 }, 			
			{ label: '', name: 'lasteUpdateUserId', width: 80 }, */			
			{ label: '', name: 'deptId', width: 80,hidden:true }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,20,30],
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
		tips: false,
		title: null,
		sysDeptWorkStandard: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.showList = false;
			vm.tips =false;
			vm.title = "新增";
			vm.sysDeptWorkStandard = {};
		},
		update: function (event) {
			var workStandardId = getSelectedRow();
			if(workStandardId == null){
				return ;
			}
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.showList = false;
			vm.tips = false;
            vm.title = "修改";
            
            vm.getInfo(workStandardId)
		},
		saveOrUpdate: function (event) {
			if($("#fileName").val()==null||$("#fileName").val()==''){
				alert("请先上传附件");
				return ;
			}
			vm.sysDeptWorkStandard.deptId=$("#deptId").val();
			vm.sysDeptWorkStandard.fileName=$("#fileName").val();
			vm.sysDeptWorkStandard.fileOldName=$("#fileOldName").val();
			var url = vm.sysDeptWorkStandard.workStandardId == null ? "../CAHGAdmin/sysdeptworkstandard/save" : "../CAHGAdmin/sysdeptworkstandard/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.sysDeptWorkStandard),
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
			var workStandardIds = getSelectedRows();
			if(workStandardIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../CAHGAdmin/sysdeptworkstandard/delete",
				    data: JSON.stringify(workStandardIds),
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
		upfile: function(){
			if($("#file").val()==null||$("#file").val()==''){
				alert("选择文件才能上传");
				return ;
			}
			var file=$("#file").val();
		    $.ajaxFileUpload({
				type: "POST",
				fileElementId: 'file', //文件上传的ID
			    url: "../CAHGAdmin/sysdeptworkstandard/upFile",
			    async: false,
			    success: function(name){
				 	if(name!="err"){
				 		$("#fileName").val(name);
				 		$("#fileOldName").val(vm.getFileName(file));  //附件原名
				 		vm.tips = true;
						alert("上传成功");
					}else{
						vm.tips = false;
						alert("上传失败");
					} 
				}
		   }); 
		},
		getFileName: function(o){
			var pos=o.lastIndexOf("\\");
			return o.substring(pos+1);  
		},
		getInfo: function(workStandardId){
			$.get("../CAHGAdmin/sysdeptworkstandard/info/"+workStandardId, function(r){
                vm.sysDeptWorkStandard = r.sysDeptWorkStandard;
                $("#fileName").val(vm.sysDeptWorkStandard.fileName);
    			$("#fileOldName").val(vm.sysDeptWorkStandard.fileOldName);
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