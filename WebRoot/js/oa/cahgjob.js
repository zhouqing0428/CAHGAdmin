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
					label : 'status',
					name : 'status',
					hidden : true
				},
				{
					label : '工作标题',
					name : 'title',
					width : 120
				},
				{
					label : '经办科室',
					name : 'deptName',
					width : 80
				},
				{
					label : '计划完成时间',
					name : 'endTime',
					width : 52,
				},				
				{
					label : '状态',
					name : 'status',
					width : 30,
					formatter : function(value, options, row) {
						if (value == 0) {
							return '<span class="label label-primary">待办</span>';
						}
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
				{
					label : '办结时间',
					name : 'finishTime',
					width : 52,
				},
				{
					label : '发起人',
					name : 'createUser',
					width : 25
				},
				{
					label : '发起时间',
					name : 'createUserDate',
					width : 52
				},
				{
					label : '操作',
					name : 'operator',
					width : 38,
					formatter : function(value, options, row) {
						return '<a  class="btn btn-primary btn-xs" href="../cahgjob/viewResult?jobId='
								+ row.jobId + '">查看完成情况</a>';
					}
				}],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50, 100, 200, 300, 500 ],
		rownumbers : true,
		rownumWidth : 30,
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
		showList : true,
		add : true,
		title : null,
		deptList : [],
		cahgJob : {},
		user : {},
		cahgJobCopy : {}
	},
	methods : {
		getUserList : function(deptId) {
			vm.cahgJob.deptId = deptId
			$.ajax({
				type : "POST",
				url : "../sys/user/selectList",
				data : JSON.stringify(vm.cahgJob),
				success : function(data) {
					var size = data.length;
					$("#flowUserId").empty();// 设置之前加载数据为空
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
				success : function(data) {
					var size = data.length;
					console.log(size);
					$("#toUserId").empty();// 设置之前加载数据为空
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
			vm.add = true;
			vm.title = "新增";
			vm.cahgJob = {
				urgentStatus : 0
			};
			$("#content").val("");
			vm.resetDeptList();
			// 获取部门信息
//			this.getDeptList();
		},
		update : function(event) {
			var jobId = getSelectedRow();
			if (jobId == null) {
				return;
			}
			vm.showList = false;
			
			vm.title = "修改";

			vm.getInfo(jobId);
			
			// 获取部门信息
//			this.getDeptList();
		},
		finish: function(event) {
			var jobIds = getSelectedRows();
			if (jobIds == null || jobIds.length == 0) {
				return;
			}
			var grid = $("#jqGrid");
			var array = new Array();
			//校验合法性
			for (var i = 0, len = jobIds.length; i < len; i++) {
				var endTime = grid.getCell(jobIds[i],"endTime");
				if(endTime == null){
					alert("存在计划结束时间未填写，不能完结。");
					return false;
				}
				var status = grid.getCell(jobIds[i],"status");
				if(status == 2 || status == 3){
					alert("存工作已完结的工作，不能完结。");
					return false;
				}
				var detailObj = {
	                jobId: jobIds[i], 
	                endTime: endTime
				};
				array.push(detailObj);
			}
			$.ajax({
				type : "POST",
				url : "../cahgjob/finish",
				data : JSON.stringify(array),
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
		exportExcel: function(event){
			var grid = $("#jqGrid");
			var jobIds = grid.getGridParam("selarrrow");
			if (jobIds == null || jobIds.length == 0) {
				alert("至少选择一条记录");
				return false;
			}
			var ids = "";
			for (var i = 0, len = jobIds.length; i < len; i++) {
				ids += jobIds[i] +",";
			}
			window.location.href="../cahgjob/export?jobIds="+ ids;
		},
		saveOrUpdate : function(event) {
			var title = $("#title").val();
			if (title == null || title == "") {
				alert("请填写工作标题");
				return;
			}
			var deptId = $("#deptId").val();
			if (deptId == null || deptId == "") {
				alert("请选择经办科室");
				return;
			}
			var deptIds = "";
			for ( var i in deptId) {
				deptIds += deptId[i] + ";";
			}
			vm.cahgJob.deptId = deptIds;
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
				//仅待办状态下可修改
				if(vm.cahgJob.status == 0){
					vm.add = true;
				} else {
					vm.add = false;
				}
				
				vm.resetDeptList();
				
				//选择经办科室
				if(r.cahgJob.deptId != null){
					var deptId = r.cahgJob.deptId.split(";");
					var deptName = "";
					$(".fs-option").each(function(){
						for ( var i in deptId) {
							if ($(this).attr("data-value") == deptId[i]) {
								$(this).addClass("selected");
								deptName += $(this).children(".fs-option-label").text() + ", ";
							}
						}
					});
					if(deptName.length > 0 ){
						deptName = deptName.substring(0, deptName.length - 2);
					}
					$(".fs-label").attr("title",deptName);
					$(".fs-label").html(deptName);
				}
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
			vm.add = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'status' : $("#status").val()
				},
				page : page
			}).trigger("reloadGrid");
		},
		resetDeptList : function() {
			//经办科室默认全部不选中
			$(".fs-option").each(function(){
				for ( var i in deptId) {
					$(this).removeClass("selected");
				}
			});
			var deptName = " ===请选择=== ";
			$(".fs-label").attr("title",deptName);
			$(".fs-label").html(deptName);
		}
	}
});