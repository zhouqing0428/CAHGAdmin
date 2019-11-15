$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgaffiche/list',
        datatype: "json",
        colModel: [			
			{ label: 'afficheId', name: 'afficheId', width: 50, key: true,hidden: true },
			{ label: '标题', name: 'title', width: 120 }, 			
			{ label: '发布时间', name: 'createDate', width: 50 }, 			
			{ label: '发文人', name: 'author', width: 50 }, 
			{ label: '状态', name: 'status', width: 40, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}   }, 
			
			{ label: '发布科室', name: 'deptName', width: 50 }
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
		cahgAffiche: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgAffiche = {status:0};
			UE.getEditor('editor').setContent('');  //编辑内容为空
			//获取部门信息
			this.getDeptList();
		},
		update: function (event) {
			var afficheId = getSelectedRow();
			if(afficheId == null){
				return ;
			}
			$("#selectedDept").attr("selected","selected");
			vm.showList = false;
            vm.title = "修改";
            //获取部门信息
			this.getDeptList();
            
            vm.getInfo(afficheId)
		},
		saveOrUpdate: function (event) {
		   $("#selectedDept").removeAttr("selected");
		   var content=UE.getEditor('editor').getContent();  //获取内容
		   vm.cahgAffiche.content=content;
		   vm.cahgAffiche.deptId=$("#deptId").val();
		   vm.cahgAffiche.rank=$("#rank").val();
		   var url = vm.cahgAffiche.afficheId == null ? "../cahgaffiche/save" : "../cahgaffiche/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgAffiche),
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
			var afficheIds = getSelectedRows();
			if(afficheIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgaffiche/delete",
				    data: JSON.stringify(afficheIds),
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
		getInfo: function(afficheId){
			$.get("../cahgaffiche/info/"+afficheId, function(r){
				UE.getEditor('editor').setContent(r.cahgAffiche.content)  //回显编辑内容
                vm.cahgAffiche = r.cahgAffiche;
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		reload: function (event) {
			$("#selectedDept").removeAttr("selected");
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});