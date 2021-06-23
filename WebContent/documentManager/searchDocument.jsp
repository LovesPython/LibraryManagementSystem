<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1>資料のISBN番号または資料IDを入力してください</h1>

<form action="/LibraryManagementSystem/DocumentServlet?action=searchDocuments" method="post">
<div class="opTx">
<div class="optAndText">
  <div class="select">
  <select class="sel" name="selector">
      <option value="ISBN">ISBN番号： </option>
      <option value="documentId">資料ID</option>
  </select>
  </div>
</div>
<div class="optAndText">
  <div class="textBox">
  <label class="ef">
    <input type="text" name="input">
  </label>
  </div>
</div>
</div>
  <br>
  <input type="submit" class="button" value="検索">
</form>
<form action="/LibraryManagementSystem/menu.jsp" method="post">
  <input type="submit" class="button" value="戻る">
</form>
</body>
</html>
