<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<h1>すでに同じ資料が入荷しています</h1>
<table border="1">
  <tr>
    <td>ISBN番号</td><td>資料名</td><td>分類コード</td><td>著者</td><td>出版社</td><td>出版日</td><td>資料ID</td>
  </tr>
  <tr>
    <td>${doc.isbnNo}</td><td>${doc.name}</td><td>${doc.categoryCode}</td><td>${doc.author}</td><td>${doc.publisher}</td><td>${doc.publishDate}</td><td>${doc.documentId}</td>
  </tr>
</table>
<form action="/LibraryManagementSystem/DocumentServlet?action=confirmAlready" method="post">
<input type="submit" class="button" value="確認画面へ">
</form>
<form action="/LibraryManagementSystem/documentManager/enterISBN.jsp" method="post">
  <input type="submit" class="button" value="戻る">
</form>
</body>
</html>
