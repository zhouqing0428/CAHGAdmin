$(function () {
    $("#jqGrid").jqGrid({
        url: '../sysapplyinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'applyInfoId', name: 'applyInfoId', width: 50,hidden:true, key: true },
			{ label: '活动名称', name: 'title', width: 50 },
			{ label: '姓名', name: 'name', width: 80 }, 			
			{ label: '手机号码', name: 'moblie', width: 80 },
			{ label: '报名时间', name: 'applyTime', width: 80,hidden:true }, 	
			{ label: '缴费时间', name: 'payTime', width: 80 }, 			
			{ label: '审核状态', name: 'checkState', width: 50,formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">未审核</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">审核失败</span>';  
				}
				if(value =='2'){
					return '<span class="label label-success">审核通过</span>';
				}
			} }, 			
			{ label: '付款状态', name: 'payState', width: 50,formatter:function(value, options, row){
				if(value == 0){
					return '<span class="label label-primary">未付款</span>';  
				}
				if(value == 1){
					return '<span class="label label-danger">已提交</span>';  
				}
				if(value == 2){
					return '<span class="label label-success">已确认</span>';
				}
			}},
			{ label: '活动费用', name: 'expense', width: 50 }, 	
			{ label: '短信状态', name: 'smsState', width: 50,hidden:true }, 			
			{ label: '备注', name: 'note', width: 80 }, 			
			{ label: '付款凭证', name: 'payImg', width: 60,formatter: function(value, options, row){
				return '<button  class="btn btn-primary btn-xs" onclick="payImg(\''+value+'\')">查看</button>';
			} }, 			
			{ label: '活动id', name: 'activityId', width: 50,hidden:true,hidden:true },
			{ label: '自定义信息', name: 'applyInfoId', width: 60, formatter: function(value, options, row){
				return '<button  class="btn btn-primary btn-xs" onclick="difiInfo('+value+')" >查看</button>';
			} }	,
			{ label: '代理商信息', name: 'bsUserId', width: 50, formatter: function(value, options, row){
				return '<button  class="btn btn-primary btn-xs" onclick="buserInfo(\''+value+'\')" >查看</button>';
			} }			
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
    
    /**
     * 查询所有活动标题
     */
    $(function(){
    	$.ajax({
    			type: "POST",
    		    url: "../sysactivity/activityList",
    		    dataType: "json",
    		    success: function(data){
    		    	//console.log("data="+data);
				  	var size = data.length;
				  	for(var i=0;i<size;i++){
				  		var activity = data[i];
				  		//console.log("id="+activity.activityId+"  title="+activity.title);
				  		var $option = $("<option value="+activity.activityId+">"+activity.title+"</option>");
				  		$("#activity_id").append($option);
				  	}
    			}
    		});
    })
   
    
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			moblie: null,
			name: null
		},
		showList: true,
		title: null,
		sysApplyInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysApplyInfo = {};
		},
		update: function (event) {
			var applyInfoId = getSelectedRow();
			if(applyInfoId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(applyInfoId)
		},
		saveOrUpdate: function (event) {
			var url = vm.sysApplyInfo.applyInfoId == null ? "../sysapplyinfo/save" : "../sysapplyinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.sysApplyInfo),
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
			var applyInfoIds = getSelectedRows();
			if(applyInfoIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sysapplyinfo/delete",
				    data: JSON.stringify(applyInfoIds),
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
		
		//审核通过
		approved: function (event) {
			var bsUserIds = getSelectedRows();
			if(bsUserIds == null){
				return ;
			}
				$.ajax({
					type: "POST",
				    url: "../sysapplyinfo/approved",
				    data: JSON.stringify(bsUserIds),
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
		
		//审核失败
		auditFailure: function (event) {
			var bsUserIds = getSelectedRows();
			if(bsUserIds == null){
				return ;
			}
			confirm('确定要审核失败选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sysapplyinfo/auditFailure",
				    data: JSON.stringify(bsUserIds),
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
		
		//确认付款
		paid: function (event) {
			var bsUserIds = getSelectedRows();
			if(bsUserIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../sysapplyinfo/paid",
			    data: JSON.stringify(bsUserIds),
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
		
		//导出
		exports: function (event) {
			var activity_id = $("#activity_id").val();
			var mobile = $("#mobile").val();
			var name =$("#name").val();
		    var check_state=$("#check_state").val();
		    var pay_state=$("#pay_state").val();
		   // alert("name="+name+" mobile="+mobile);
		   // alert("activity_id="+activity_id+" check_state="+check_state);
		    //alert("pay_state="+pay_state);
			location.href = "../sysapplyinfo/exports?activity_id="+activity_id+"&mobile="+mobile+"&name="+name+"&check_state="+check_state+"&pay_state"+pay_state ;
		},
		
		
		getInfo: function(applyInfoId){
			$.get("../sysapplyinfo/info/"+applyInfoId, function(r){
                vm.sysApplyInfo = r.sysApplyInfo;
            });
		},
		//重新加载
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'activity_id':$("#activity_id").val(),
					  'mobile':vm.q.mobile,
					  'name': vm.q.name,
				      'check_state':$("#check_state").val(),
				      'pay_state':$("#pay_state").val()
				      },
                page:page
            }).trigger("reloadGrid");
		}
	}
});