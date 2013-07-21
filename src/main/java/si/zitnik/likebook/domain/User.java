package si.zitnik.likebook.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 4/1/13
 * Time: 12:21 AM
 * To change this template use Campaign | Settings | Campaign Templates.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String fbId;
    private String username;
    private String email;
    private String name;
    private String about;
    private String birthday;
    private String gender;
    private String political;
    private String relationshipStatus;
    private String religion;
    private String website;
    @Type(type = "text")
    private String education;
    @Type(type = "text")
    private String hometown;
    @Type(type = "text")
    private String allFacebookLikes;
    @Type(type = "text")
    private String allFacebookFriends;

    public User() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getAllFacebookLikes() {
        return allFacebookLikes;
    }

    public void setAllFacebookLikes(String allFacebookLikes) {
        this.allFacebookLikes = allFacebookLikes;
    }

    public String getAllFacebookFriends() {
        return allFacebookFriends;
    }

    public void setAllFacebookFriends(String allFacebookFriends) {
        this.allFacebookFriends = allFacebookFriends;
    }
}
