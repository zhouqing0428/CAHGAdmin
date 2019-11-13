$(function () {
///	var surveyId=$("#surveyId").val();
    $("#jqGrid").jqGrid({
        url: '../cahgsurveyquestion/list',
        datatype: "json",
 //       postData:{'surveyId': surveyId},  //提交参数
        colModel: [			
			{ label: 'surveyQuestionId', name: 'surveyQuestionId', width: 50, key: true,hidden:true },
			{ label: '问题名称', name: 'question', width: 80 }, 			
			{ label: '类型', name: 'answerType', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">单选</span>';  
				}
				if(value =='1'){
					return '<span class="label label-success">多选</span>';  
				}
			}   }, 
			{ label: '选项', name: 'surveyQuestionId', width: 80, formatter: function(value, options, row){
				return '<a  class="btn btn-primary btn-xs" href="../cahgsurveyquestion/answerInfo?surveyQuestionId='+value+'">查看</a>';
			} },
			{ label: '排序号', name: 'orderId', width: 80 },
			{ label: '添加时间', name: 'createDate', width: 80 }	,
			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}   }
	//		{ label: '卷名id', name: 'surveyId', width: 80,hidden:true }			
        ],
		viewrecords: true,
        height: 500,
        rowNum: 10,
		rowList : [30,50],
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
		cahgSurveyQuestion: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgSurveyQuestion = {answerType:0,status:0};
		},
		update: function (event) {
			var surveyQuestionId = getSelectedRow();
			if(surveyQuestionId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(surveyQuestionId)
		},
		//首页显示
		indexShow: function(event){
			var surveyQuestionId = getSelectedRow();
			if(surveyQuestionId == null){
				return ;
			}
			$.get("../cahgsurveyquestion/indexShow/"+surveyQuestionId, function(r){
				if(r.code === 0){
					alert('操作成功', function(index){
						vm.reload();
					});
				}else{
					alert(r.msg);
				}
            });
		},
		saveOrUpdate: function (event) {
		//	vm.cahgSurveyQuestion.surveyId=$("#surveyId").val();
			vm.cahgSurveyQuestion.orderId=$("#orderId").val();
			var url = vm.cahgSurveyQuestion.surveyQuestionId == null ? "../cahgsurveyquestion/save" : "../cahgsurveyquestion/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgSurveyQuestion),
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
			var surveyQuestionIds = getSelectedRows();
			if(surveyQuestionIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgsurveyquestion/delete",
				    data: JSON.stringify(surveyQuestionIds),
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
		getInfo: function(surveyQuestionId){
			$.get("../cahgsurveyquestion/info/"+surveyQuestionId, function(r){
                vm.cahgSurveyQuestion = r.cahgSurveyQuestion;
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