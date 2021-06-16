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
  <h1>この資料を返却しますか？</h1>
  <table border="1">
    <tr>
      <td>会員ID</td><td>資料ID</td><td>貸出年月日</td><td>返却期日</td>
    </tr>
    <tr>
      <td>サンプルID</td><td>サンプルID</td><td>サンプル年月日</td><td>サンプル期日</td>
    </tr>
  </table>
  <form action="#" method="post">
    <input type="submit" value="OK"> <br>
    <input type="submit" value="戻る">
  </form>
</body>
</html>
