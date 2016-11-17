<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div style="width:100%;float:left;">
  <div style="width:190px;float:left;"><a href="${pageContext.request.contextPath }/order/add.action">创建采购单</a></div>
  <div style="width:190px;float:left;"><a href="${pageContext.request.contextPath }/order/createOrderList.action">提交采购单</a></div>
  <div style="width:190px;float:left;"><a href="${pageContext.request.contextPath }/order/firstAuditList.action">部门经理审核采购单</a></div>
  <div style="width:190px;float:left;"><a href="${pageContext.request.contextPath }/order/secondAuditList.action">总经理审核采购单</a></div>
  <div style="width:190px;float:left;"><a href="${pageContext.request.contextPath }/order/thirdAuditList.action">财务审核采购单</a></div>
  <div style="width:190px;float:left;"><a href="${pageContext.request.contextPath }/order/activeOrderList.action">采购单进度</a></div>
  <div style="width:190px;float:left;"><a href="${pageContext.request.contextPath }/order/finishOrderList.action">已完成采购单</a></div>
</div>
<div>
当前用户：${active_user}<a href="${pageContext.request.contextPath }/logout.action">退出</a>
</div>
<br/>
<br/>
<br/>
</body>
</html>