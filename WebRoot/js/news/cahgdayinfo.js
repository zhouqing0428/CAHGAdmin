$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgdayinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'dayId', name: 'dayId', width: 50, key: true,hidden:true },
			{ label: '每日信息标题', name: 'dayTitle', width: 120 }, 			
		//	{ label: '每日信息内容', name: 'dayContent', width: 80 }, 			
			{ label: '作者', name: 'author', width: 50 }, 			
			{ label: '发布时间', name: 'createDate', width: 60 }, 			
		//	{ label: '创建人', name: 'createUserId', width: 80 }, 
			{ label: '发布科室', name: 'deptName', width: 50 }, 			
			{ label: '显示状态', name: 'dayStatus', width: 40, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			} }	,
			{ label: '排序号', name: 'dayRank', width: 40 }, 	
			{ label: '置顶状态 0：置顶', name: 'dayStick', width: 50,hidden:true } 		
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
		q : {
			title : null,
			author : null
		},
		deptList:[],
		cahgDayInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgDayInfo = {dayStatus:0};
			UE.getEditor('editor').setContent('');  //编辑内容为空
			//获取部门信息
			this.getDeptList();
		},
		update: function (event) {
			var dayId = getSelectedRow();
			if(dayId == null){
				return ;
			}
			$("#selectedDept").attr("selected","selected");
			vm.showList = false;
            vm.title = "修改";
            //获取部门信息
			this.getDeptList();
            vm.getInfo(dayId)
        
		},
		saveOrUpdate: function (event) {
		   $("#selectedDept").removeAttr("selected");
		   var content=UE.getEditor('editor').getContent();  //新闻内容
		   vm.cahgDayInfo.dayContent=content;
		   vm.cahgDayInfo.deptId=$("#deptId").val();
		   vm.cahgDayInfo.dayRank=$("#dayRank").val();
		   var url = vm.cahgDayInfo.dayId == null ? "../cahgdayinfo/save" : "../cahgdayinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgDayInfo),
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
			var dayIds = getSelectedRows();
			if(dayIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgdayinfo/delete",
				    data: JSON.stringify(dayIds),
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
		getInfo: function(dayId){
			$.get("../cahgdayinfo/info/"+dayId, function(r){
				UE.getEditor('editor').setContent(r.cahgDayInfo.dayContent)  //回显编辑内容
                vm.cahgDayInfo = r.cahgDayInfo;
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
				postData : {
					'dayTitle' : vm.q.title,
					'author' : vm.q.author
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});