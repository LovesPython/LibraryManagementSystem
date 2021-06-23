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
  <h1>更新したい要素を書き換えてください</h1>
  <form  action="/LibraryManagementSystem/DocumentServlet?action=updateCatalog" method="post">
    <div class="textBox">
      <label class="ef">
    ISBN番号：　<input type="text" name="ISBN" value="${docCategory.isbnNo}"><br>
    資料名：　　<input type="text" name="name" value="${docCategory.name}"><br>
    分類コード：<input type="text" name="classifyCode" value="${docCategory.categoryCode}"><br>
    著者：　　　<input type="text" name="author" value="${docCategory.author}"><br>
    出版社：　　<input type="text" name="publisher" value="${docCategory.publisher}"><br>
    出版日：　　<input type="text" class="dateText" name="year" value="${dateList[0]}">年
    <input type="text" class="dateText" name="month" value="${dateList[1]}">月
      <input type="text" class="dateText" name="date" value="${dateList[2]}">日<br>
    <input type="submit" class="button" value="更新完了">
    </label>
  </div>
  </form>
  <form action="/LibraryManagementSystem/documentManager/showSearchedDocuments.jsp" method="post">
    <input type="submit" class="button" value="戻る">
  </form>
</body>
</html>
