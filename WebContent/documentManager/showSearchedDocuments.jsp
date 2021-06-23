<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      <h1>ISBN番号sample番号の検索結果</h1>
      <h2>資料目録</h2>
      <table border="1">
        <tr>
          <td>ISBN番号</td><td>資料名</td><td>分類コード</td><td>著者</td><td>出版社</td><td>出版日</td><td>　</td>
        </tr>
        <form action="/LibraryManagementSystem/DocumentServlet?action=forwardToUpdateCatalog" method="post">
          <tr>
            <td>${docCategory.isbnNo}</td>
            <td>${docCategory.name}</td>
            <td>${docCategory.categoryCode}</td>
            <td>${docCategory.author}</td>
            <td>${docCategory.publisher}</td>
            <td>${docCategory.publishDate}</td>
            <td>
              <input type="submit" class="button" value="更新">
              </td>
            </tr>
          </form>
        </table>
          <h2>資料台帳</h2>
          <table border="1">
            <tr>
              <td>資料ID</td><td>ISBN番号</td><td>資料名</td><td>入荷年月日</td><td>廃棄年月日</td><td>備考</td><td>　</td><td>　</td>
            </tr>
            <c:forEach items="${docLedger}" var="docLedger" >

              <form action="/LibraryManagementSystem/DocumentServlet?action=forwardToUpdateLedger" method="post">
                <tr>
                  <td>${docLedger.id}</td>
                  <td>${docLedger.isbnNo}</td>
                  <td>${docLedger.name}</td>
                  <td>${docLedger.addDate}</td>
                  <td>${docLedger.discardedDate}</td>
                  <td>${docLedger.note}</td>
                  <td>
                    <input type="hidden" name="targetId" value="${docLedger.id}">
                    <input type="submit" class="button" value="更新">
                    </td>
                  </form>
                  <td>
                    <form action="/LibraryManagementSystem/DocumentServlet?action=deleteLedger" method="post">
                      <input type="hidden" name="targetLedgerId" value="${docLedger.id}">
                      <input type="submit" class="button" value="削除">
                      </form>
                    </td>
                  </tr>
                </c:forEach>
              </table>
                <form action="/LibraryManagementSystem/documentManager/searchDocument.jsp" method="post">
                  <input type="submit" value="戻る">
                  </form>
                </body>
              </html>
