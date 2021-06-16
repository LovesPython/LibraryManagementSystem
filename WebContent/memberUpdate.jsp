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
  <table border="1">
    <tr>
      <td>ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td>
    </tr>
    <tr>
      <td>サンプルID</td><td>サンプルさん</td><td>サンプル住所</td><td>サンプル電話番号</td><td>サンプルメールアドレス</td><td>サンプル生年月日</td>
    </tr>
  </table>
  <hr>
    <form  action="#" method="post">
      名前：<input type="text" name="name"><br>
      住所：<input type="text" name="address"><br>
      電話番号：<input type="text" name="tel"><br>
      メールアドレス：<input type="text" name="email"><br>
      生年月日：<input type="text" name="birthday"><br>
      <input type="submit" value="確認画面へ">
    </form>
    <form action="#" method="post">
      <input type="submit" value="戻る">
    </form>
</body>
</html>
