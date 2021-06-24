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
  <h1>会員検索</h1>
<form action="/LibraryManagementSystem/LendRetServlet?action=searchForLendRet" method="post">
  <div class="textBox">
    <label class="ef">
  会員ID：
	<input type="text" name="memberID">
  </label>
</div>
  	<input type="submit" class="button" value="検索">
 </form>
<form action="/LibraryManagementSystem/LendRetServlet?action=top" method="post">
  <input type="submit" class="button" value="戻る">
</form>
</body>
</html>
