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
<h1>会員検索</h1>
<form  action="/LibraryManagementSystem/MemberServlet?action=search" method="post">
  <div class="opTx">
  <div class="optAndText">
  <div class="select">
  <select class="sel" name="type">
    <option value="memberId">会員ID</option>
    <option value="email">メールアドレス</option>
  </select>
  </div>
  </div>
  <div class="optAndText">
  <div class="textBox">
  <label class="ef">
  <input type="text" name="submitText">
  </label>
  </div>
  </div>
  </div>
  <input type="submit" class="button" value="検索">
</form>
  <form action="/LibraryManagementSystem/menu.jsp" method="post">
    <input type="submit" class="button" value="戻る">
  </form>
</body>
</html>
