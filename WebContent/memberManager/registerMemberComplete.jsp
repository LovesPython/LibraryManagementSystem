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
<h1>
登録が完了しました。登録内容は以下です。
</h1>
<table border="1">
  <tr>
    <td>会員ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td>
  </tr>
  <tr>
    <td>${member.id}</td><td>${member.name}</td><td>${member.address}</td><td>${member.tel}</td><td>${member.email}</td><td>${member.birthday}</td>
  </tr>
</table>
</body>
</html>
