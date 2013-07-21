package si.zitnik.likebook.controller;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.zitnik.likebook.domain.User;
import si.zitnik.likebook.repository.UserRepository;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 4/12/13
 * Time: 6:34 PM
 * To change this template use Campaign | Settings | Campaign Templates.
 */
@Controller
public class FacebookController {

    @Inject
    private Facebook facebook;

    @Resource
    private UserRepository userRepository;


    @RequestMapping(value="/channel", method= RequestMethod.GET)
    public String channel(Model model) {
        return "channel";
    }

    @RequestMapping(value="/myProfileImage", method = RequestMethod.GET)
    public String unlike(RequestMethod model, HttpServletResponse response) {
        byte[] image = facebook.userOperations().getUserProfileImage();

        try {
            OutputStream out = response.getOutputStream();
            IOUtils.copy(new ByteArrayInputStream(image), out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/signin", method=RequestMethod.GET)
    public void signin() {
    }

    @RequestMapping(value="/filldata", method = RequestMethod.GET)
    public String filldata(Model model) {
        createUser(facebook);
        return "redirect:/";
    }

    private User createUser(Facebook fbApi) {
        FacebookProfile userProfile = fbApi.userOperations().getUserProfile();
        User user = new User();

        user.setFbId(userProfile.getId()); //id
        user.setAbout(userProfile.getAbout()); //user_about_me
        user.setBirthday(userProfile.getBirthday()); //user_birthday
        user.setEmail(userProfile.getEmail()); //email
        if (userProfile.getName() == null || userProfile.getName().isEmpty()) { //name
            user.setName(userProfile.getFirstName()+ " " + userProfile.getMiddleName() + " " + userProfile.getLastName());
        } else {
            user.setName(userProfile.getName());
        }
        user.setGender(userProfile.getGender()); //gender
        user.setPolitical(userProfile.getPolitical()); //user_religion_politics
        user.setRelationshipStatus(userProfile.getRelationshipStatus()); //user_relationships,user_relationship_details
        user.setReligion(userProfile.getReligion()); //user_religion_politics
        user.setUsername(userProfile.getUsername()); //username
        user.setWebsite(userProfile.getWebsite());  //user_website


        List<EducationEntry> educationList = userProfile.getEducation(); //user_education_history
        StringBuffer sb = new StringBuffer("<ul>");
        for (EducationEntry e : educationList) {
            String year = "";
            if (e.getYear() != null) {
                year = e.getYear().getName();
            }
            sb.append(String.format("<li>FB ID: <b>%s</b><br />Name: <b>%s</b><br />Type: <b>%s</b><br />Year: <b>%s</b></li>",
                    e.getSchool().getId(),
                    e.getSchool().getName(),
                    e.getType(),
                    year));
        }
        sb.append("</ul>");
        user.setEducation(sb.toString());

        //hometown
        user.setHometown(String.format("<ul><li>FB ID: <b>%s</b><br />Name: <b>%s</b></li></ul>",
                userProfile.getHometown().getId(),
                userProfile.getHometown().getName()));


        PagedList<Page> pagesLiked = fbApi.likeOperations().getPagesLiked(); //user_likes
        sb = new StringBuffer("<ul>");
        for (Page p : pagesLiked) {
            try {
                sb.append(String.format("<li><img src=\"http://graph.facebook.com/%s/picture\" align=\"middle\"/><br />FB ID: <b>%s</b><br />Name: <b>%s</b><br />Website: <b>%s</b><br />Likes: <b>%s</b></li>",
                    p.getId(),
                    p.getId(),
                    p.getName(),
                    p.getWebsite(),
                    p.getLikes()));

            } catch (Exception e) {}
        }
        sb.append("</ul>");
        user.setAllFacebookLikes(sb.toString());

        PagedList<Reference> friends = fbApi.friendOperations().getFriends(); //
        sb = new StringBuffer();
        for (Reference r : friends) {
            sb.append(String.format("<img src=\"http://graph.facebook.com/%s/picture\" align=\"middle\"/>&nbsp;&nbsp;",
                    r.getId()));
        }
        user.setAllFacebookFriends(sb.toString());

        return userRepository.save(user);
    }
}
