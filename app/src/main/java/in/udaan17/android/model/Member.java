package in.udaan17.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pranshu on 11/3/17.
 */

public class Member {

    @SerializedName("name")
    private String name;

    @SerializedName("name")
    private String title;

    public Member(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}

