<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<c:url value="/resources/css/style.css" var="css" />


<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
	<title>..:: Optibuk.si ::..</title>
	<link rel="stylesheet" href="${css}" type="text/css">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />
</head>
<body>

<!-- FB Like button -->
<div id="fb-root"></div>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1&appId=410382149079596";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script>
    window.fbAsyncInit = function() {
        FB.init({
            appId : '410382149079596',
            status : true, // check login status
            cookie : true, // enable cookies to allow the server to access the session
            xfbml : true, // parse XFBML
            channelUrl : 'http://optibuk.si/channel', // channel.html file
            oauth : true // enable OAuth 2.0
        });
    }
</script>
<script>
    FB.Event.subscribe('edge.create', function(href, widget) {
        //do something with like...
        alert("Like button was clicked!");
    });
</script>




	<div id="header">
		<div>
			<div class="logo">
				<a href="<c:url value="/" />">Optibuk.si</a>
			</div>
			<ul id="navigation">
                <li style="margin-top: 70px;">
                        <a href="<c:url value="/signout" />">Odjava</a>
                </li>
                <li style="padding-left: 7px;">
                    <img src="<c:url value="/myProfileImage" />" class="img_header">
                </li>
			</ul>
		</div>
	</div>
    <div id="subheader"></div>
	<div id="contents">
        <h1>Personal data:</h1><br />
        Facebook ID: <b>${user.fbId}</b><br />
        Username: <b>${user.username}</b><br />
        Email: <b>${user.email}</b><br />
        Name: <b>${user.name}</b><br />
        About: <b>${user.about}</b><br />
        Birthday: <b>${user.birthday}</b><br />
        Gender: <b>${user.gender}</b><br />
        Political: <b>${user.political}</b><br />
        Relationship status: <b>${user.relationshipStatus}</b><br />
        Religion: <b>${user.religion}</b><br />
        Website: <b>${user.website}</b>

        <div class="hr"></div>
        <h1>Education:</h1><br />
        ${user.education}

        <div class="hr"></div>
        <h1>Hometown:</h1><br />
        ${user.hometown}

        <div class="hr"></div>
        <h1>Likes:</h1><br />
        ${user.allFacebookLikes}

        <div class="hr"></div>
        <h1>Friends:</h1><br />
        ${user.allFacebookFriends}
	</div>
	<div id="footer">
        <div>
            <ul class="navigation_footer">
                <li>
                    <a href="#">Pravila uporabe</a>
                </li>
                <li>
                    <a href="#">O nas</a>
                </li>
                <li>
                    <a>© 2013 Optibuk.si. Vse pravice pridržane.</a>
                </li>
            </ul>

        </div>
	</div>
</body>
</html>