$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgintrofile/list',
        datatype: "json",
        colModel: [			
			{ label: 'introFileId', name: 'introFileId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 80 }, 			
			/*{ label: '', name: 'fileName', width: 80 }, 		*/	
			{ label: '文件名称', name: 'fileOldName', width: 80 }, 	
			{ label: '排序号', name: 'rank', width: 80 },
			{ label: '上传时间', name: 'createDate', width: 80 }			
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
		tips:false,
		title: null,
		cahgIntroFile: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips = false;
			vm.title = "新增";
			vm.cahgIntroFile = {};
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
		},
		update: function (event) {
			var introFileId = getSelectedRow();
			if(introFileId == null){
				return ;
			}
			vm.showList = false;
			vm.tips = false;
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
            vm.title = "修改";
            
            vm.getInfo(introFileId)
		},
		saveOrUpdate: function (event) {
			if($("#fileName").val()==null||$("#fileName").val()==''){
				alert("请先上传附件");
				return ;
			}
			var url = vm.cahgIntroFile.introFileId == null ? "../cahgintrofile/save" : "../cahgintrofile/update";
			vm.cahgIntroFile.fileName=$("#fileName").val();
			vm.cahgIntroFile.fileOldName=$("#fileOldName").val();
			vm.cahgIntroFile.rank=$("#rank").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgIntroFile),
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
			var introFileIds = getSelectedRows();
			if(introFileIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgintrofile/delete",
				    data: JSON.stringify(introFileIds),
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
			    url: "../cahgintrofile/upFile",
			    async: false,
			    success: function(name){
				 	if(name!="err"){
				 		$("#fileName").val(name);
				 		//var temp=$("#file").val();//IE 浏览器下文件被动刷新，所以不起作用
				 		//$("#fileOldName").val(vm.getFileName(temp));  //附件原名  /*IE兼容有问题*/
				 		//$("#fileOldName").val(temp.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1"));  //附件原名 /*IE兼容有问题*/
				 		$("#fileOldName").val(file.substring( file.lastIndexOf('\\')+1 ))
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
		getInfo: function(introFileId){
			$.get("../cahgintrofile/info/"+introFileId, function(r){
                vm.cahgIntroFile = r.cahgIntroFile;
                $("#fileName").val( vm.cahgIntroFile.fileName);
		        $("#fileOldName").val( vm.cahgIntroFile.fileOldName);
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