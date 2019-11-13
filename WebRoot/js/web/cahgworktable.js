$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgworktable/list',
        datatype: "json",
        colModel: [			
			{ label: 'workTableId', name: 'workTableId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 120 }, 			
		//	{ label: '附件名称', name: 'fileName', width: 80,hidden:true }, 			
			{ label: '附件名称', name: 'fileOldName', width: 80 }, 	
			{ label: '上传时间', name: 'createTime', width: 60 }, 	
			{ label: '状态', name: 'status', width: 40,formatter : function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-primary">显示</span>';
				}
				else{
					return '<span class="label label-warning">隐藏</span>';
				}
			} }, 
			/*{ label: '排序号', name: 'rank', width: 50 },*/
			{ label: '类别', name: 'categoryName', width: 50 }			
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
    vm.getCategoryList();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		tips:false,
		title: null,
		categoryList:[],
		cahgWorkTable: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips = false;
			vm.title = "新增";
			vm.cahgWorkTable = {status:0};
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
	        this.getCategoryList();  //
		},
		update: function (event) {
			var workTableId = getSelectedRow();
			if(workTableId == null){
				return ;
			}
			vm.showList = false;
			vm.tips = false;
            vm.title = "修改";
            $("#file").val("");
            $("#fileName").val("");
            $("#fileOldName").val("");
            
            vm.getInfo(workTableId);
            this.getCategoryList();
		},
		saveOrUpdate: function (event) {
			if($("#fileName").val()==null||$("#fileName").val()==''){
				alert("请先上传附件");
				return ;
			}
			var workTableCategoryId=$("#workTableCategoryId").val();
			if(workTableCategoryId==null||workTableCategoryId==''){
				alert("表格分类必须选择");
				return ;
			}
			var url = vm.cahgWorkTable.workTableId == null ? "../cahgworktable/save" : "../cahgworktable/update";
			vm.cahgWorkTable.workTableCategoryId=workTableCategoryId;
			vm.cahgWorkTable.fileName=$("#fileName").val();
			vm.cahgWorkTable.fileOldName=$("#fileOldName").val();
			vm.cahgWorkTable.rank=$("#rank").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgWorkTable),
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
			var workTableIds = getSelectedRows();
			if(workTableIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgworktable/delete",
				    data: JSON.stringify(workTableIds),
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
		getInfo: function(workTableId){
			$.get("../cahgworktable/info/"+workTableId, function(r){
                vm.cahgWorkTable = r.cahgWorkTable;
                $("#fileName").val( vm.cahgWorkTable.fileName);
		        $("#fileOldName").val( vm.cahgWorkTable.fileOldName);
            });
		},
		getCategoryList: function(){
			$.get("../cahgworktablecategory/selectList", function(r){
				vm.categoryList = r.list;
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
			    url: "../cahgworktable/upFile",
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
		
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'workTableCategoryId': $("#categoryId").val()},
                page:page
            }).trigger("reloadGrid");
		}
	}
});