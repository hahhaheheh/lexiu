<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{width:100%;height:70%;}
		p{margin-left:5px; font-size:14px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=t3wrfavKyphyLdmG604EnypdXWXr1WFR"></script>
	<title>机构添加</title>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'cerulean'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="${ctxStatic}/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/jeesite.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
</head>
<body>
	<div id="allmap"></div>
  <p>
  <form:form id="inputForm" modelAttribute="tOrg" action="${ctx}/drh/tOrg/save" method="post" class="form-horizontal">
  <form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<form:input path="tags" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">省：</label>
			<div class="controls">
				<form:input path="province" id="province" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市：</label>
			<div class="controls">
				<form:input path="city" id="city" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区：</label>
			<div class="controls">
				<form:input path="distrinct" id="distrinct" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">街道：</label>
			<div class="controls">
				<form:input path="street" id="street" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">街道号：</label>
			<div class="controls">
				<form:input path="streetnumber" id="streetnumber" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经度：</label>
			<div class="controls">
				<form:input path="longitude" id="longitude" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纬度：</label>
			<div class="controls">
				<form:input path="latitude" id="latitude" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">封面图：</label>
			<div class="controls">
				<form:hidden id="imageurl" path="imageurl" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<sys:ckfinder input="imageurl" type="images" uploadPath="/orgphoto" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级：</label>
			<div class="controls">
				<form:input path="startlevel" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</form:form>
  </p>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");            
	//map.centerAndZoom("北京",12); 
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	
	var geoc = new BMap.Geocoder();   
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		//alert(e.point.lng + "," + e.point.lat);
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			$("#province").val(addComp.province);
			$("#city").val(addComp.city);
			$("#street").val(addComp.street);
			$("#streetNumber").val(addComp.streetNumber);
			$("#district").val(addComp.district);
			$("#longitude").val(pt.lng);
			$("#latitude").val(pt.lat);
			
			//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		});        
		
	});
</script>
