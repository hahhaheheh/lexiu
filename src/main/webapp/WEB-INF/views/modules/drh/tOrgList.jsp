<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/drh/tOrg/">机构信息列表</a></li>
		<shiro:hasPermission name="drh:tOrg:edit"><li><a href="${ctx}/drh/tOrg/form">机构信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tOrg" action="${ctx}/drh/tOrg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>tags：</label>
				<form:input path="tags" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>adress：</label>
				<form:input path="adress" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>经度：</label>
				<form:input path="longitude" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>纬度：</label>
				<form:input path="latitude" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>星级：</label>
				<form:input path="startlevel" htmlEscape="false" maxlength="5" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>tags</th>
				<th>adress</th>
				<th>经度</th>
				<th>纬度</th>
				<th>封面图</th>
				<th>星级</th>
				<shiro:hasPermission name="drh:tOrg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tOrg">
			<tr>
				<td><a href="${ctx}/drh/tOrg/form?id=${tOrg.id}">
					${tOrg.title}
				</a></td>
				<td>
					${tOrg.tags}
				</td>
				<td>
					${tOrg.adress}
				</td>
				<td>
					${tOrg.longitude}
				</td>
				<td>
					${tOrg.latitude}
				</td>
				<td>
					${tOrg.imageurl}
				</td>
				<td>
					${tOrg.startlevel}
				</td>
				<shiro:hasPermission name="drh:tOrg:edit"><td>
    				<a href="${ctx}/drh/tOrg/form?id=${tOrg.id}">修改</a>
					<a href="${ctx}/drh/tOrg/delete?id=${tOrg.id}" onclick="return confirmx('确认要删除该机构信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>