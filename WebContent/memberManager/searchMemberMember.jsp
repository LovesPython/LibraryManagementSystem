<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員検索</title>
</head>
<style media="screen">
img{
  background-repeat: no-repeat;
  left: 0px;
  height: 50px;
  margin: 0px;
  padding: 0px;
}
</style>
<body>
  <header>
  	<a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
  </header>
<h1>会員検索</h1>
<form  action="/LibraryManagementSystem/MemberServlet?action=search" method="post">
  <select  name="">
    <option value="memberId">会員ID</option>
    <option value="email">メールアドレス</option>
  </select>
  <input type="text" name="submitText"><br><br>
    <div>
      <br>
    </div>
  <input type="submit" value="検索">
</form>
  <form action="./../menu.jsp" method="post">
    <input type="submit" value="戻る">
  </form>
</body>
</html>
