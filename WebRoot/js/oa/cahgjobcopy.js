$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgjobcopy/list',
        datatype: "json",
        colModel: [			
			{ label: 'jobCopyId', name: 'jobCopyId', width: 50, key: true,hidden:true },
			{ label: '工作标题', name: 'title', width: 80 },
			{ label: '备注', name: 'remark', width: 80 },
			{ label: '抄送人', name: 'userName', width: 50 },
			{ label: '督办详细', name: 'jobId', width: 50,formatter : function(value, options, row) {
				return '<a  class="btn btn-primary btn-xs" href="../cahgjob/jobDetail?jobId='+ value + '">查看</a>';
			} }, 
			/*{ label: '状态：', name: 'status', width: 80 }, 	*/		
			/*{ label: '', name: 'createUserId', width: 80 }, 			
			{ label: '', name: 'toUserId', width: 80 }, 			
			{ label: '', name: 'jobId', width: 80 }, 	*/		
			{ label: '抄送时间', name: 'createTime', width: 50 }			
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
		cahgJobCopy: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgJobCopy = {};
		},
		update: function (event) {
			var jobCopyId = getSelectedRow();
			if(jobCopyId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(jobCopyId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgJobCopy.jobCopyId == null ? "../cahgjobcopy/save" : "../cahgjobcopy/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgJobCopy),
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
			var jobCopyIds = getSelectedRows();
			if(jobCopyIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgjobcopy/delete",
				    data: JSON.stringify(jobCopyIds),
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
		getInfo: function(jobCopyId){
			$.get("../cahgjobcopy/info/"+jobCopyId, function(r){
                vm.cahgJobCopy = r.cahgJobCopy;
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