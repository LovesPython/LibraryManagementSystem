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
  <h1>更新したい要素を書き換えてください</h1>
  <form  action="#" method="post">
    ISBN番号：<input type="text" name="ISBN" value="smpISBN番号"><br>
    資料名：<input type="text" name="name" value="smp資料名"><br>
    分類コード：<input type="text" name="classifyCode" value="smp分類コード"><br>
    著者：<input type="text" name="author" value="smp著者"><br>
    出版社：<input type="text" name="publisher" value="smp出版社"><br>
    出版日：<input type="text" name="publishDate" value="smp出版日"><br>
    <input type="submit" value="更新完了">
  </form>
  <form action="#" method="post">
    <input type="submit" value="戻る">
  </form>
</body>
</html>
