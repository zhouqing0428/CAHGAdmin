$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgmeetingnotice/list',
        datatype: "json",
        colModel: [			
			{ label: 'meetingNoticeId', name: 'meetingNoticeId', width: 50, key: true,hidden:true },
			{ label: '会议名称', name: 'title', width: 140 }, 
			{ label: '会议时间', name: 'meetingTime', width: 80 }, 
			{ label: '会议地点', name: 'meetingPlace', width: 80 },
			{ label: '联系人', name: 'meetingContact', width: 80 },
			{ label: '发文人', name: 'author', width: 80 },
			{ label: '发文科室', name: 'deptName', width: 80},
			{ label: '发表时间', name: 'startTime', width: 80 }
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
		cahgMeetingNotice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgMeetingNotice = {status:0};
			this.getDeptList();
		},
		update: function (event) {
			var meetingNoticeId = getSelectedRow();
			if(meetingNoticeId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
        	this.getDeptList();
            vm.getInfo(meetingNoticeId)
		},
		saveOrUpdate: function (event) {
			
			var title = $("#title").val();
			if(title == null || title == ""){
				alert("请填写会议名称");
				return;
			}
			
			var meetingTime = $("#meetingTime").val();
			if(meetingTime == null || meetingTime == ""){
				alert("请填写会议时间");
				return;
			}
			
			var meetingPlace = $("#meetingPlace").val();
			if(meetingPlace == null || meetingPlace == ""){
				alert("请填写会议地点");
				return;
			}
			
			var meetingDep = $("#meetingDep").val();
			if(meetingDep == null || meetingDep == ""){
				alert("请填写参会部门");
				return;
			}
			
			var meetingPerson = $("#meetingPerson").val();
			if(meetingPerson == null || meetingPerson == ""){
				alert("请填写参会人员");
				return;
			}
			
			
			var meetingRequire = $("#meetingRequire").val();
			if(meetingRequire == null || meetingRequire == ""){
				alert("请填写会议要求");
				return;
			}
			
			var meetingContact = $("#meetingContact").val();
			if(meetingContact == null || meetingContact == ""){
				alert("请填写联系人");
				return;
			}
			
			
			
			
			var url = vm.cahgMeetingNotice.meetingNoticeId == null ? "../cahgmeetingnotice/save" : "../cahgmeetingnotice/update";
			vm.cahgMeetingNotice.startTime=$("#startTime").val();
			vm.cahgMeetingNotice.meetingTime=$("#meetingTime").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgMeetingNotice),
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
			var meetingNoticeIds = getSelectedRows();
			if(meetingNoticeIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgmeetingnotice/delete",
				    data: JSON.stringify(meetingNoticeIds),
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
		getInfo: function(meetingNoticeId){
			$.get("../cahgmeetingnotice/info/"+meetingNoticeId, function(r){
                vm.cahgMeetingNotice = r.cahgMeetingNotice;
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		//部门信息
		getDeptInfo: function(deptId){
			$.get("../sysdept/info/"+deptId, function(r){
				vm.deptInfo = r.sysDept;
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