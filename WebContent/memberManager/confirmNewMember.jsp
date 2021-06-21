<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録内容確認</title>
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
    <a href="./../menu.jsp"><img src="./../logo.png" alt="ヘッダ背景"></a>
  </header>
<h1>登録内容は以下でよろしいですか？</h1>
<table border="1">
  <tr>
    <td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td>
  </tr>
  <tr>
    <td>${member.name}</td><td>${member.address}</td><td>${member.tel}</td><td>${member.email}</td><td>${member.birthday}</td>
  </tr>
</table>
<form action="/LibraryManagementSystem/MemberServlet?action=register" method="post">
  <input type="submit" value="OK"> <br>
</form>
<form action="registerNewMember.jsp" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
