<%@ page import="com.atguigu.pojo.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Person person = new Person();
    person.setName("zou_bai");
    person.setPhones(new String[]{"18610541354", "18688886666", "18699998888"});
    List<String> cities = new ArrayList<String>();
    cities.add("北京");
    cities.add("上海");
    cities.add("广州");
    cities.add("深圳");
    person.setCities(cities);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    person.setMap(map);

    pageContext.setAttribute("person", person);
%>
输出person：${person}<br>
输出person的name属性：${person.name}<br>
输出person的phones属性：${person.phones[2]}<br>
输出person的cities集合中的元素值：${person.cities}<br>
输出person的List集合中个别元素值：${person.cities[1]}<br>
输出person的Map集合：${person.map}<br>
输出person的Map集合中的某个key的值：${person.map.key1}<br>
</body>
</html>
