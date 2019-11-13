$(function () {
    $("#jqGrid").jqGrid({
        url: '../CAHGAdmin/sysdeptregime/list',
        datatype: "json",
        postData:{'deptId':$("#deptId").val()},
        colModel: [			
			{ label: 'regimeId', name: 'regimeId', width: 50, key: true ,hidden:true},
			{ label: '标题', name: 'title', width: 90 }, 			
			//{ label: '', name: 'fileName', width: 80 }, 			
			{ label: '附件名称', name: 'fileOldName', width: 90 }, 			
			{ label: '添加时间', name: 'createDate', width: 60 }, 			
			/*{ label: '', name: 'createUserId', width: 80 }, 			
			{ label: '', name: 'lastUpdateDate', width: 80 }, 			
			{ label: '', name: 'lastUpdateUserId', width: 80 },*/ 			
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
		tips:false,
		title: null,
		sysDeptRegime: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
	        vm.tips = false;
			vm.showList = false;
			vm.title = "新增";
			vm.sysDeptRegime = {};
		},
		update: function (event) {
			var regimeId = getSelectedRow();
			if(regimeId == null){
				return ;
			}
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(regimeId)
		},
		saveOrUpdate: function (event) {
			if($("#fileName").val()==null||$("#fileName").val()==''){
				alert("请先上传附件");
				return ;
			}
			vm.sysDeptRegime.fileName=$("#fileName").val();
			vm.sysDeptRegime.fileOldName=$("#fileOldName").val();
			var url = vm.sysDeptRegime.regimeId == null ? "../CAHGAdmin/sysdeptregime/save" : "../CAHGAdmin/sysdeptregime/update";
			vm.sysDeptRegime.deptId=$("#deptId").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.sysDeptRegime),
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
			var regimeIds = getSelectedRows();
			if(regimeIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../CAHGAdmin/sysdeptregime/delete",
				    data: JSON.stringify(regimeIds),
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
		   //var file=$("#file").val();
		   $.ajaxFileUpload({
				type: "POST",
				fileElementId: 'file', //文件上传的ID
			    url: "../CAHGAdmin/sysdeptregime/upFile",
			    async: false,
			    success: function(name){
				 	if(name!="err"){
				 		$("#fileName").val(name);
				 		$("#fileOldName").val(vm.getFileName($("#file").val()));  //附件原名
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
		getInfo: function(regimeId){
			$.get("../CAHGAdmin/sysdeptregime/info/"+regimeId, function(r){
                vm.sysDeptRegime = r.sysDeptRegime;
                $("#fileName").val(vm.sysDeptRegime.fileName);
    			$("#fileOldName").val(vm.sysDeptRegime.fileOldName);
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