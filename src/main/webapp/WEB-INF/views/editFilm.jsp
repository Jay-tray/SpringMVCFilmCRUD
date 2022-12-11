<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>editFilm</title>
</head>
<body>
  <c:choose>
    <c:when test="${! empty film}">
     <form action="editFilm.do" method="POST">
     <input type="hidden" name="id" value="${film.id }" />
     <br>
     ID: ${film.id} }
     <br> 
     Film Name:
    <input type="text" name="title" value="${film.title }" />
    <br>
    Description:
    <input type="text" name="description" value="${film.description }" />
    <br>
    Release year:
    <input type="text" name="relYear"value="${film.releaseYear}" /> 
    <br>
    Language ID:
    <select name="lanId"> 
    <option value="1">English</option> 
    <option value="2">Italian</option> 
    <option value="3">Japanese</option> 
    <option value="4">Mandarin</option> 
    <option value="5">French</option> 
    <option value="6">German</option>
    </select>
    <br>
     Rental Duration:
    <input type="text" name="renDur" value="${film.rentalDuration }" />
    <br>
     Rental Rate:
    <select name="renRat">
    <option value="3.99">3.99</option> 
    <option value="5.99">5.99</option> 
    <option value="7.99">7.99</option> 
    </select>
    <br>
    Film Length:
    <input type="text" name="length" value="${film.length }" />
    <br>
    Replacement Cost:
     <select name="repCost">
    <option value="3.99">3.99</option> 
    <option value="5.99">5.99</option> 
    <option value="7.99">7.99</option> 
    </select>
    <br>
    Rating:
    <select name="rating">
    <option value="G">G</option> 
    <option value="PG">PG</option> 
    <option value="PG-13">PG-13</option> 
    <option value="R">R</option> 
    <option value="M">M</option> 
    <option value="NA">NA</option>
    </select>
    <br><br>
   
     <div>Special Features <br>
      <input type="checkbox" id="trailers" name="specFeat" value="trailers" />
      <label for="trailers">Trailers</label>
    <br>
      <input type="checkbox" id="commentaries" name="specFeat" value="commentaries" />
      <label for="commentaries">Commentaries</label>
    <br>
      <input type="checkbox" id="delScenes" name="specFeat" value="delScenes" />
      <label for="delScenes">Deleted Scenes</label>
   <br>
      <input type="checkbox" id="behScenes" name="specFeat" value="behScenes" />
      <label for="behScenes">Behind the Scenes</label>
    </div>
    
    </form>  
    </c:when>
    <c:otherwise>
      <p>No Film Found</p>
    </c:otherwise>
  </c:choose>
</body>
</html>