$(function () {
	var surveyQuestionId=$("#surveyQuestionId").val();
	var pollSum=$("#pollSum").val()
    $("#jqGrid").jqGrid({
        url: '../CAHGAdmin/cahgsurveyanswer/list',
        datatype: "json",
        postData:{'surveyQuestionId': surveyQuestionId,
        	     'pollSum': pollSum},  //提交参数
        colModel: [			
			{ label: 'surveyAnswerId', name: 'surveyAnswerId', width: 50, key: true,hidden:true },
			{ label: '选项描述', name: 'choiceText', width: 80 }, 			
			{ label: '选项得票数量', name: 'poll', width: 80 },
			{ label: '百分比', name: 'percent', width: 80 }		
        ],
		viewrecords: true,
        height: 485,
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
		cahgSurveyAnswer: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgSurveyAnswer = {};
		},
		update: function (event) {
			var surveyAnswerId = getSelectedRow();
			if(surveyAnswerId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(surveyAnswerId)
		},
		saveOrUpdate: function (event) {
			var surveyQuestionId=$("#surveyQuestionId").val();
			vm.cahgSurveyAnswer.surveyQuestionId=surveyQuestionId;
			var url = vm.cahgSurveyAnswer.surveyAnswerId == null ? "../CAHGAdmin/cahgsurveyanswer/save" : "../CAHGAdmin/cahgsurveyanswer/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgSurveyAnswer),
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
			var surveyAnswerIds = getSelectedRows();
			if(surveyAnswerIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../CAHGAdmin/cahgsurveyanswer/delete",
				    data: JSON.stringify(surveyAnswerIds),
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
		getInfo: function(surveyAnswerId){
			$.get("../CAHGAdmin/cahgsurveyanswer/info/"+surveyAnswerId, function(r){
                vm.cahgSurveyAnswer = r.cahgSurveyAnswer;
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