$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgimgnews/list',
        datatype: "json",
        colModel: [			
			{ label: 'imgNewId', name: 'imgNewId', width: 50, key: true,hidden:true },
			{ label: '新闻标题', name: 'imgNewTitle', width: 120 }, 			
			{ label: '作者', name: 'author', width: 80 }, 			
			{ label: '发布时间', name: 'createDate', width: 30,formatter:'date',
				formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d'} },
			{ label: '发布科室', name: 'deptName', width: 80 },
			{ label: '最后修改时间', name: 'lastUpdateDate', width: 80,hidden:true }, 
			{ label: '状态', name: 'imgNewStatus', width: 25,formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}  },
			{ label: '总关采用', name: 'imgNewsStick', width: 25, formatter:function(value, options, row){
				if(value == '1'){
					return '是';  
				} if(value =='0'){
					return '否'; 
				}
			} },
			{ label: '标题图片', name: 'imgUrl', width: 75,formatter:function(value, options, row){
				return '<img class="img-responsive" src="/file/upImg/imgNews/'+value+'">';
			} }
        ],
		viewrecords: true,
        height: 530,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 30, 
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
		q : {
			title : null,
			author : null,
			stick: null
		},
		title: null,
		deptList:[],
		cahgImgNews: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips = false;
			vm.title = "新增";
			vm.cahgImgNews = {imgNewStatus:0};
			UE.getEditor('editor').setContent('');  //编辑内容为空
			$("#imgUrl").val("");
			$("#imgUrlName").val("");
			//获取部门信息
			this.getDeptList();
		},
		update: function (event) {
			var imgNewId = getSelectedRow();
			if(imgNewId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.tips=false;
            $("#imgUrl").val("");
			$("#imgUrlName").val("");
            this.getDeptList();
            vm.getInfo(imgNewId)
		},
		saveOrUpdate: function (event) {
			var title=$("#imgNewTitle").val();
			if(title == null || title == ""){
		    	alert("请填写图片新闻标题");
		    	return;
		    }
		    var content=UE.getEditor('editor').getContent();  //新闻内容
		    if(content == null || content == ""){
		    	alert("请填写图片新闻内容");
		    	return;
		    }
		    var deptId = $("#deptId").val();
		    if(deptId == null || deptId == ""){
		    	alert("请选择科室");
		    	return;
		    }
		    var author = $("#imgAuthor").val();
		    if(author == null || author == ""){
		    	alert("请填写作者");
		    	return;
		    }
		    var createDate = $("#createDate").val();
		    if(createDate == null || createDate == ""){
		    	alert("请选择发布时间");
		    	return;
		    }		    
		    vm.cahgImgNews.imgNewContent = content;
		    vm.cahgImgNews.deptId = deptId;
		    vm.cahgImgNews.imgUrl = $("#imgUrlName").val();
		    //总关采用
		    if ($("#imgNewsStick").is(':checked')) {
				vm.cahgImgNews.imgNewsStick = 1;
			} else {
				vm.cahgImgNews.imgNewsStick = 0;
			}
		    vm.cahgImgNews.createDate=$("#createDate").val();//时间
			var url = vm.cahgImgNews.imgNewId == null ? "../cahgimgnews/save" : "../cahgimgnews/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgImgNews),
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
			var imgNewIds = getSelectedRows();
			if(imgNewIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgimgnews/delete",
				    data: JSON.stringify(imgNewIds),
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
	
      stick	:function(){
    	  var imgNewId = getSelectedRow();
			if(imgNewId == null){
				return ;
			}
			$.get("../cahgimgnews/stick/" + imgNewId, function(r) {
				if(r.code == 0){
					alert('操作成功', function(index){
						$("#jqGrid").trigger("reloadGrid");
					});
				}else{
					alert(r.msg);
				}
			});
      },
	  upImg: function(){
		  if($("#imgUrl").val()==null||$("#imgUrl").val()==''){
				alert("选择图片才能上传");
				return ;
			}
		   $.ajaxFileUpload({
				type: "POST",
				secureuri: false, //一般设置为false
				fileElementId: 'imgUrl', //文件上传的ID
			    url: "../cahgimgnews/upImgUrl",
			    //timeout : 50000, //超时时间设置，单位毫秒
			    async: false,
			    //maxFileSize:1024*1024, //大小限制1M
		        //sizeErrorStr:"上传文件不能大于1M", 
			    success: function(name){
				 	if(name!="err"){
				 		vm.tips = true;
						var path="../upImg/imgNews/"+name;
						$("#imgUrlName").val(name);
			            $("#showImg").attr('src',path); 
					}else{
						alert("上传图片失败");
					} 
				},
	   			error: function (data, status, e) {//服务器响应失败处理函数
                   alert('上传图片失败,服务器响应失败(可能是文件过大导致)');
                }
			}); 
		},
		
		getInfo: function(imgNewId){
			$.get("../cahgimgnews/info/"+imgNewId, function(r){
			    UE.getEditor('editor').setContent(r.cahgImgNews.imgNewContent);  //回显编辑内容 
                vm.cahgImgNews = r.cahgImgNews;
			    var path="../upImg/imgNews/"+vm.cahgImgNews.imgUrl;
				$("#imgUrlName").val(vm.cahgImgNews.imgUrl);
			    $("#showImg").attr('src',path);
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../cahgimgnews/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'title' : vm.q.title,
					'author' : vm.q.author,
					'stick' : vm.q.stick
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});