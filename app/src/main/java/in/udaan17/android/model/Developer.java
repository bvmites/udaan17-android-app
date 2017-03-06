package in.udaan17.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pranshu on 5/3/17.
 * For: udaan17-android-app
 */

public class Developer {

    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;

    @SerializedName("github")
    private String github;

    @SerializedName("title")
    private String title;

    @SerializedName("color")
    private String color;

    public Developer(String name, String mobile, String email, String github, String title, String color) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.github = github;
        this.title = title;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
