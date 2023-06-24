<%--
  Created by IntelliJ IDEA.
  User: Haonenho
  Date: 2023/6/4
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>班次信息</title>
    <script type="text/javascript" src="js/jquery-3.7.0.js"></script>
    <link rel="stylesheet" href="css/index01.css" type="text/css">
</head>
<body>
<div class="navbar">
    <a href="#">首页</a>
    <a href="#">关于</a>
    <a href="#">服务</a>
    <a href="#">联系我们</a>
</div>
<div>
    <table id="dataTable" border="1" class="tabler">


        <tr>
            <th>班次</th>
            <th>发车时间</th>
            <th>起始站</th>
            <th>终点站</th>
            <th>额定载客量</th>
            <th>已定人数</th>
        </tr>

    </table>
</div>
<script type="text/javascript" src="js/display01.js"></script>
<form action="T01" method="post">
    <h1>路线查询</h1>
    <label>
        <input type="text" name="shift number">
        <input type="submit" name="num check" value="查询">
    </label>
</form>
<form action="T02" method="post">
    <label>
        <input type="text" name="terminal">
        <input type="submit" name="ter check" value="查询">
    </label>
</form>
<form action="T03" method="post">
    <label>
        <input type="submit" name="setting" value="管理车票信息">
    </label>
</form>
</body>
</html>
