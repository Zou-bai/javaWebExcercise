<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/15
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <%--			大于首页才显示--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--	页码输出的开始--%>
    <%--			<a href="#">3</a>--%>
    <%--			【${requestScope.page.pageNo}】--%>
    <%--			<a href="#">5</a>--%>
    <c:choose>
        <%--				情况1、如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <%--                <c:set var="begin" value="1"></c:set>--%>
            <%--                <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>--%>
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i==requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i!=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}"
                               end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}"
                               end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%--<c:forEach begin="${begin}" end="${end}" var="1">--%>
    <%--	<c:if test="${i==requestScope.page.pageNo}">--%>
    <%--		${i}--%>
    <%--	</c:if>--%>
    <%--	<c:if test="${i!=requestScope.page.pageNo}">--%>
    <%--		<a href="client/bookServlet?action=page&pageNo="${i}>${i}</a>--%>
    <%--	</c:if>--%>
    <%--</c:forEach>--%>
    <%--	页码输出的结束--%>
    <%--	小于末页才显示--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script>
        $(function () {
            //调到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNO = $("#pn_input").val();

                var pagetatol = ${requestScope.page.pageTotal};
                // JavaScript语言提供了一个location地址栏对象
                // 他有一个属性叫做href，它可以获取地址栏中的地址
                // href属性可读可写可赋值
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNO;

            })
        })
    </script>
</div>

