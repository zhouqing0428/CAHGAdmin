$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : '../bsuser/list',
						datatype : "json",
						colModel : [
								{
									label : 'bsUserId',
									name : 'bsUserId',
									width : 50,
									key : true,
									hidden : true
								},
								{
									label : '用户名',
									name : 'userName',
									width : 80,
									hidden : true
								},
								{
									label : '密码',
									name : 'password',
									width : 80,
									hidden : true
								},
								{
									label : '姓名',
									name : 'name',
									width : 80
								},
								{
									label : '手机号码',
									name : 'mobile',
									width : 80
								},
								{
									label : '备注',
									name : 'note',
									width : 80
								},
								{
									label : '是否备注',
									name : 'note',
									width : 80,
									formatter : function(value, options, row) {
										if (value == null|| value == undefined || value == '') {
											return '<span class="label label-primary">否</span>';
										}
										else{
											return '<span class="label label-danger">是</span>';
										}
									}
								},
								{
									label : '申请日期',
									name : 'applDate',
									width : 80
								},
								/*
								 * { label: '审核状态', name: 'bsUserState', width:
								 * 80 },
								 */
								{
									label : '审核状态',
									name : 'bsUserState',
									width : 50,
									formatter : function(value, options, row) {
										if (value == 0) {
											return '<span class="label label-primary">未审核</span>';
										}
										if (value == 1) {
											return '<span class="label label-danger">审核失败</span>';
										}
										if (value == 2) {
											return '<span class="label label-success">审核通过</span>';
										}
									}
								},
								{
									label : '性别',
									name : 'sex',
									width : 80,
									hidden : true
								},
								{
									label : '自定义信息',
									name : 'bsUserId',
									width : 50,
									formatter : function(value, options, row) {
										return '<button  class="btn btn-primary btn-xs" onclick="difiInfo('
												+ value + ')" >查看</button>';
									}
								}

						],
						viewrecords : true,
						height : 400,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 25,
						autowidth : true,
						multiselect : true,
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

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			moblie : null,
			name : null
		},
		showList : true,
		title : null,
		bsUser : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.bsUser = {};
		},
		update : function(event) {
			var bsUserId = getSelectedRow();
			if (bsUserId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(bsUserId)
		},
		saveOrUpdate : function(event) {
			var url = vm.bsUser.bsUserId == null ? "../bsuser/save": "../bsuser/update";
			console.log(vm.bsUser.applDate);
			console.log($("#applDate").val());
			vm.bsUser.applDate = $("#applDate").val();
			console.log(vm.bsUser.applDate);
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(vm.bsUser),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del : function(event) {
			var bsUserIds = getSelectedRows();
			if (bsUserIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : "../bsuser/delete",
					data : JSON.stringify(bsUserIds),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},

		// 审核通过

		approved : function(event) {
			var bsUserIds = getSelectedRows();
			if (bsUserIds == null) {
				return;
			}
			$.ajax({
				type : "POST",
				url : "../bsuser/approved",
				data : JSON.stringify(bsUserIds),
				success : function(r) {
					if (r.code == 0) {
						alert('操作成功', function(index) {
							$("#jqGrid").trigger("reloadGrid");
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},

		// 审核失败
		auditFailure : function(event) {
			var bsUserIds = getSelectedRows();
			if (bsUserIds == null) {
				return;
			}

			$.ajax({
				type : "POST",
				url : "../bsuser/auditFailure",
				data : JSON.stringify(bsUserIds),
				success : function(r) {
					if (r.code == 0) {
						alert('操作成功', function(index) {
							$("#jqGrid").trigger("reloadGrid");
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},

		// 解除审核
		removeAudit : function(event) {
			var bsUserIds = getSelectedRows();
			if (bsUserIds == null) {
				return;
			}

			confirm('确定要解除审核选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : "../bsuser/removeAudit",
					data : JSON.stringify(bsUserIds),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},

		// 导出
		exports : function(event) {
			location.href = "../bsuser/exports";
		},

		getInfo : function(bsUserId) {
			$.get("../bsuser/info/" + bsUserId, function(r) {
				vm.bsUser = r.bsUser;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'name' : vm.q.name,
					'mobile' : vm.q.mobile,
					'bs_user_state' : $("#bs_user_state").val()
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});