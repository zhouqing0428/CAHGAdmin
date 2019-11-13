$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgimptwork/list',
        datatype: "json",
        colModel: [			
			{ label: 'imptWorkId', name: 'imptWorkId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 80 }, 			
//			{ label: '内容', name: 'content', width: 80 }, 			
			{ label: '作者', name: 'author', width: 80 }, 			
//			{ label: '', name: 'fileName', width: 80 }, 			
//	        { label: '', name: 'fileOldName', width: 80 }, 			
//			{ label: '', name: 'createUserId', width: 80 }, 			
			{ label: '发布时间', name: 'createDate', width: 80 }, 			
			{ label: '状态 ', name: 'status', width: 80 ,formatter : function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-primary">显示</span>';
				}
				else{
					return '<span class="label label-warning">隐藏</span>';
				}
			} }, 	
			{ label: '科室', name: 'deptName', width: 80 }	
			//{ label: '', name: 'deptId', width: 80 }			
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
		deptList:[],
		cahgImptWork: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			this.getDeptList();
			vm.cahgImptWork = {status:0};
		},
		update: function (event) {
			var imptWorkId = getSelectedRow();
			if(imptWorkId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
        	this.getDeptList();
            vm.getInfo(imptWorkId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgImptWork.imptWorkId == null ? "../cahgimptwork/save" : "../cahgimptwork/update";
		    var content=UE.getEditor('editor').getContent();  //内容
			vm.cahgImptWork.deptId=$("#deptId").val();
			//alert(vm.cahgImptWork.deptId);
			//return ;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgImptWork),
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
			var imptWorkIds = getSelectedRows();
			if(imptWorkIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgimptwork/delete",
				    data: JSON.stringify(imptWorkIds),
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
		getInfo: function(imptWorkId){
			$.get("../cahgimptwork/info/"+imptWorkId, function(r){
				UE.getEditor('editor').setContent(r.cahgImptWork.content)  //回显编辑内容
                vm.cahgImptWork = r.cahgImptWork;
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