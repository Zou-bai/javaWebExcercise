<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        <%--		页面加载完成之后给删除添加删除时间--%>

        $(function () {
            //给【删除】绑定单击事件
            $("a.deleteItem").click(function () {
                return confirm("你确定要删除" + $(this).parent().parent().find("td:first").text() + "吗？");
            });
            //给清空购物车绑定单击事件
            $("a.clearItem").click(function () {
                return confirm("你确定要删除所有商品吗？");
            });
            //给输入框绑定失去焦点事件===onchange内容发生改变事件
            $(".updateCount").change(function () {

                var id = $(this).attr('bookId');
                var count = this.value;
                if (confirm("你确定要将【" +
                    $(this).parent().parent().find("td:first").text() +
                    "】商品修改数量为 " + count + "吗？")) {
                    location.href = "http://localhost:8080/book/cartServlet?action=updateItem&count=" + count + "&id=" + id;
                } else {
                    //defaultValue属性是表单项Dom对象
                    this.value = this.defaultValue;
                }
            });
        });
    </script>
</head>
<body>
<%--	${sessionScope}--%>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@include file="/pages/common/Login_sucess_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <%--				购物车为空--%>
            <tr>
                <td colspan="5"><a href="index.jsp">当前购物车为空</a></td>
            </tr>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <%--				购物车非空--%>
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}"</td>
                    <td><input class="updateCount" bookId="${entry.value.id}" type="text" value="${entry.value.count}"></td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <%--		&lt;%&ndash;静态包含登陆成功之后的菜单&ndash;%&gt;--%>
    <%--		<%@include file="/pages/common/Login_sucess_menu.jsp"%>--%>

    <%--		如果非空才输出--%>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a class="clearItem" href="cartServlet?action=clearItem">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>