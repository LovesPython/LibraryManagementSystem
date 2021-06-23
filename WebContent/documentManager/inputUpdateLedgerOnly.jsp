<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
  </head>
  <body>
    <header>
      <a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
      ささき図書館
    </header>
  <h1>更新したい要素を書き換えてください</h1>
  <form  action="/LibraryManagementSystem/DocumentServlet?action=updateLedgerOnly" method="post">
    <div class="textBox">
      <label class="ef">
    入荷年月日：<input type="text" class="dateText" name="addedYear" value="${addDateList[0]}">年
    <input type="text" class="dateText" name="addedMonth" value="${addDateList[1]}">月
    <input type="text" class="dateText" name="addedDate" value="${addDateList[2]}">日
    <br>
    廃棄年月日：<input type="text" class="dateText" name="discardedYear" value="${discardedDateList[0]}">年
    <input type="text" class="dateText" name="discardedMonth" value="${discardedDateList[1]}">月
    <input type="text" class="dateText" name="discardedDate" value="${discardedDateList[2]}">日<br>
    備考：　　　<input type="text" name="note" value="${docLedger.note}"><br>
    <input type="submit" class="button" value="更新完了">
    </label>
  </div>
  </form>
  <form action="/LibraryManagementSystem/documentManager/showSearchedLedger.jsp" method="post">
    <input type="submit" class="button" value="戻る">
  </form>
</body>
</html>
