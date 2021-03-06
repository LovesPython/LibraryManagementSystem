<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>会員情報更新確認</title>
  <link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
  </head>
  <body>
    <header>
      <a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
      ささき図書館
    </header>
  <h1>更新内容は以下でよろしいですか？</h1>
  <table border="1">
    <tr>
      <td>ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td>
    </tr>
    <tr>
    <td>${member1.id}</td><td>${member1.name}</td><td>${member1.address}</td><td>${member1.tel}</td><td>${member1.email}</td><td>${member1.birthday}</td>
    </tr>
  </table>
  <form action="/LibraryManagementSystem/MemberServlet?action=registerUpdate" method="post">
    <input type="submit" class="button" value="OK">
  </form>
  <form action="/LibraryManagementSystem/MemberServlet?action=forwardToUpdate" method="post">
    <input type="submit" class="button" value="戻る">
  </form>
</body>
</html>
