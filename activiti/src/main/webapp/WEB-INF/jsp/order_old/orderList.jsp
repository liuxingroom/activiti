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
<title>采购单列表</title>
<script type="text/javascript">
	function queryorder() {
		doucment.queryOrderForm.submit();

	}
</script>

</head>
<body>

	<form id="queryOrderForm" name="queryOrderForm"
		action="${pageContext.request.contextPath}/order/createOrderList.action"
		method="post">
		
		<TABLE class="toptable grid" >
			<TBODY>
				<tr>
					<td>采购单名称</td>
					<td>采购金额</td>
					<td>创建时间</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${list }" var="order">
					<tr>
						<td class=category>${order.name}</td>
						<td class=category>${order.price}</td>
						<td class=category><fmt:formatDate
								value="${order.createtime}"
								pattern="yyyy-MM-dd hh:mm:ss" /></td>
						<td class=category>
						<c:if test="${type=='1' }">
						<a
							href="${pageContext.request.contextPath}/order/submitOrder.action?orderId=${order.id}">提交采购单</a>
						
						</c:if>
						<c:if test="${type=='2' }">
						<a
							href="${pageContext.request.contextPath}/order/firstAudit.action?orderId=${order.id}">部门经理审核</a>
						
						</c:if>	
						<c:if test="${type=='3' }">
						<a
							href="${pageContext.request.contextPath}/order/secondAudit.action?orderId=${order.id}">总经理审核</a>
						
						</c:if>	
							</td>
					</tr>
				</c:forEach>
			</TBODY>
		</TABLE>
	</form>

</body>
</html>