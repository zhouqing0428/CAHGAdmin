$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgcommonforms/list',
        datatype: "json",
        colModel: [			
			{ label: 'commonFormsId', name: 'commonFormsId', width: 50, key: true,hidden: true },
			{ label: '表格名称', name: 'fileOldName', width: 80 }, 
			{ label: '类别', name: 'type', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">财务</span>';  
				}
				if(value =='1'){
					return '<span class="label label-success">后勤</span>';  
				}
				if(value =='2'){
					return '<span class="label label-info">技术</span>';  
				}
				if(value =='3'){
					return '<span class="label label-warning">机要</span>';  
				}
				if(value =='4'){
					return '<span class="label label-danger">科室日常办公</span>';  
				}
			}    }, 		
			{ label: '排序号', name: 'rank', width: 80 }, 
				
			{ label: '上传时间', name: 'createDate', width: 80 }, 
			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}   }, 	
			{ label: '科室', name: 'deptName', width: 80 }			
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
		cahgCommonForms: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgCommonForms = {status:0,type:0};
			vm.tips = false;
			$("#file").val("");
			$("#fileName").val("");
	        $("#fileOldName").val("");
			this.getDeptList();
		},
		update: function (event) {
			var commonFormsId = getSelectedRow();
			if(commonFormsId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.tips = false;
            $("#file").val("");
            $("#fileName").val("");
            $("#fileOldName").val("");
            
            vm.getInfo(commonFormsId);
           //获取部门信息
			this.getDeptList();
		},
		saveOrUpdate: function (event) {
			if($("#fileName").val()==null||$("#fileName").val()==''){
				alert("请先上传附件");
				return ;
			}
			var url = vm.cahgCommonForms.commonFormsId == null ? "../cahgcommonforms/save" : "../cahgcommonforms/update";
			vm.cahgCommonForms.deptId=$("#deptId").val();
			vm.cahgCommonForms.fileName=$("#fileName").val();
			vm.cahgCommonForms.fileOldName=$("#fileOldName").val();
			vm.cahgCommonForms.rank=$("#rank").val()
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgCommonForms),
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
			var commonFormsIds = getSelectedRows();
			if(commonFormsIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgcommonforms/delete",
				    data: JSON.stringify(commonFormsIds),
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
			    url: "../cahgcommonforms/upFile",
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
		
		getInfo: function(commonFormsId){
			$.get("../cahgcommonforms/info/"+commonFormsId, function(r){
		        vm.cahgCommonForms = r.cahgCommonForms;
		        $("#fileName").val( vm.cahgCommonForms.fileName);
		        $("#fileOldName").val( vm.cahgCommonForms.fileOldName);
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
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