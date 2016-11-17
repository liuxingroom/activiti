<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="cn.itcast.purchasing.util.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ attribute name="postFunctionName" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int current =  page.getPageNo();
long begin = Math.max(1, current - paginationSize/2);
long end = Math.min(begin + (paginationSize - 1), page.getTotalPages());
request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end); 

%>
<script type="text/javascript">
function page_change_fun(){
	var page_size_v=document.getElementById("page_change_select").value;
	${postFunctionName}(1,page_size_v);
}
</script>
<div class="pagination pagination-centered">
	<ul>
		<li class="disabled"><a>共${page.totalCount }条数据 </a></li>
		
		 <% if ((page.isHasNext() && current != 1) || (current == end && current != 1)){%>
               	<li><a href=javascript:${postFunctionName}(1,${page.pageSize})>&lt;&lt;</a></li>
                <li><a href=javascript:${postFunctionName}(${current-1},${page.pageSize})>&lt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
         <%} %>
		
	  	 <% if (page.isHasNext()){%>
               	<li><a href=javascript:${postFunctionName}(${current+1},${page.pageSize})>&gt;</a></li>
                <li><a href=javascript:${postFunctionName}(${page.totalPages},${page.pageSize})>&gt;&gt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
         <%} %>
         <li>
         <span>
		每页显示
		<select id="page_change_select" onChange="page_change_fun()">
		<option value="15" <c:if test="${page.pageSize==15}">selected</c:if>>15</option>
		<option value="30" <c:if test="${page.pageSize==30}">selected</c:if>>30</option>
		<option value="50" <c:if test="${page.pageSize==50}">selected</c:if>>50</option>
		<option value="100" <c:if test="${page.pageSize==100}">selected</c:if>>100</option>
		</select>
		条
		</span>
		</li>
	</ul>
</div>