$(function() {
	$("#jqGrid").jqGrid({
		url : '../cahgjob/list',
		datatype : "json",
		colModel : [
				{
					label : 'jobId',
					name : 'jobId',
					width : 50,
					key : true,
					hidden : true
				},
				{
					label : '工作标题',
					name : 'title',
					width : 80
				},
				// { label: '工作内容', name: 'content', width: 80
				// },
				{
					label : '发起时间',
					name : 'createUserDate',
					width : 80
				},
				{
					label : '计划完成时间',
					name : 'endTime',
					width : 80,
				},
				{
					label : '办结时间',
					name : 'finishTime',
					width : 80,
				},
				{
					label : '状态',
					name : 'status',
					width : 80,
					formatter : function(value, options, row) {
						if (value == 1) {
							return '<span class="label label-primary">在办</span>';
						}
						if (value == 2) {
							return '<span class="label label-success">已办</span>';
						}
						if (value == 3) {
							return '<span class="label label-danger">超时办结</span>';
						}
					}
				},
				// { label: '紧急程度： 0 低 1 中 2 高', name:
				// 'urgentStatus', width: 80 },
				/*
				{
					label : '发起人',
					name : 'createUser',
					width : 80
				},
				{
					label : '最后处理人',
					name : 'lastUpdateName',
					width : 80
				},
				/*{
					label : '最后处理时间',
					name : 'lastUpdateUserDate',
					width : 80
				},*/
				/*{
					label : '跟进',
					name : 'jobId',
					width : 50,
					formatter : function(value, options, row) {
						return '<a  class="btn btn-primary btn-xs" href="../cahgjob/jobDetail?jobId='
								+ value + '">查看</a>';
					}
				}*/ ],
		viewrecords : true,
		height : 385,
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

	$("#deptId").change(function() { // 选择部门后执行
		vm.cahgJob.deptId = "";
		if (jQuery.isNumeric($("#deptId").val())) {
			vm.getUserList($("#deptId").val());
		}
	});

	$("#deptId2").change(function() { // 选择部门后执行
		vm.user.deptId = "";
		if (jQuery.isNumeric($("#deptId2").val())) {
			vm.getUserList2($("#deptId2").val());
		}
	});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		//add : false,
		//copy : false,
		title : null,
		deptList : [],
		cahgJob : {},
		user : {},
		cahgJobCopy : {}
	},
	methods : {
		getUserList : function(deptId) {
			vm.cahgJob.deptId = deptId
			// alert(vm.cahgJob.deptId);
			$.ajax({
				type : "POST",
				url : "../sys/user/selectList",
				data : JSON.stringify(vm.cahgJob),
				// dataType: "json",
				success : function(data) {
					var size = data.length;
					$("#flowUserId").empty();// 设置之前加载数据为空
					// $("#userId").append("<option>"+"--请选择流转人--"+"<option>");
					for (var i = 0; i < size; i++) {
						var user = data[i];
						var $option = $("<option value=" + user.userId + ">"
								+ user.name + "</option>");
						$("#flowUserId").append($option);
					}
				}
			});
		},
		getUserList2 : function(deptId) {
			vm.user.deptId = deptId
			$.ajax({
				type : "POST",
				url : "../sys/user/selectList",
				data : JSON.stringify(vm.user),
				// dataType: "json",
				success : function(data) {
					var size = data.length;
					console.log(size);
					$("#toUserId").empty();// 设置之前加载数据为空
					// $("#userId").append("<option>"+"--请选择流转人--"+"<option>");
					for (var i = 0; i < size; i++) {
						var user = data[i];
						var $option = $("<option value=" + user.userId + ">"
								+ user.name + "</option>");
						$("#toUserId").append($option);
					}
				}
			});
		},
		query : function() {
			vm.reload();
		},
		toadd : function() {
			vm.showList = false;
			//vm.copy = false;
			vm.add = true;
			vm.title = "新增";
			vm.cahgJob = {
				urgentStatus : 0
			};
			//$("#content").val("");
			// 获取部门信息
			//this.getDeptList();

		},
		update : function(event) {
			var jobId = getSelectedRow();
			if (jobId == null) {
				return;
			}
			vm.showList = false;
			
			vm.title = "修改";

			vm.getInfo(jobId)
		},
		finish: function(event) {
			var jobId = getSelectedRow();
			if (jobId == null) {
				return;
			}
			//vm.cahgJob=null;
			//vm.getInfo(jobId);
			//alert(vm.cahgJob.status);
			$.get("../cahgjob/info/" + jobId, function(r) {
				vm.cahgJob = r.cahgJob;
				if(vm.cahgJob.endTime==null){
					alert("计划结束时间未填写,工作不能完结");
					return ;
				}
				if(vm.cahgJob.status==2||vm.cahgJob.status==3){
					alert("工作已完结");
					return ;
				}
				$.ajax({
					type : "POST",
					url : "../cahgjob/finish",
					data : JSON.stringify(vm.cahgJob),
					success : function(r) {
						if (r.code === 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						if (textStatus != "success") {
							location.reload(true);
						}
						;
					}
				});
			});
	
		},
		saveOrUpdate : function(event) {
			/*var flowUserId = $("#flowUserId").val();
			if (flowUserId == null || flowUserId == "") {
				alert("请选择流转人");
				return;
			}*/
			var endTime = $("#endTime").val();
			if(endTime==null||endTime==''){
				alert("请填写计划完成时间");
				return ;
			}
			var url = vm.cahgJob.jobId == null ? "../cahgjob/save"
					: "../cahgjob/update";
			vm.cahgJob.endTime = endTime;
			//content = content.replace(/\n|\r\n/g, "<br>");
			vm.cahgJob.content=UE.getEditor('editor').getContent(); 
			//vm.cahgJob.flowUserId = flowUserId;
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(vm.cahgJob),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					if (textStatus != "success") {
						location.reload(true);
					}
					;
				}
			});
		},
		del : function(event) {
			var jobIds = getSelectedRows();
			if (jobIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : "../cahgjob/delete",
					data : JSON.stringify(jobIds),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						if (textStatus != "success") {
							location.reload(true);
						}
						;
					}
				});
			});
		},
		// 到抄送页面
		tocopy : function() {
			var jobId = getSelectedRow();
			if (jobId == null) {
				return;
			}
			// 获取部门信息
			this.getDeptList();
			vm.title = "抄送";
			vm.copy = true;
			vm.add = false;
			vm.showList = false;
			vm.cahgJobCopy.jobId = jobId;
		},
		addcopy : function() {
			/*var toUserId = $("#toUserId").val();
			if (toUserId == null || toUserId == "") {
				alert("请选择抄送到的人");
				return;
			}
			vm.cahgJobCopy.toUserId = toUserId;*/
			// alert(vm.cahgJobCopy.jobId);
			var url = vm.cahgJobCopy.jobCopyId == null ? "../cahgjobcopy/save": "../cahgjobcopy/update";
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(vm.cahgJobCopy),
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
		getInfo : function(jobId) {
			$.get("../cahgjob/info/" + jobId, function(r) {
				UE.getEditor('editor').setContent(r.cahgJob.content);  //回显编辑内容 
				vm.cahgJob = r.cahgJob;
			});
		},
		// 部门列表
		getDeptList : function() {
			$.get("../sysdept/selectList", function(r) {
				vm.deptList = r.list;
			});
		},
		reload : function(event) {
			vm.showList = true;
			vm.add = false;
			vm.copy = false;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'status' : $("#status").val()
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});