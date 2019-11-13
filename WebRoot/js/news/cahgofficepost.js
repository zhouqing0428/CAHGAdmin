$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgofficepost/list',
        datatype: "json",
        colModel: [			
			{ label: 'officePostId', name: 'officePostId', width: 50, key: true,hidden: true },
			{ label: '标题', name: 'title', width: 80 }, 			
		//	{ label: '内容', name: 'content', width: 80 }, 			
		//	{ label: '文件名', name: 'fileName', width: 80 }, 			
		//	{ label: '文件原名', name: 'fileOldName', width: 80 }, 		
			{ label: '作者', name: 'author', width: 80 }, 			
			{ label: '发布时间', name: 'createDate', width: 80 }, 
			{ label: '是否上传附件', name: 'fileName', width: 80,formatter : function(value, options, row) {
				if (value == null|| value == undefined || value == '') {
					return '<span class="label label-warning">否</span>';
				}
				else{
					return '<span class="label label-primary">是</span>';
				}
			} },
			{ label: '状态', name: 'status', width: 80,formatter : function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-primary">显示</span>';
				}
				else{
					return '<span class="label label-warning">隐藏</span>';
				}
			} }, 	
			{ label: '科室', name: 'deptName', width: 80 }		
			//{ label: '部门id', name: 'deptId', width: 80 }			
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
		tips : false,
		deptList:[],
		cahgOfficePost: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips=false;
			vm.title = "新增";
			vm.cahgOfficePost = {status:0};
			this.getDeptList();
			$("#file").val('');
			$("#fileName").val('');
			$("#fileOldName").val('');
			UE.getEditor('editor').setContent('');  //编辑内容为空
		},
		update: function (event) {
			var officePostId = getSelectedRow();
			if(officePostId == null){
				return ;
			}
			vm.showList = false;
			vm.tips=false;
            vm.title = "修改";
        	$("#file").val('');
        	
            this.getDeptList();
            vm.getInfo(officePostId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgOfficePost.officePostId == null ? "../cahgofficepost/save" : "../cahgofficepost/update";
		    var content=UE.getEditor('editor').getContent();  //内容
		    vm.cahgOfficePost.content=content;
			vm.cahgOfficePost.deptId=$("#deptId").val();
			vm.cahgOfficePost.fileName=$("#fileName").val();
			vm.cahgOfficePost.fileOldName=$("#fileOldName").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgOfficePost),
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
			var officePostIds = getSelectedRows();
			if(officePostIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgofficepost/delete",
				    data: JSON.stringify(officePostIds),
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
			$.ajaxFileUpload({
					type: "POST",
					fileElementId: 'file', //文件上传的ID
				    url: "../cahgofficepost/upFile",
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
		getInfo: function(officePostId){
			$.get("../cahgofficepost/info/"+officePostId, function(r){
				UE.getEditor('editor').setContent(r.cahgOfficePost.content)  //回显编辑内容
				$("#fileName").val(r.cahgOfficePost.fileName);
				$("#fileOldName").val(r.cahgOfficePost.fileOldName);
                vm.cahgOfficePost = r.cahgOfficePost;
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		getFileName: function(o){
			var pos=o.lastIndexOf("\\");
			return o.substring(pos+1);  
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