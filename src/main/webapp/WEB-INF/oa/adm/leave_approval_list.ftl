<#--请假条审批列表-->
<#include "ftl/comm.ftl"/>
<#macro approval_list type title>
<script type="text/javascript">
<!--
$(function(){
//dg
	var dg = $("#${dg}").datagrid({
		url:"<@s.url '/adm/leave/approval/${type}/datagrid'/>",
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"startTime",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		}
		,{
			title:"所属部门",
			field:"deptName",
			width:30,
			align:"left",
			sortable:true
		},{
			title:"姓名",
			field:"employeeName",
			width:20,
			align:"left",
			sortable:true
		},{
			title:"请假类型",
			field:"typeName",
			width:15,
			align:"center",
			sortable:true
		},{
			title:"开始时间",
			field:"startTime",
			width:20,
			align:"center",
			sortable:true
		},{
			title:"结束时间",
			field:"endTime",
			width:20,
			align:"center",
			sortable:true
		},{
			title:"状态",
			field:"statusName",
			width:20,
			align:"center",
			sortable:true
		},{
			title:"结果",
			field:"resultName",
			width:15,
			align:"center",
			sortable:true
		}]],
		toolbar:"#${dg}_toobar",
		onLoadError:function(e){
			<@error_dialog "e"/>
		},
		onDblClickRow:function(rowIndex,rowData){
			edit_window("${title}",rowIndex,rowData);
		}
	});
	//edit
	function edit_window(title,index,row){
		var btns =[];
		<@shiro.hasPermission name="${PER_UPDATE}">
		if(row.status == ${CURRENT_STATUS_VALUE} && row.result == ${CURRENT_RESULT_VALUE}){
			btns.push({
				text:"审批",
				iconCls:"icon-save",
				handler:function(){
					$.messager.confirm("确认","您是否确认审核请假条?",function(r){
						if(!r)return;
						$.messager.progress();
						var post = $("#${module}_edit_form").form("serialize");
						if(!post.result){
							$.messager.progress("close");
							$.messager.alert("提示","请选择审核结果！");
							return;
						}
						$.ajax({
							url:"<@s.url '/adm/leave/approval/${type}/update'/>",
							type:"POST",
							data:post,
							dataType:"json",
							error:function(e){
								$.messager.progress("close");
								<@error_dialog "e"/>
							},
							success:function(data,textStatus){
								$.messager.progress("close");
								if(data.success){
									dg.datagrid(row ? "updateRow" : "insertRow",{
										index:index,
										row:data.data
									});
									d.dialog("close");
								}else{
									$.messager.show({
										title:"保存异常",
										msg:data.msg
									});
								}
							}
						});
					});
				}
			});
		}
		</@shiro.hasPermission>
		btns.push({
			text:"关闭",
			iconCls:"icon-cancel",
			handler:function(){
				d.dialog("close");
			}
		});
		var d = $("<div/>").dialog({
			title:title,
			width:620,
			height:520,
			href:"<@s.url '/adm/leave/approval/${type}/edit?id='/>"+row.id,
			modal:true,
			buttons:btns,
			onClose:function(){
				$(this).dialog("destroy");
			},
			onLoad:function(){
				if(row) $("#${module}_edit_form").form("load",row);
			}
	  });
	};
	//search
	${dg}_search = function(){
		var st = $("#${dg}_toobar input[name='startTime']").val(),et = $("#${dg}_toobar input[name='endTime']").val();
		if(st && $.trim(st) !=""){ st += " 00:00:00";}
		if(et && $.trim(et) !=""){ et += " 00:00:00";}
		dg.datagrid("load",{
			startTime:st,
			endTime:et
		});
	};
});
//-->
</script>
<table id="${dg}"></table>
<div id="${dg}_toobar">
	<label style="margin-left:10px;">开始日期：<input name="startTime" type="text" class="easyui-datebox" style="width:128px;"/>－<input name="endTime" type="text" class="easyui-datebox" style="width:128px;"/></label>
	<a href="#" class="easyui-linkbutton" style="margin-left:10px;"  onclick="${dg}_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>
</#macro>