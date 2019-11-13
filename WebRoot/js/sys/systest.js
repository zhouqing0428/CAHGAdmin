$(function () {
    $("#jqGrid").jqGrid({
        url: '../systest/list',
        datatype: "json",
        colModel: [			
			{ label: 'testId', name: 'testId', width: 50, key: true },
			{ label: '测试名', name: 'name', width: 80 }	,
			{ label: '自定义信息', name: 'testId', width: 80, formatter: function(value, options, row){
				//return '<span class="label label-primary">查看</span>';
				return '<button  onclick="test('+value+')" >查看</button>';
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		sysTest: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysTest = {};
		},
		update: function (event) {
			var testId = getSelectedRow();
			/*alert("testId"+testId);*/
			if(testId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(testId)
		},
		saveOrUpdate: function (event) {
			var url = vm.sysTest.testId == null ? "../systest/save" : "../systest/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.sysTest),
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
			var testIds = getSelectedRows();
			if(testIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../systest/delete",
				    data: JSON.stringify(testIds),
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
		
		//导入
		imports: function (event) {
			if($("#excle").val()=="" || document.getElementById("excle").files[0] =='请选择xls格式的文件'){
				alert("请先选择xls或者xlsx格式文件");
				return false;
			}
			$.ajaxFileUpload({
				type: "POST",
				fileElementId: 'excle', //文件上传域的ID
			    url: "../systest/imports",
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
		
		//自定义测试
		test: function(){
			alert("test");
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#testdLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					var data = "password="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
					    url: "sys/user/password",
					    data: data,
					    dataType: "json",
					    success: function(result){
							if(result.code == 0){
								layer.close(index);
								layer.alert('修改成功', function(index){
									location.reload();
								});
							}else{
								layer.alert(result.msg);
							}
						}
					});
	            }
			});
		},
		
		getInfo: function(testId){
			$.get("../systest/info/"+testId, function(r){
                vm.sysTest = r.sysTest;
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