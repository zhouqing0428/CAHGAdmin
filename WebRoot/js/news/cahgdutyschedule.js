$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgdutyschedule/list',
        datatype: "json",
        colModel: [			
			{ label: 'dutyScheduleId', name: 'dutyScheduleId', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'name', width: 80 }, 			
			{ label: '办公内线', name: 'interior', width: 80 }, 			
			{ label: '办公外线', name: 'external', width: 80 }, 
			{ label: '手机号码', name: 'mobile', width: 80 }, 	
			{ label: '值班日期', name: 'workTime', width: 80,formatter:'date',
				formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d'} }, 			
			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}  }, 			
//			{ label: '', name: 'createDate', width: 80 }, 			
			{ label: '类别', name: 'category', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">一般干部</span>';  
				}
				if(value =='1'){
					return '<span class="label label-success">领导</span>';  
				}
			}  },	
			{ label: '备注', name: 'remark', width: 80 }
        ],
		viewrecords: true,
        height: 420,
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
		q:{
			mobile: "",
            name: ""
		},
		cahgDutySchedule: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgDutySchedule = {status:0,category:0};
		},
		update: function (event) {
			var dutyScheduleId = getSelectedRow();
			if(dutyScheduleId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(dutyScheduleId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgDutySchedule.dutyScheduleId == null ? "../cahgdutyschedule/save" : "../cahgdutyschedule/update";
			vm.cahgDutySchedule.workTime=$("#workTime").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgDutySchedule),
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
			var dutyScheduleIds = getSelectedRows();
			if(dutyScheduleIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgdutyschedule/delete",
				    data: JSON.stringify(dutyScheduleIds),
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
		//导入
		imports: function (event) {
			if($("#excle").val()=="" || document.getElementById("excle").files[0] =='请选择xls格式的文件'){
				alert("请先选择xls或者xlsx格式文件");
				return false;
			}
			$.ajaxFileUpload({
				type: "POST",
				fileElementId: 'excle', //文件上传域的ID
			    url: "../cahgdutyschedule/imports",
			    success: function(r){
					if(r.code == 0){
						alert('导入成功', function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		//导出
		exports:function(){
			window.location.href="../cahgdutyschedule/exports?mobile="+vm.q.mobile+"&name="+vm.q.name+"&category="+$("#category").val()+"&status="+status
		} ,
		getInfo: function(dutyScheduleId){
			$.get("../cahgdutyschedule/info/"+dutyScheduleId, function(r){
                vm.cahgDutySchedule = r.cahgDutySchedule;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
					  'mobile':vm.q.mobile,
					  'name': vm.q.name,
				      'category':$("#category").val(),
				      'status':$("#status").val(),
				      'startDate': $("#startDate").val(),
				      'endDate':$("#endDate").val()
				      },
                page:page
            }).trigger("reloadGrid");
		}
	}
});