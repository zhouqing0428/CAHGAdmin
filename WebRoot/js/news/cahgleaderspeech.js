$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgleaderspeech/list',
        datatype: "json",
        colModel: [			
			{ label: 'leaderSpeechId', name: 'leaderSpeechId', width: 40, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 120 }, 			
//			{ label: '内容', name: 'content', width: 80 }, 
			{ label: '添加人id', name: 'createUserName', width: 80 },
			{ label: '发布时间', name: 'createDate', width: 50 }, 		
			{ label: '备注', name: 'remark', width: 80 }, 	
			{ label: '是否上传附件', name: 'fileName', width: 40,formatter : function(value, options, row) {
				if (value == null|| value == undefined || value == '') {
					return '<span class="label label-warning">否</span>';
				}
				else{
					return '<span class="label label-primary">是</span>';
				}
			} },
//			{ label: '创建人', name: 'createUserId', width: 80 }, 			
			{ label: '最后修改人', name: 'lastUpdateName', width: 80 }, 			
			{ label: '最后修改时间', name: 'lastUpdateDate', width: 80 }, 	
			{ label: '类别', name: 'type', width: 40,formatter : function(value, options, row) {
				if (value == '本办领导') {
					return '<span class="label label-primary">本关领导</span>';
				}
				else{
					return '<span class="label label-success">总关领导</span>';
				}
			}  },
			{ label: '状态', name: 'status', width: 40,formatter : function(value, options, row) {
				if (value == 0) {
					return '<span class="label label-primary">显示</span>';
				}
				else{
					return '<span class="label label-warning">隐藏</span>';
				}
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
		tips : false,
		title: null,
		cahgLeaderSpeech: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tips = false;
			$("#file").val('');
			$("#fileName").val('');
			$("#fileOldName").val('');
			UE.getEditor('editor').setContent('');  //编辑内容为空
			vm.cahgLeaderSpeech = {status:0,type:'本办领导'};
		},
		update: function (event) {
			var leaderSpeechId = getSelectedRow();
			if(leaderSpeechId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.tips = false;
            $("#file").val('');
            $("#fileName").val('');
            $("#fileOldName").val('');
            vm.getInfo(leaderSpeechId)
		},
		saveOrUpdate: function (event) {
			var content=UE.getEditor('editor').getContent();  //内容
			vm.cahgLeaderSpeech.content=content;
			vm.cahgLeaderSpeech.fileName=$("#fileName").val();
			vm.cahgLeaderSpeech.fileOldName=$("#fileOldName").val();
			var url = vm.cahgLeaderSpeech.leaderSpeechId == null ? "../cahgleaderspeech/save" : "../cahgleaderspeech/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgLeaderSpeech),
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
			var leaderSpeechIds = getSelectedRows();
			if(leaderSpeechIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgleaderspeech/delete",
				    data: JSON.stringify(leaderSpeechIds),
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
		upfile: function(){
			if($("#file").val()==null||$("#file").val()==''){
				alert("选择文件才能上传");
				return ;
			}
			var file=$("#file").val();
			$.ajaxFileUpload({
					type: "POST",
					fileElementId: 'file', //文件上传的ID
				    url: "../cahgleaderspeech/upFile",
				    async: false,
				    success: function(name){
				    	console.log(name);
					 	if(name!="err"){
					 		$("#fileName").val(name);
					 		$("#fileOldName").val(vm.getFileName(file));  //附件原名
					 		vm.tips = true;
							alert("上传成功");
						}else{
							vm.tips = false;
							alert("上传失败");
						} 
					}
			}); 
		},
		getFileName: function(o){
			var pos=o.lastIndexOf("\\");
			return o.substring(pos+1);  
		},
		
		getInfo: function(leaderSpeechId){
			$.get("../cahgleaderspeech/info/"+leaderSpeechId, function(r){
				vm.cahgLeaderSpeech = r.cahgLeaderSpeech;
				UE.getEditor('editor').setContent(r.cahgLeaderSpeech.content);  //回显编辑内容 
				$("#fileName").val(r.cahgLeaderSpeech.fileName);
				$("#fileOldName").val(r.cahgLeaderSpeech.fileOldName);
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