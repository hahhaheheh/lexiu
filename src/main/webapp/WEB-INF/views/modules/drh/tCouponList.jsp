<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>coupon管理</title>
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
		<li class="active"><a href="${ctx}/drh/tCoupon/">coupon列表</a></li>
		<shiro:hasPermission name="drh:tCoupon:edit"><li><a href="${ctx}/drh/tCoupon/form">coupon添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tCoupon" action="${ctx}/drh/tCoupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>type：</label>
				<form:input path="type" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			<li><label>facevalue：</label>
				<form:input path="facevalue" htmlEscape="false" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>type</th>
				<th>name</th>
				<th>facevalue</th>
				<shiro:hasPermission name="drh:tCoupon:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCoupon">
			<tr>
				<td><a href="${ctx}/drh/tCoupon/form?id=${tCoupon.id}">
					${tCoupon.type}
				</a></td>
				<td>
					${tCoupon.name}
				</td>

				<td>
					${tCoupon.facevalue}
				</td>

				<shiro:hasPermission name="drh:tCoupon:edit"><td>
    				<a href="${ctx}/drh/tCoupon/form?id=${tCoupon.id}">修改</a>
					<a href="${ctx}/drh/tCoupon/delete?id=${tCoupon.id}" onclick="return confirmx('确认要删除该coupon吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>