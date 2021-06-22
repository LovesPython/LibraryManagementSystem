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
<h1>資料のISBN番号または資料IDを入力してください</h1>
<form action="/LibraryManagementSystem/DocumentServlet?action=searchDocuments" method="post">
<select name="selector">
    <option value="ISBN">ISBN番号： </option>
    <option value="documentId">資料ID</option>
  </select>
  <input type="text" name="input"><br>
  <input type="submit" value="検索">
</form>
<form action="/LibraryManagementSystem/menu.jsp" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
