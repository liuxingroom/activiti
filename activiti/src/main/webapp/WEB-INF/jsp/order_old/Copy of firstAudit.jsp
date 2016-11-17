<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引用jquery easy ui的js库及css -->
<LINK rel="stylesheet" type="text/css" href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/jsp/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/common_js.jsp"%>
<title>部门经理审核</title>
</head>
<body>

<form action="${baseurl}order/firstAuditSubmit.action" method="post">
	任务id：<input type="text" name="taskId" value="${taskId}"/> 
	审核意见：<input type="text" name="purBusOrderAudit.auditInfo"/><br/>
	审核结果：
	<input type="radio" name="purBusOrderAudit.status" value="1"/>通过,
	<input type="radio" value="0" name="purBusOrderAudit.status"/>不通过<br/>
	<input type="submit" value="提交"/>
</form>

</body>
</html>