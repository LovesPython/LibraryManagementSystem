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
  <h1>資料のISBN番号を入力してください</h1>
<form action="/LibraryManagementSystem/DocumentServlet?action=registerSearchByISBN" method="post">
  <div class="textBox">
    <label class="ef">
    ISBN番号： <input type="text" name="ISBN"><br>
    </label>
  </div>
  <input type="submit" class="button buttonA" value="登録">
</form>
<form action="/LibraryManagementSystem/menu.jsp" method="post">
  <input type="submit" class="button buttonB" value="戻る">
</form>
</body>
</html>
