<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <h1>以下内容で貸し出しますか？</h1>
  <table border="1">
    <tr>
      <td>会員ID</td><td>資料ID</td><td>貸出年月日</td><td>返却期日</td>
    </tr>
    <c:forEach items="${lending}" var="lend">
        <tr>
          <td>${lend.memberId}</td><td>${lend.documentId}</td><td>${lend.lentDate}</td><td>${lend.returnDeadline}</td>
        </tr>
    </c:forEach>
  </table>
  <form action="/LibraryManagementSystem/LendRetServlet?action=lend" method="post">
    <input type="submit" value="OK"> <br>
  </form>
  <form action="/LibraryManagementSystem/LendRetServlet?action=searchForLendRet" method="post">
    <input type="submit" value="戻る"> <br>
  </form>
</body>
</html>
