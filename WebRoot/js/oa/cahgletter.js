$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgletter/list',
        datatype: "json",
        colModel: [			
			{ label: 'letterId', name: 'letterId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 80 }, 			
			{ label: '内容', name: 'letterId', width: 80 , formatter: function(value, options, row){
				return  '<button  class="btn btn-primary btn-xs" onclick="detail(\''+value+'\')" >查看</button>';
			} },		
			{ label: '写信人', name: 'author', width: 80 },
			{ label: '部门', name: 'deptName', width: 80 }, 	
			{ label: '职务', name: 'dutyName', width: 80 }, 	
			{ label: '时间', name: 'createDate', width: 80 },
			{ label: '状态', name: 'status', width: 40,formatter:function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-primary"> 未读</span>';
				}
				if (value == 1) {
					return '<span class="label label-success"> 已读</span>';
				}
			} },
			{ label: '回信', name: 'isReply', width: 40,formatter:function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-primary"> 未回</span>';
				}
				if (value == 1) {
					return '<span class="label label-success"> 已回</span>';
				}
			} },
			{ label: '显示/隐藏', name: 'hidden', width: 50,formatter:function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-primary"> 显示</span>';
				}
				if (value == 1) {
					return '<span class="label label-danger"> 隐藏</span>';
				}
			} }
//			{ label: '', name: 'createUserId', width: 80 }, 			
	//		{ label: '给用户id', name: 'toUserId', width: 80 }			
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
    
    $("#deptId").change(function(){  //选择部门后执行
        vm.user.deptId="";
        if(jQuery.isNumeric($("#deptId").val())){
     	   vm.getUserList($("#deptId").val());
        }
     });
  
});

function detail(letterId){
	UE.getEditor('editor').setContent('');  //编辑内容为空
	vm.showList = false;
	vm.detail = true;
    vm.getInfo(letterId)
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		detail: false,
		letterFlow: false,
		title: null,
		deptList:[],
		user:{},
		cahgLetter: {},  //信封信息
        cahgReplyLetter: {},  //回信信息
        cahgLetterFlow: {}  //信封流转信息
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getUserList: function (deptId){
			vm.user.deptId=deptId
		    $.ajax({
				type: "POST",
			    url: "../sys/user/selectList",
			    data: JSON.stringify(vm.user),
			    //dataType: "json",
			    success: function(data){
				  	var size = data.length;
					console.log(size);
					$("#flowUserId").empty();//设置之前加载数据为空
					//$("#userId").append("<option>"+"--请选择流转人--"+"<option>");
				  	for(var i=0;i<size;i++){
				  		var user = data[i];
				  		var $option = $("<option value="+user.userId+">"+user.name+"</option>");
				  		$("#flowUserId").append($option);
				  	}
				}
			});
		},
	/*	add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgLetter = {};
		},
		update: function (event) {
			var letterId = getSelectedRow();
			if(letterId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(letterId)
		},*/
		//信封转交页面
		flow: function(event){
			var letterId = getSelectedRow();
			if(letterId == null){
				return ;
			}
			vm.showList = false;
			vm.detail = false;
			vm.letterFlow = true;
			vm.cahgLetterFlow ={};
			var letter = $("#jqGrid").jqGrid("getRowData",letterId) //获取选择行的数据
			vm.cahgLetterFlow.letterId=letterId;
			vm.cahgLetterFlow.letterTitle=letter.title;
			//获取部门信息
			this.getDeptList();
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgLetter.letterId == null ? "../cahgletter/save" : "../cahgletter/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgLetter),
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
		saveReplyLetter: function(event){
			vm.cahgReplyLetter.letterId=vm.cahgLetter.letterId;
			var content=content=UE.getEditor('editor').getContent(); 
			if(content==null){
				alert("回信内容不能为空");
				return ;
			}
			vm.cahgReplyLetter.content=content;
			$.ajax({
				type:"POST",
				url:"../cahgletter/saveReplyLetter",
				data:JSON.stringify(vm.cahgReplyLetter),
				success: function(r){
				    	if(r.code === 0){
				    		vm.cahgLetter = {};
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
			})
		},
		//添加信封转交
		saveLetterFlow: function(event){
			var flowUserId=$("#flowUserId").val();
			if(flowUserId == null|| flowUserId == ""){
				alert("请选择转交人");
				return ;
			}
			vm.cahgLetterFlow.userId=flowUserId;  //转交给用户的ID
			var url = vm.cahgLetterFlow.letterFlowId == null ? "../cahgletterflow/save" : "../cahgletterflow/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgLetterFlow),
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
			var letterIds = getSelectedRows();
			if(letterIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgletter/delete",
				    data: JSON.stringify(letterIds),
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
		show: function(event){
			var letterIds = getSelectedRows();
			if(letterIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgletter/show",
			    data: JSON.stringify(letterIds),
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
		},
		hide: function(event){
			var letterIds = getSelectedRows();
			if(letterIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgletter/hide",
			    data: JSON.stringify(letterIds),
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
		},
		//设为已读
		hadRead: function(){
			var letterIds = getSelectedRows();
			if(letterIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../cahgletter/hadRead",
			    data: JSON.stringify(letterIds),
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
		getInfo: function(letterId){
			$.get("../cahgletter/info/"+letterId, function(r){
				$("#content").html(r.cahgLetter.content);
                vm.cahgLetter = r.cahgLetter;
             
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
			vm.detail = false;
			vm.letterFlow = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});