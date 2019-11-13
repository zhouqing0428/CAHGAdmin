$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgmeetingnotice/list',
        datatype: "json",
        colModel: [			
			{ label: 'meetingNoticeId', name: 'meetingNoticeId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 80 }, 
			{ label: '发文人', name: 'author', width: 80 },
			{ label: '科室', name: 'deptName', width: 80 },
//			{ label: '会议内容', name: 'content', width: 80 }, 		
/*			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">未开始</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">会议中</span>';  
				}
				if(value =='2'){
					return '<span class="label label-success">已结束</span>';  
				}
			}   }, 	*/		
			{ label: '排序号', name: 'rank', width: 80,hidden:true }, 			
//			{ label: '发布时间', name: 'createDate', width: 80 }, 
			{ label: '开始时间', name: 'startTime', width: 80 }, 
//			{ label: '结束时间', name: 'endTime', width: 80 }, 		
//			{ label: '', name: 'meetingRootId', width: 80 },	
			{ label: '置顶: 0 置顶', name: 'stick', width: 80,hidden:true } 
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
			UE.getEditor('editor').setContent('');  //编辑内容为空
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
			var url = vm.cahgMeetingNotice.meetingNoticeId == null ? "../cahgmeetingnotice/save" : "../cahgmeetingnotice/update";
		    var content=UE.getEditor('editor').getContent();  //获取内容
		    vm.cahgMeetingNotice.content=content;
			vm.cahgMeetingNotice.startTime=$("#startTime").val();
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
				UE.getEditor('editor').setContent(r.cahgMeetingNotice.content)  //回显编辑内容
                vm.cahgMeetingNotice = r.cahgMeetingNotice;
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