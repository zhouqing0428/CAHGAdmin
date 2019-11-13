$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgsurvey/list',
        datatype: "json",
        colModel: [			
			{ label: 'surveyId', name: 'surveyId', width: 50, key: true,hidden:true },
			{ label: '问卷名称', name: 'title', width: 80 }, 			
			{ label: '描述', name: 'description', width: 80 }, 			
			{ label: '添加时间', name: 'createDate', width: 80 }, 	
			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}  },
			{ label: '问题', name: 'surveyId', width: 80, formatter: function(value, options, row){
				return '<a  class="btn btn-primary btn-xs" href="../cahgsurvey/questionInfo?surveyId='+value+'">查看</a>';
			} }			
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
		cahgSurvey: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgSurvey = {status:0};
		},
		update: function (event) {
			var surveyId = getSelectedRow();
			if(surveyId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(surveyId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgSurvey.surveyId == null ? "../cahgsurvey/save" : "../cahgsurvey/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgSurvey),
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
			var surveyIds = getSelectedRows();
			if(surveyIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgsurvey/delete",
				    data: JSON.stringify(surveyIds),
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
		getInfo: function(surveyId){
			$.get("../cahgsurvey/info/"+surveyId, function(r){
                vm.cahgSurvey = r.cahgSurvey;
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