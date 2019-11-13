$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgmeetinghis/list',
        datatype: "json",
        colModel: [			
			{ label: 'meetingHisId', name: 'meetingHisId', width: 50, key: true,hidden:true },
		//	{ label: '', name: 'mettingRoomId', width: 80 }, 
			{ label: '会议室', name: 'roomName', width: 80 }, 
			{ label: '开始时间', name: 'startTime', width: 80 }, 			
			{ label: '结束时间', name: 'endTime', width: 80 }, 			
		//	{ label: '', name: 'deptId', width: 80 },
			{ label: '申请科室', name: 'deptName', width: 80 },	
			{ label: '备注', name: 'remark', width: 80 } 		
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
		roomList:[],
		cahgMeetingHis: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			this.getDeptList();
			this.getMeetingRoomList();
			vm.cahgMeetingHis = {};
		},
		update: function (event) {
			var meetingHisId = getSelectedRow();
			if(meetingHisId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            this.getDeptList();
            vm.getInfo(meetingHisId);
            this.getMeetingRoomList();
		},
		saveOrUpdate: function (event) {
			var starTime=$("#startTime").val();
			var endTime = $("#endTime").val();
			if(starTime==null||starTime==undefined||starTime==""){
				alert("会议开始时间必须填写");
				return ;
			}
			if(endTime==null||endTime==undefined||endTime==""){
				alert("会议结束时间必须填写");
				return ;
			}
			vm.cahgMeetingHis.deptId=$("#deptId").val();
			vm.cahgMeetingHis.meetingRoomId=$("#meetingRoomId").val();
			vm.cahgMeetingHis.startTime=starTime;
			vm.cahgMeetingHis.endTime=endTime;
			var url = vm.cahgMeetingHis.meetingHisId == null ? "../cahgmeetinghis/save" : "../cahgmeetinghis/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgMeetingHis),
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
			var meetingHisIds = getSelectedRows();
			if(meetingHisIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgmeetinghis/delete",
				    data: JSON.stringify(meetingHisIds),
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
		getInfo: function(meetingHisId){
			$.get("../cahgmeetinghis/info/"+meetingHisId, function(r){
                vm.cahgMeetingHis = r.cahgMeetingHis;
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		getMeetingRoomList: function(){
			$.get("../cahgmeetingroom/selectList", function(r){
				vm.roomList = r.list;
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