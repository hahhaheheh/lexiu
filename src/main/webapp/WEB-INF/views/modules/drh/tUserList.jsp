<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息管理</title>
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
		<li class="active"><a href="${ctx}/drh/tUser/">用户信息列表</a></li>
		<shiro:hasPermission name="drh:tUser:edit"><li><a href="${ctx}/drh/tUser/form">用户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tUser" action="${ctx}/drh/tUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>sid：</label>
				<form:input path="sid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>username：</label>
				<form:input path="username" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>nickname：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>signature：</label>
				<form:input path="signature" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>sex：</label>
				<form:input path="sex" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>age：</label>
				<form:input path="age" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>province：</label>
				<form:input path="province" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>city：</label>
				<form:input path="city" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>area：</label>
				<form:input path="area" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>address：</label>
				<form:input path="address" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>useravatar：</label>
				<form:input path="useravatar" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>professional：</label>
				<form:input path="professional" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>interest：</label>
				<form:input path="interest" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>umoney：</label>
				<form:input path="umoney" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>idcard：</label>
				<form:input path="idcard" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>bankcard：</label>
				<form:input path="bankcard" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>username</th>
				<shiro:hasPermission name="drh:tUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tUser">
			<tr>
				<td><a href="${ctx}/drh/tUser/form?id=${tUser.id}">
					${tUser.username}
				</a></td>
				<shiro:hasPermission name="drh:tUser:edit"><td>
    				<a href="${ctx}/drh/tUser/form?id=${tUser.id}">修改</a>
					<a href="${ctx}/drh/tUser/delete?id=${tUser.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>