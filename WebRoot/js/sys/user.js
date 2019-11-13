$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'userId', width: 45, key: true,hidden:true },
			{ label: '登录名', name: 'username', width: 65 },
			{ label: '姓名', name: 'name', width: 65 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '备注', name: 'remark', width: 65 },
			{ label: '简介', name: 'intro', width: 100 },
			{ label: '所属科室', name: 'deptName', width: 75 },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '关长信箱', name: 'isLetterLeader', width: 45, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-primary">否</span>' : 
					'<span class="label label-success">是</span>';
			}},
		//	{ label: '创建时间', name: 'createTime', width: 80,hidden:true},
			{ label: '照片', name: 'photo', width: 45,formatter:function(value, options, row){
				return '<img class="img-responsive" src="/file/upImg/userPhoto/'+value+'">';} }
				//{ label: '添加人id', name: 'createUserName', width: 45 },
				//{ label: '添加时间', name: 'createDate', width: 60 },
				//{ label: '最后修改人id', name: 'lastUpdateName', width: 45 },
				//{ label: '最后修改时间', name: 'lastUpdateDate', width: 60 }
        ],
		viewrecords: true,
        height: 485,
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
        }/*,
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }*/
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			username: null
		},
		showList: true,
		tips:false,
		title:null,
		roleList:{},
		deptList:[],
		user:{
			status:1,
			roleIdList:[]
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips =false;
			vm.title = "新增";
			$("#photoFile").val("");
			$("#photo").val("");
			vm.roleList = {};
			vm.user = {status:1,roleIdList:[]};
			
			//获取角色信息
			this.getRoleList();
			//获取部门信息
			this.getDeptList();
		},
		update: function () {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			$("#photoFile").val("");
			vm.showList = false;
			vm.tips =false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
			   //获取部门信息
			this.getDeptList();
		},
		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/user/delete",
				    data: JSON.stringify(userIds),
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
		//设为主任信箱
		setLetterLeader: function(event){
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			};
//			var id=$("#jqGrid").jqGrid("getGridParam","selrow"); //获取选择的id
			var user = $("#jqGrid").jqGrid("getRowData",userId) //获取选择行的数据
			if(user.isLetterLeader=='<span class="label label-success">是</span>'){
				alert("已经是信箱主任了,无需设置");
				return ;
			}
			$.get("../sys/user/setLetterLeader/"+userId, function(r){
				if(r.code == 0){
					alert('操作成功', function(index){
						$("#jqGrid").trigger("reloadGrid");
					});
				}else{
					alert(r.msg);
				}
			});
		},
		//撤销信箱主任
		canselLetterLeader:function(event){
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			confirm('确定要撤销选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/user/canselLetterLeader",
				    data: JSON.stringify(userIds),
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
		saveOrUpdate: function (event) {
			var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
		    vm.user.deptId=$("#deptId").val();
		    vm.user.photo=$("#photo").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.user),
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
		getUser: function(userId){
			$.get("../sys/user/info/"+userId, function(r){
				console.log(r.user.name);
				vm.user = r.user;
				$("#photo").val(vm.user.photo);
			});
		},
		getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
	    upImg: function(){
		  if($("#photoFile").val()==null||$("#photoFile").val()==''){
				alert("选择图片才能上传");
				return ;
			}
		   $.ajaxFileUpload({
					type: "POST",
					fileElementId: 'photoFile', //文件上传的ID
				    url: "../sys/user/upImg",
				    async: false,
				    success: function(name){
					 	if(name!="err"){
					 		vm.tips = true;
							var path="../upImg/userPhoto/"+name;
							$("#photo").val(name);
				            $("#showImg").attr('src',path); 
						}else{
							alert("上传图片失败");
						} 
					}
			}); z
			},
		
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		}
	}
});