<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film Result</title>
</head>
<body>
  <c:choose>
    <c:when test="${! deleted}">
       Film  successfully deleted!
    
          		<p>Return to Main Menu</p>
		<form action="index.html" method="GET">
		<input type="hidden" name="film" value= ${film.id } />
		<input type="submit" value="Main Menu" />
		</form>
    </c:when>
    <c:otherwise>
      <p>Error deleting film</p>
    </c:otherwise>
  </c:choose>
</body>
</html>