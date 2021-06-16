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
  <h1>ISBN番号sample番号の資料を登録します</h1>
  <form  action="#" method="post">
    資料名：<input type="text" name="name"><br>
    分類コード：<input type="text" name="classifyCode"><br>
    著者：<input type="text" name="author"><br>
    出版社：<input type="text" name="publisher"><br>
    出版日：<input type="text" name="publishDate"><br>
    <input type="submit" value="確認画面へ">
  </form>
  <form action="#" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
