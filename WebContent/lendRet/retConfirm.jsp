<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  <h1>この資料を返却しますか？</h1>
  <table border="1">
  	<tr>
      <td>会員ID</td><td>資料ID</td><td>貸出年月日</td><td>返却期日</td>
    </tr>
    <c:forEach items="${returnDoc}" var="ret">
        <tr>
          <td>${ret.memberId}</td><td>${ret.documentId}</td><td>${ret.lentDate}</td><td>${ret.returnDeadline}</td>
        </tr>
    </c:forEach>
  </table>
  <form action="/LibraryManagementSystem/LendRetServlet?action=return" method="post">
    <input type="submit" class="button" value="OK"> <br>
  </form>
   <form action="/LibraryManagementSystem/LendRetServlet?action=searchForLendRet" method="post">
    <input type="submit" class="button" value="戻る"> <br>
  </form>
</body>
</html>
