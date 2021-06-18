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
  <h1>ISBN番号sample番号の検索結果</h1>
  <h2>資料目録</h2>
  <table border="1">
    <tr>
      <td>ISBN番号</td><td>資料名</td><td>分類コード</td><td>著者</td><td>出版社</td><td>出版日</td><td>資料ID</td>
    </tr>
    <tr>
      <td>samISBN番号</td><td>sam資料名</td><td>sam分類コード</td><td>sam著者</td><td>sam出版社</td><td>sam出版日</td><td>sam資料ID</td>
      <td>
        <input type="submit" value="更新">
      </td>
    </tr>
  </table>
<hr>
  <h2>資料台帳</h2>
  <table border="1">
    <tr>
      <td>資料ID</td><td>ISBN番号</td><td>資料名</td><td>入荷年月日</td><td>廃棄年月日</td><td>備考</td>
    </tr>
    <tr>
      <td>smp資料ID</td><td>smpISBN番号</td><td>smp資料名</td><td>smp入荷年月日</td><td>smp廃棄年月日</td><td>smp備考</td>
      <td>
        <input type="submit" value="更新">
      </td>
      <td>
        <input type="submit" value="削除">
      </td>
    </tr>
    <tr>
      <td>smp資料ID</td><td>smpISBN番号</td><td>smp資料名</td><td>smp入荷年月日</td><td>smp廃棄年月日</td><td>smp備考</td>
      <td>
        <input type="submit" value="更新">
      </td>
      <td>
        <input type="submit" value="削除">
      </td>
    </tr>
  </table>
<hr>
  <form action="#" method="post">
    <input type="submit" value="戻る">
  </form>
</body>
</html>
