<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--c:redirect url="/" />-->


<form action="<c:url value="/signin/facebook" />" method="POST">
    <input type="hidden" name="scope" value="email,user_about_me,user_birthday,user_religion_politics,user_website,user_relationship_details,user_education_history,user_relationships,user_hometown,user_likes" />
    <button type="submit">Sign in with Facebook</button>
</form>
