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
    <a href="main.html"><img src="logo.png" alt="ヘッダ背景"></a>
  </header>
<h1>以下の内容で資料を登録します</h1>
<table border="1">
  <tr>
    <td>ISBN番号</td><td>資料名</td><td>分類コード</td><td>著者</td><td>出版社</td><td>出版日</td><td>資料ID</td>
  </tr>
  <tr>
    <td>samISBN番号</td><td>sam資料名</td><td>sam分類コード</td><td>sam著者</td><td>sam出版社</td><td>sam出版日</td><td>sam資料ID</td>
  </tr>
</table>
<form action="#" method="post">
  <input type="submit" value="登録する">
</form>
<form action="#" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
