<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員検索結果</title>
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
<h1>検索結果</h1>
<table border="1">
  <tr>
    <td>ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td><td>入会年月日</td><td>退会年月日</td>
  </tr>
  <tr>
    <td>${member.memberId}</td><td>${member.name}</td><td>${member.address}</td><td>${member.tel}</td><td>${member.email}</td><td>${member.birthday}</td><td>${member.addedAt}</td><td>${member.deletedAt}</td>
  </tr>
</table>
<form  action="memberUpdate.jsp" method="post">
  <input type="submit" value="会員情報変更">
</form>
<form  action="/LibraryManagementSystem/MemberServlet?action=verdictDeletableMember" method="post">
  <input type="submit" value="会員退会">
</form>
<form action="searchMemberMember.jsp" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
