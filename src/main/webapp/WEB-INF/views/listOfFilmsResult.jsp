<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film Search Result</title>
</head>
<body>
  <c:choose>
    <c:when test="${! empty film}">

      <p> Film Search Match: </p>
      <c:forEach var="film" items="${film}">
    <ul>
        <li>${film.title}</li>
        <li>${film.description}</li>
        <li>${film.relYear}</li>
        <li>${film.lanId}</li>
        <li>${film.renDur}</li>
        <li>${film.renRat}</li>
        <li>${film.length}</li>
        <li>${film.repCost}</li>
        <li>${film.rating}</li>
        <li>${film.specFeat}</li>
        <li>${film.actors}</li>
        <%-- <li>${film.category}</li> --%>
      </ul>
            		<p>Return to Main Menu</p>
		<form action="index.html" method="GET">
		<input type="hidden" name="film" value= ${film.id } />
		<input type="submit" value="Main Menu" />
		</form>
		<br>			<p>Edit Film</p>
		<form action="editFilm.do?id=${film.id }&title=${film.title}&description=${film.description }&relYear=${film.relYear}&lanId=${film.lanId}&renDur=${film.renDur}&renRat=${film.renRat}&length=${film.length}&repCost=${film.repCost}&rating=${film.rating}&specFeat=${film.specFeat}" method="POST">
		<input type="hidden" name="film" value= "${film.id }" />
		<input type="submit" value="Edit Film" />		
		</form>	
		<br>
			<p>Delete Film</p>
		<form action="deleteFilm.html" method="POST">
		<input type="hidden" name="film" value= "${film.id }" />
		<input type="submit" value="Delete Film" />
		</form>	
      
      </c:forEach>

    </c:when>
    <c:otherwise>
      <p>No Film Found</p>
    </c:otherwise>
  </c:choose>

</body>
</html>