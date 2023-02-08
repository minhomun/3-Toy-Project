
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" --%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title>글내용</title>
</head>
<body>
<form method="post" action="UpdateAction">
  <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
    <thead>
    <tr>
      <th colspan="2" style="background-color:#eeeeee; text-align:left;">자유게시판</th>

    </tr>
    </thead>
    <tbody>
    <tr>
      <td><input type="text" class="form-control" placeholder="제목을 입력하세요."  name="bbsTitle" value="${ dto.TITLE}" maxlength="50" ></td>
    </tr>
    <tr>
      <td><textarea class="form-control" placeholder="문의 내용을 입력하세요."  name="bbsContent" maxlength="2048" style="height:350px" >${ dto.CONTENT}</textarea></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="수정하기">
        <a href="freeBoard"><input type="button" value="목록보기"></a>
        <a href="deleteAction?Table_No=${ dto.Table_No }"><input type="button" value="삭제하기"></a>
      </td>
    </tr>
    </tbody>
  </table>
  <input type="submit"  class="btn btn-primary pull-right" value="저장">
</form>
</body>
</html>
