package in.udaan17.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pranshu on 5/3/17.
 * For: udaan17-android-app
 */

public class Manager {

    @SerializedName("mobile")
    private String contactInfo;

    @SerializedName("name")
    private String name;

    public Manager(String contactInfo, String name) {
        this.contactInfo = contactInfo;
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
