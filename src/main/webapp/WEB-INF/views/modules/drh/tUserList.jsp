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
		<li><a href="${ctx}/drh/tUser/form">用户信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tUser" action="${ctx}/drh/tUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="username" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>sid</th>
				<th>用户名</th>
				<th>昵称</th>
				<th>signature</th>
				<th>性别</th>
				<th>年龄</th>
				<th>省</th>
				<th>市</th>
				<th>area</th>
				<th>地址</th>
				<th>useravatar</th>
				<th>professional</th>
				<th>喜好</th>
				<th>余额</th>
				<th>省份证号</th>
				<th>银行卡</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tUser">
				<td><a href="${ctx}/drh/tUser/form?id=${tUser.id}">
					${tUser.sid}
				</a></td>
				<td>
					${tUser.username}
				</td>
				<td>
					${tUser.password}
				</td>
				<td>
					${tUser.nickname}
				</td>
				<td>
					${tUser.signature}
				</td>
				<td>
					${tUser.sex}
				</td>
				<td>
					${tUser.age}
				</td>
				<td>
					${tUser.province}
				</td>
				<td>
					${tUser.city}
				</td>
				<td>
					${tUser.area}
				</td>
				<td>
					${tUser.address}
				</td>
				<td>
					${tUser.useravatar}
				</td>
				<td>
					${tUser.professional}
				</td>
				<td>
					${tUser.interest}
				</td>
				<td>
					${tUser.umoney}
				</td>
				<td>
					${tUser.idcard}
				</td>
				<td>
					${tUser.bankcard}
				</td>
				<td>
    				<a href="${ctx}/drh/tUser/form?id=${tUser.id}">修改</a>
					<a href="${ctx}/drh/tUser/delete?id=${tUser.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>