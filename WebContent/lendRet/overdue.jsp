<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
  </head>
  <body>
    <header>
      <a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
      ささき図書館
    </header>
<h1>延滞者一覧</h1>
<table border="1">
  <tr>
    <td>会員ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td>
  </tr>

  <c:forEach items="${members}" var="member">
  <tr>
    <td>${member.id}</td><td>${member.name}</td><td>${member.address}</td><td>${member.tel}</td><td>${member.email}</td><td>${member.birthday}</td>
  </tr>
  </c:forEach>
</table>
<form action="/LibraryManagementSystem/LendRetServlet?action=top" method="post">
  <input type="submit" class="button" value="戻る">
</form>
</body>
</html>
