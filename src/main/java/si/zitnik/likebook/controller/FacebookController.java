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
import si.zitnik.likebook.util.SignInUtils;

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

    @Inject
    private ConnectionRepository connectionRepository;

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

    @RequestMapping(value="/filldata/{fbId}", method = RequestMethod.GET)
    public String filldata(Model model, @PathVariable("fbId") String fbId) {
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
        JSONArray jsonArray = new JSONArray();
        for (EducationEntry e : educationList) {
            try {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("id", e.getSchool().getId());
                jsonObj.put("name", e.getSchool().getName());
                jsonObj.put("type", e.getType());
                jsonObj.put("year", e.getYear().getName());
                jsonArray.put(jsonObj);
            } catch (Exception e1) {}
        }
        user.setEducation(jsonArray.toString());


        try {
            JSONObject hometownJsonObj = new JSONObject(); //user_hometown
            hometownJsonObj.put("id", userProfile.getHometown().getId());
            hometownJsonObj.put("name", userProfile.getHometown().getName());
            user.setHometown(hometownJsonObj.toString());
        } catch (Exception e) {}


        PagedList<Page> pagesLiked = fbApi.likeOperations().getPagesLiked(); //user_likes
        jsonArray = new JSONArray();
        for (Page p : pagesLiked) {
            try {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("id", p.getId());
                jsonObj.put("name", p.getName());
                jsonObj.put("website", p.getWebsite());
                jsonObj.put("likes", p.getLikes());
                jsonArray.put(jsonObj);
            } catch (Exception e) {}
        }
        user.setAllFacebookLikes(jsonArray.toString());

        PagedList<Reference> friends = fbApi.friendOperations().getFriends(); //
        jsonArray = new JSONArray();
        for (Reference r : friends) {
            try {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("id", r.getId());
                jsonObj.put("name", r.getName());
                jsonArray.put(jsonObj);
            } catch (Exception e) {}
        }
        user.setAllFacebookFriends(jsonArray.toString());

        return userRepository.save(user);
    }
}
