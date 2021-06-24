<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
  <script src="/LibraryManagementSystem/script/script.js" defer></script>
  </head>
  <body>
    <header>
      <a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
      ささき図書館
    </header>
  <h1>検索結果</h1>
  <h2>資料台帳</h2>
  <table border="1">
    <tr>
      <td>資料ID</td><td>ISBN番号</td><td>資料名</td><td>入荷年月日</td><td>廃棄年月日</td><td>備考</td><td>　</td><td>　</td>
    </tr>
    <tr>
      <td>${docLedger.id}</td>
      <td>${docLedger.isbnNo}</td>
      <td>${docLedger.name}</td>
      <td>${docLedger.addDate}</td>
      <td>${docLedger.discardedDate}</td>
      <td>${docLedger.note}</td>
      <td>
        <form action="/LibraryManagementSystem/DocumentServlet?action=forwardToUpdateLedgerOnly" method="post">
        <input type="submit" class="button" value="更新">
        </form>
      </td>
      <td>
        <form action="/LibraryManagementSystem/DocumentServlet?action=deleteLedgerOnly" method="post">
        <input type="submit" class="button" value="削除" onClick="return Check()">
        </form>
      </td>
    </tr>
  </table>
<hr>
  <form action="/LibraryManagementSystem/documentManager/searchDocument.jsp" method="post">
    <input type="submit" class="button" value="戻る">
  </form>
</body>
</html>
