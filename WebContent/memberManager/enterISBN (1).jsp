<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/LibraryManagementSystem/Script/script.js" defer></script>
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
    <a href="./../menu.jsp"><img src="./../logo.png" alt="ヘッダ背景"></a>
  </header>
  <h1>資料のISBN番号を入力してください</h1>
<form id="form"action="#" method="post">
<%-- <form action="/LibraryManagementSystem/DocumentServlet?action=registerSearchByISBN" method="post" > --%>
  ISBN番号： <input id="text" type="text" name="ISBN" ><br>
  <input id="button" type="submit" value="登録">
</form>
<form action="./../menu.jsp" method="post">
  <input  type="submit" value="戻る">
</form>
</body>
</html>
