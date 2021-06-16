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
    入荷年月日：<input type="text" name="classifyCode" value="smp入荷年月日"><br>
    廃棄年月日：<input type="text" name="author" value="smp廃棄年月日"><br>
    備考：<input type="text" name="publisher" value="smp備考"><br>
    <input type="submit" value="更新完了">
  </form>
  <form action="#" method="post">
    <input type="submit" value="戻る">
  </form>
</body>
</html>
