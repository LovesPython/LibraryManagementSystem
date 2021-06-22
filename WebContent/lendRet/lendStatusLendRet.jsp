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
  <h1>貸出状況</h1>
  <table border="1">
    <tr>
      <td>会員ID</td><td>資料ID</td><td>貸出年月日</td><td>返却期日</td>
    </tr>
      <c:forEach items="${members}" var="member">
        <tr>
          <td>${member.memberId}</td><td>${member.documentId}</td><td>${member.lentDate}</td><td>${member.returnDeadline}</td>
          <td>
          	<form action="/LibraryManagementSystem/LendRetServlet?action=confirmRet" method="post">
          		<input type="hidden" name="doc_code" value="${member.documentId }">
            	<input type="submit" value="返却">
            </form>
          </td>
        </tr>
      </c:forEach>
  </table>
<hr>
<form action="/LibraryManagementSystem/LendRetServlet?action=confirmLend" method="post">
  貸出資料ID：<input type="text" name="lendDocumentID"><br>
  <input type="submit" value="貸出確認画面へ">
</form>
<form action="/LibraryManagementSystem/LendRetServlet?action=top" method="post">
  <input type="submit" value="戻る">
</form>
</body>
</html>
