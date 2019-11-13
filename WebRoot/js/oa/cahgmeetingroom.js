$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgmeetingroom/list',
        datatype: "json",
        colModel: [			
			{ label: 'meetingRoomId', name: 'meetingRoomId', width: 50, key: true,hidden:true },
			{ label: '编号', name: 'num', width: 80 }, 			
			{ label: '名称', name: 'name', width: 80 }, 	
			{ label: '状态', name: 'status', width: 80,formatter:function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-success">非占用</span>';
				}
				if (value == 1) {
					return '<span class="label label-danger">占用</span>';
				}
			}  },	
			{ label: '申请科室', name: 'deptName', width: 80 },	
			{ label: '结束时间', name: 'endTime', width: 80 },		
			{ label: 'deptId', name: 'deptId', width: 80,hidden:true },			
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
		cahgMeetingRoom: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			//获取部门信息
			this.getDeptList();
			vm.cahgMeetingRoom = {status:0};
		},
		update: function (event) {
			var meetingRoomId = getSelectedRow();
			if(meetingRoomId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
        	//获取部门信息
			this.getDeptList();
            vm.getInfo(meetingRoomId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgMeetingRoom.meetingRoomId == null ? "../cahgmeetingroom/save" : "../cahgmeetingroom/update";
			vm.cahgMeetingRoom.deptId=$("#deptId").val();
			vm.cahgMeetingRoom.endTime=$("#endTime").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgMeetingRoom),
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
			var meetingRoomIds = getSelectedRows();
			if(meetingRoomIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgmeetingroom/delete",
				    data: JSON.stringify(meetingRoomIds),
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
		//可用,非占用状态
		usable:function(event){
			var meetingRoomIds = getSelectedRows();
			if(meetingRoomIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgmeetingroom/usable",
			    data: JSON.stringify(meetingRoomIds),
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
		},
		//不可用,占用状态
		unusable:function(event){
			var meetingRoomIds = getSelectedRows();
			if(meetingRoomIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgmeetingroom/unusable",
			    data: JSON.stringify(meetingRoomIds),
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
		},
		getInfo: function(meetingRoomId){
			$.get("../cahgmeetingroom/info/"+meetingRoomId, function(r){
                vm.cahgMeetingRoom = r.cahgMeetingRoom;
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
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