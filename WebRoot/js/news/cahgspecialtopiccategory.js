$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgspecialtopiccategory/list',
        datatype: "json",
        colModel: [			
			{ label: 'specialTopicCategoryId', name: 'specialTopicCategoryId', width: 50, key: true,hidden:true },
			{ label: '专题类别名称', name: 'name', width: 80 }, 			
			{ label: '备注', name: 'remark', width: 80 },
			{ label: '排序', name: 'rank', width: 40 }, 	
			{ label: '状态', name: 'status', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">显示</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">隐藏</span>';  
				}
			}   },
			{ label: '标题图片', name: 'imgName', width: 80,formatter:function(value, options, row){
				return '<img class="img-responsive" src="/file/upImg/topiCategory/'+value+'">';} }
        ],
		viewrecords: true,
        height: 536,
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
		tips: false,
		title: null,
		cahgSpecialTopicCategory: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.tips=false;
			vm.title = "新增";
			$("#img").val("");
			$("#imgName").val("");
			vm.cahgSpecialTopicCategory = {status:0};
		},
		update: function (event) {
			var specialTopicCategoryId = getSelectedRow();
			if(specialTopicCategoryId == null){
				return ;
			}
			$("#img").val("");
			$("#imgName").val("");
			vm.showList = false;
			vm.tips=false;
            vm.title = "修改";
            
            vm.getInfo(specialTopicCategoryId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgSpecialTopicCategory.specialTopicCategoryId == null ? "../cahgspecialtopiccategory/save" : "../cahgspecialtopiccategory/update";
			vm.cahgSpecialTopicCategory.imgName=$("#imgName").val();
			vm.cahgSpecialTopicCategory.rank=$("#rank").val();  //避免vue序列化出错
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgSpecialTopicCategory),
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
			var specialTopicCategoryIds = getSelectedRows();
			if(specialTopicCategoryIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgspecialtopiccategory/delete",
				    data: JSON.stringify(specialTopicCategoryIds),
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
		getInfo: function(specialTopicCategoryId){
			$.get("../cahgspecialtopiccategory/info/"+specialTopicCategoryId, function(r){
                vm.cahgSpecialTopicCategory = r.cahgSpecialTopicCategory;
                $("#imgName").val(vm.cahgSpecialTopicCategory.imgName);
            });
		},
		upImg: function(){
			if($("#img").val()==null||$("#img").val()==''){
				alert("选择图片才能上传");
				return ;
			}
			 $.ajaxFileUpload({
				type: "POST",
				fileElementId: 'img', //文件上传的ID
			    url: "../cahgspecialtopiccategory/upImg",
			    async: false,
			    success: function(name){
				 	if(name!="err"){
				 		vm.tips=true;
						var path="../upImg/topiCategory/"+name;
						$("#imgName").val(name);
			            $("#showImg").attr('src',path); 
					}else{
						alert("上传图片失败");
					} 
				}
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