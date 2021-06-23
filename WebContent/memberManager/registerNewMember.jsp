<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
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
<h1>新規会員登録</h1>
<form  action="/LibraryManagementSystem/MemberServlet?action=registerConfirm" method="post">
  名前：<input type="text" name="name" placeholder="例）山田太郎"><br>
  住所：<input type="text" name="address" placeholder="例）東京都港区赤坂1-1"><br>
  電話番号：<input type="text" name="tel" placeholder="例）09011223344"><br>
  メールアドレス：<input type="text" name="email" placeholder="例）yamada@mail.com"><br>
  生年月日：<input type="text" name="year" placeholder="例）1988">年<input type="text" name="month" placeholder="例）4">月<input type="text" name="date" placeholder="例）14">日<br>
  <input type="submit" value="確認画面へ">
</form>
<form action="./../menu.jsp" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
