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
	<title>..:: Lajkbuk.si ::..</title>
    <script src="<c:url value="/resources/jquery/jquery-1.9.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/spin.js" />"></script>
	<link rel="stylesheet" href="${css}" type="text/css">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />
</head>
<body>
<div id="progressWindow"></div>

<div id="fb-root"></div>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1&appId=208956702576053";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script>
    window.fbAsyncInit = function() {
        FB.init({
            appId : '208956702576053',
            status : true, // check login status
            cookie : true, // enable cookies to allow the server to access the session
            xfbml : true, // parse XFBML
            channelUrl : 'http://lajkbuk.si/channel', // channel.html file
            oauth : true // enable OAuth 2.0
        });
    }
</script>
<script>
    FB.Event.subscribe('edge.create', function(href, widget) {
        var opts = {
            lines: 13, // The number of lines to draw
            length: 20, // The length of each line
            width: 10, // The line thickness
            radius: 30, // The radius of the inner circle
            corners: 1, // Corner roundness (0..1)
            rotate: 0, // The rotation offset
            direction: 1, // 1: clockwise, -1: counterclockwise
            color: '#000', // #rgb or #rrggbb
            speed: 1, // Rounds per second
            trail: 60, // Afterglow percentage
            shadow: false, // Whether to render a shadow
            hwaccel: false, // Whether to use hardware acceleration
            className: 'spinner', // The CSS class to assign to the spinner
            zIndex: 2e9, // The z-index (defaults to 2000000000)
            top: 'auto', // Top position relative to parent in px
            left: 'auto' // Left position relative to parent in px
        };
        var target = document.getElementById('progressWindow');
        target.style = 'position: fixed; width: 100%; height: 100%; left: 0; top: 0; background: rgba(51,51,51,0.6); z-index: 10;';
        var spinner = new Spinner(opts).spin(target);

        $.post('http://lajkbuk.si/like/'+widget.dom.id);

        spinner.stop();
        target.style = 'z-index: -1;';
    });
</script>
<script>
    function showHide(id, visibility) {
        var iframe = document.getElementById(id);
            if (visibility) {
                $('.fbgumbek iframe').css('height', '25px');
                $('.fbgumbek iframe').css('display', 'inline');

                $('.fbgumbek span').css('height', '25px');
                $('.fbgumbek span').css('display', 'inline');

                $('.fbgumbek div').css('height', '25px');
                $('.fbgumbek div').css('display', 'inline');
            } else {
                $('.fbgumbek iframe').css('height', '0px');
                $('.fbgumbek iframe').css('display', 'none');

                $('.fbgumbek span').css('height', '0px');
                $('.fbgumbek span').css('display', 'none');

                $('.fbgumbek div').css('height', '0px');
                $('.fbgumbek div').css('display', 'none');
            }
    }
</script>





	<div id="header">
		<div>
			<div class="logo">
				<a href="<c:url value="/" />">&nbsp;</a>
			</div>
			<ul id="navigation">
				<li class="active" style="margin-top: 70px;">
					<a href="<c:url value="/" />">Domov</a>
				</li>
                <li class="sep" style="margin-top: 70px;"></li>
				<li style="margin-top: 70px;">
					<a href="#">O Lajkbuk.si</a>
				</li>
                <li class="sep" style="margin-top: 70px;"></li>
				<li style="margin-top: 70px;">
					<a href="#">Pretekle nagradne igre</a>
				</li>

                <c:if test="${isAuthenticated}">
                <li class="sep" style="margin-top: 70px;"></li>
                <li style="margin-top: 70px;">
                        <a href="<c:url value="/signout" />">Odjava</a>
                </li>
                <li style="padding-left: 7px;">
                    <img src="<c:url value="/myProfileImage" />" class="img_header">
                </li>
                </c:if>
                <c:if test="${!isAuthenticated}">
                <li style="padding-left: 7px; margin-top: 50px;">
                        <form action="<c:url value="/signin/facebook" />" method="POST">
                            <input type="hidden" name="scope" value="email,user_about_me,user_birthday,user_religion_politics,user_website,user_relationship_details,user_education_history,user_relationships,user_hometown,user_likes" />
                            <input type="image" src="<c:url value="/resources/images/fblogin.png" />" />
                        </form>
                </li>
                </c:if>
			</ul>
		</div>
	</div>
    <div id="subheader"></div>
	<div id="contents">
        <c:if  test="${!empty campaignList}">
                <c:forEach items="${campaignList}" var="campaign">
                    <div class="kampanja">

                    <div class="kampanja_left">
                        <img src="<c:url value="/image/" />${campaign.id}" class="img_campaign">
                    </div>

                    <div class="kampanja_right">
                        <h1>${campaign.title}</h1>
                        <div class="kampanja_right_hr"></div>
                        <div class="kampanja_text">${campaign.description}</div>
                        <div class="kampanja_right_hr"></div>
                        <div class="organizer_info">
                            Nagradno igro organizira <b>${campaign.customer.name}</b>, ${campaign.customer.address}, e-naslov: ${campaign.customer.email}.
                        </div>
                        <div class="bar">
                           <div class="bar_header"></div>
                           <div class="bar_empty"></div>
                           <div class="bar_full" style="width: ${campaign.currentLikes*400/campaign.targetLikes}px;"></div>
                        </div>
                        <div class="kampanja_status">
                            <div class="kampanja_status_first"><b>${campaign.currentLikes}</b> trenutno</div>
                            <div class="kampanja_status_2nd"><b><fmt:formatNumber value="${campaign.currentLikes*100.0/campaign.targetLikes}" maxFractionDigits="0"/></b>%</div>
                            <div class="kampanja_status_3rd"><b>${campaign.targetLikes}</b> cilj</div>
                        </div>

                        <c:if test="${isAuthenticated}">
                            <div class="kampanja_right_hr"></div>
                            <div class="kampanja_terms">
                                <input type="checkbox" onclick="showHide('${campaign.id}', checked);"> Strinjam se s <a href="<c:url value="/terms/" />${campaign.id}">pogoji in pravili</a> nagradne igre.
                                Vsi podatki, ki ste jih delili z aplikacijo, so lahko posredovani podjetju ${campaign.customer.name}.
                            </div>

                            <div class="fbgumbek">
                                <!--<div class="fb-like" id="%%%%%%{campaign.id}" data-href="%%%%{campaign.likeLink}" data-send="false" data-width="200" data-show-faces="false"></div>-->
                                <a href="${campaign.facebookAppLink}">Sodeluj</a>
                            </div>
                        </c:if>
                    </div>
                    </div>
                    <div class="kampanja_hr"></div>
                </c:forEach>
        </c:if>
        <c:if  test="${empty campaignList}">
            <div class="kampanja">
                <h1>Nagradne igre</h1>
                <div>
                    <p>
                        Trenutno ni na voljo nobene nagradne igre.
                    </p>
                </div>
            </div>
        </c:if>
	</div>
	<div id="footer">
        <div>
            <ul class="navigation_footer">
                <li>
                    <a href="#">Pravila uporabe</a>
                </li>
                <li>
                    <a href="<c:url value="/contact" />">O nas</a>
                </li>
                <li>
                    <a>© 2013 Lajkbuk.si. Vse pravice pridržane.</a>
                </li>
            </ul>

        </div>
		<div>
			<div id="connect">
				<a href="https://www.facebook.com/pages/Likebook/412974628764670" target="_blank" class="facebook"></a>
                <div class="fb-like" data-href="https://www.facebook.com/pages/Likebook/412974628764670" data-send="false" data-width="200" data-show-faces="false"></div>
			</div>
		</div>
	</div>
</body>
</html>