$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgactivitynotice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '活动标题', name: 'title', width: 150 }, 
			{ label: '活动目标', name: 'content', width: 100 }, 
			{ label: '活动方案', name: 'plan', width: 100 }, 
			{ label: '文件名', name: 'fileOldName', width: 60 }, 
			{ label: '活动地点', name: 'address', width: 100 }, 
			{ label: '活动时间', name: 'time', width: 100 }, 
			{ label: '参加人员', name: 'person', width: 100 }, 
			{ label: '发布人', name: 'userName', width: 32 },
			{ label: '发布时间', name: 'createDate', width: 55}
        ],
		viewrecords: true,
        height: 385,
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    vm.getCategoryList();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		tips:false,
		title: null,
		q:{
			title:""
		},
		info: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.tips =false;
			vm.showList = false;
			vm.title = "新增";
			vm.info = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.tips =false;
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var title = $("#noticeTitle").val();
			if(title == null || title == ""){
				alert("请填写标题");
				return;
			}
			
			var url = vm.info.id == null ? "../cahgactivitynotice/save" : "../cahgactivitynotice/update";
	
			vm.info.fileName=$("#fileName").val();
			vm.info.fileOldName=$("#fileOldName").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.info),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgactivitynotice/delete",
				    data: JSON.stringify(ids),
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
		
		delFile:function(){
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的附件吗？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgactivitynotice/delFile",
				    data: JSON.stringify(ids),
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
		upfile: function(){
			if($("#file").val()==null||$("#file").val()==''){
				alert("选择文件才能上传");
				return ;
			}
			var file=$("#file").val();
		    $.ajaxFileUpload({
				type: "POST",
				fileElementId: 'file', //文件上传的ID
			    url: "../cahgactivitynotice/upFile",
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
		getInfo: function(id){
			$.get("../cahgactivitynotice/info/"+id, function(r){
                vm.info = r.info;
                $("#fileName").val(vm.info.fileName);
    			$("#fileOldName").val(vm.info.fileOldName);
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData : {
					'title' : $("#title").val()
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});