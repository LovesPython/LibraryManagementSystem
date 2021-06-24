<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>退会</title>
  <link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
  </head>
  <body>
    <header>
      <a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
      ささき図書館
    </header>
  <h1>退会は可能です</h1>
  <table border="1">
    <tr>
      <td>ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td>
    </tr>
    <tr>
    <td>${member.id}</td><td>${member.name}</td><td>${member.address}</td><td>${member.tel}</td><td>${member.email}</td><td>${member.birthday}</td>
    </tr>
  </table>
<hr>
貸出中の資料：なし
<form action="/LibraryManagementSystem/MemberServlet?action=deleteMember" method="post">
  <input type="submit" class="button" value="退会">
</form>
<form action="/LibraryManagementSystem/memberManager/searchMemberResult.jsp" method="post">
  <input type="submit" class="button" value="戻る">
</form>
</body>
</html>
