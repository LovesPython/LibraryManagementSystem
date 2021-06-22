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
  <h1>更新したい要素を書き換えてください</h1>
  <form  action="/LibraryManagementSystem/DocumentServlet?action=updateLedgerOnly" method="post">
    入荷年月日：<input type="text" name="addedYear" value="${addDateList[0]}">年
    <input type="text" name="addedMonth" value="${addDateList[1]}">月
    <input type="text" name="addedDate" value="${addDateList[2]}">日
    <br>
    廃棄年月日：<input type="text" name="discardedYear" value="${discardedDateList[0]}">年
    <input type="text" name="discardedMonth" value="${discardedDateList[1]}">月
    <input type="text" name="discardedDate" value="${discardedDateList[2]}">日
    <br>
    備考：<input type="text" name="note" value="${docLedger.note}"><br>
    <input type="submit" value="更新完了">
  </form>
  <form action="/LibraryManagementSystem/documentManager/showSearchedDocuments.jsp" method="post">
    <input type="submit" value="戻る">
  </form>
</body>
</html>
