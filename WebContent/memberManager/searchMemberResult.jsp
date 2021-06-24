<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>会員検索結果</title>
  <link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
  </head>
  <body>
    <header>
      <a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
      ささき図書館
    </header>
<h1>検索結果</h1>
<table border="1">
  <tr>
    <td>ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td><td>入会年月日</td><td>退会年月日</td>
  </tr>
  <tr>
    <td>${member.id}</td><td>${member.name}</td><td>${member.address}</td><td>${member.tel}</td><td>${member.email}</td><td>${member.birthday}</td><td>${member.joinDate}</td><td>${member.withdrawalDate}</td>
  </tr>
</table>
<div class="yokoBox">
  <div class="yoko">
    <form  action="/LibraryManagementSystem/MemberServlet?action=forwardToUpdate" method="post">
      <input type="submit" class="button" value="会員情報変更">
    </form>
  </div>
  <div class="yoko">
    <form  action="/LibraryManagementSystem/MemberServlet?action=verdictDeletableMember" method="post">
      <input type="submit" class="button" value="会員退会">
    </form>
  </div>
</div>
<form action="/LibraryManagementSystem/memberManager/searchMemberMember.jsp" method="post">
  <input type="submit" class="button" value="戻る">
</form>
</body>
</html>
