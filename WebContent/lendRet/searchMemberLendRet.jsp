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
  <h1>会員検索</h1>
<form action="/LibraryManagementSystem/LendRetServlet?action=searchForLendRet" method="post">
	会員ID：
	<input type="text" name="memberID"><br><br>
	  <div>
	    <br>
	  </div>

  	<input type="submit" value="検索">
 </form>
<form action="/LibraryManagementSystem/LendRetServlet?action=top" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
