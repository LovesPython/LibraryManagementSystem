<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<h1>以下の内容で資料を登録します</h1>
<table border="1">
  <tr>
    <td>ISBN番号</td><td>資料名</td><td>分類コード</td><td>著者</td><td>出版社</td><td>出版日</td><td>資料ID</td>
  </tr>
  <tr>
    <td>${doc.isbnNo}</td><td>${doc.name}</td><td>${doc.categoryCode}</td><td>${doc.author}</td><td>${doc.publisher}</td><td>${doc.publishDate}</td><td>${doc.documentId}</td>
  </tr>
</table>
<form action="/LibraryManagementSystem/DocumentServlet?action=register" method="post">
  <input type="submit" value="登録する">
</form>
<form action="/LibraryManagementSystem/documentManager/inputDocumentInfo.jsp" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
