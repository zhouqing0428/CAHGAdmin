$(function() {
	$("#jqGrid").jqGrid({
		url : '../cahgjob/resultList?jobId='+jobId,
		datatype : "json",
		colModel : [
				{
					label : '经办人',
					name : 'userName',
					width : 30
				},
				{
					label : '经办科室',
					name : 'deptName',
					width : 100
				},
				{
					label : '办理情况',
					name : 'content',
					width : 300,
				},
				{
					label : '办理时间',
					name : 'createTime',
					width : 60,
				}],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 30,
		autowidth : true,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});