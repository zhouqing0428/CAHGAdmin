$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgofficework/list',
        datatype: "json",
        colModel: [			
			{ label: 'officeWorkId', name: 'officeWorkId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 120 }, 
			{ label: '附件名称', name: 'fileOldName', width: 80 },
			{ label: '工作分类', name: 'categoryName', width: 80 }, 
			{ label: '备注', name: 'remark', width: 80 }, 			
			{ label: '添加时间', name: 'createDate', width: 60 }
		/*	{ label: '', name: 'officeWorkCategoryId', width: 80 } 			*/
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
    vm.getWorkCategoryList();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tips: false,
		workCategoryList:[],
		cahgOfficeWork: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips = false;
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
			vm.title = "新增";
			vm.cahgOfficeWork = {};
			//获取分类信息
			this.getWorkCategoryList();
		},
		update: function (event) {
			var officeWorkId = getSelectedRow();
			if(officeWorkId == null){
				return ;
			}
			$("#file").val("");
	        $("#fileName").val("");
	        $("#fileOldName").val("");
	        $("#categoryName").attr("selected","selected"); //设置回显数据被选中
			vm.showList = false;
			vm.tips = false;
            vm.title = "修改";
            vm.getInfo(officeWorkId);
          //获取分类信息
			this.getWorkCategoryList();
		},
		saveOrUpdate: function (event) {
			if($("#fileName").val()==null||$("#fileName").val()==''){
				alert("请先上传附件");
				return ;
			}
			if($("#officeWorkCategoryId").val()==''||$("#officeWorkCategoryId").val()==null){
				alert("请先选择工作分类");
				return ;
			}
			$("#categoryName").removeAttr("selected");
			vm.cahgOfficeWork.fileName=$("#fileName").val();
			vm.cahgOfficeWork.fileOldName=$("#fileOldName").val();
			vm.cahgOfficeWork.officeWorkCategoryId=$("#officeWorkCategoryId").val();
			var url = vm.cahgOfficeWork.officeWorkId == null ? "../cahgofficework/save" : "../cahgofficework/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgOfficeWork),
			    success: function(r){
			    	if(r.code === 0){
			    		//$("#categoryName").removeAttr("selected");
			    		//$("#categoryName").attr("selected","selected");
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
			var officeWorkIds = getSelectedRows();
			if(officeWorkIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgofficework/delete",
				    data: JSON.stringify(officeWorkIds),
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
			    url: "../cahgofficework/upFile",
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
		getInfo: function(officeWorkId){
			$.get("../cahgofficework/info/"+officeWorkId, function(r){
                vm.cahgOfficeWork = r.cahgOfficeWork;
                //console.log(vm.cahgOfficeWork.categoryName);
            	$("#fileName").val(vm.cahgOfficeWork.fileName);
    			$("#fileOldName").val(vm.cahgOfficeWork.fileOldName);
            });
		},
		//分类列表
		getWorkCategoryList: function(){
			$.get("../cahgofficeworkcategory/selectList", function(r){
				vm.workCategoryList = r.list;
			});
		},
		reload: function (event) {
			$("#categoryName").removeAttr("selected");
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'officeWorkCategoryId': $("#categoryId").val()},
                page:page
            }).trigger("reloadGrid");
		}
	}
});