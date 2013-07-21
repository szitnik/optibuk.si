<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<center>
    <h1>OPTIBUK.SI</h1>
<form action="<c:url value="/signin/facebook" />" method="POST">
    <input type="hidden" name="scope" value="email,user_about_me,user_birthday,user_religion_politics,user_website,user_relationship_details,user_education_history,user_relationships,user_hometown,user_likes" />
    <button type="submit"><img src="/resources/images/fblogin.png" /></button>
</form>
</center>
