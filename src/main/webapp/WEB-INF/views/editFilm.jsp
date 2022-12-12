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

      <p> You may edit the following items for Film ID: ${film.id }</p>
    <ul>
        <li>Title: ${film.title}</li>
        <li>Description: ${film.description}</li>
        <li>Year: ${film.relYear}</li>
        <li>Language identifier: ${film.lanId}</li>
        <li>Rental Duration: ${film.renDur} days</li>
        <li>Rental Rate: $ ${film.renRat}</li>
        <li>Film Length: ${film.length} minutes</li>
        <li>Replacement Cost: $ ${film.repCost}</li>
        <li>Rating: ${film.rating}</li>
        <li>Special Features: ${film.specFeat}</li>
    
        </ul>
       <%--  <li>${film.category}</li> --%>


    </c:when>
    <c:otherwise>
      <p>No Film Found</p>
    </c:otherwise>
  </c:choose>
<%-- <p>
<ul>
<li>${film.id }</li>
<li>${film.title }</li>
<li>${film.description }</li>
<li>${film.relYear }</li>
</ul>
</p> --%>
<form action ="editFilm.do?id=${film.id}"  method = "POST">
	<label for= "title">Title:</label>
     <input type="text" name="title" required="required" value="${ film.title} " />
     <br>
	<label for= "title">Description:</label>
    <input type="text" name="description" required="required" value="${film.description }" />
  <br>
   <label for= "title">Release Year:</label>
    <input type="number" name="relYear" required="required" value="${film.relYear }" /> 
    <br>
<label for= "title">Language ID:</label>
    <select name="lanId"> 
    <option value="1">English</option> 
    <option value="2">Italian</option> 
    <option value="3">Japanese</option> 
    <option value="4">Mandarin</option> 
    <option value="5">French</option> 
    <option value="6">German</option>
    </select>
    <br>
    <label for= "title">Rental Duration:</label>
    <input type="number" name="renDur" required="required" value="${film.renDur }" />
    <br>
 <label for= "title">Rental Rate:</label>
    <select name="renRat">
    <option value="3.99">3.99</option> 
    <option value="5.99">5.99</option> 
    <option value="7.99">7.99</option> 
    </select>
    <br>
    <label for= "title">Film Length:</label>
    <input type="number" name="length" required="required" value="${film.length }" />
    <br>
   <label for= "title">Replacement Cost:</label>
     <select name="repCost">
    <option value="3.99">3.99</option> 
    <option value="5.99">5.99</option> 
    <option value="7.99">7.99</option> 
    </select>
    <br>
     <label for= "title">Rating:</label>
    <select name="rating">
    <option value="G">G</option> 
    <option value="PG">PG</option> 
    <option value="PG-13">PG13</option> 
    <option value="R">R</option> 
    <option value="NC-17">NC17</option> 
    </select>
    <br>
     <div>Special Features <br>
   
   <input type="checkbox" id="specFeat" name="specFeat" value="Trailers" />
      <label for="trailers">Trailers</label>
    <br>
      <input type="checkbox" id="specFeat" name="specFeat" value="Commentaries" />
      <label for="commentaries">Commentaries</label>
    <br>
      <input type="checkbox" id="specFeat" name="specFeat" value="Deleted Scenes" />
      <label for="delScenes">Deleted Scenes</label>
   <br>
      <input type="checkbox" id="specFeat" name="specFeat" value="Behind the Scenes" />
      <label for="behScenes">Behind the Scenes</label>
    </div>
    
    <br>
    <input type= "submit" value= "Update Film">
    <br>
 </form>
    	<p>To view or delete your film return to Main Menu</p>
		<form action="index.html" method="GET">
		<input type="hidden" name="film" value= ${film.id } />
		<input type="submit" value="Main Menu" />
		</form>
</body>
</html>