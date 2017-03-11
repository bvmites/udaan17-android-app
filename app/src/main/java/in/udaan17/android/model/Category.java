package in.udaan17.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pranshu on 11/3/17.
 */

public class Category {

    @SerializedName("category")
    private String Categoryname;

    @SerializedName("members")
    private List<Member> members;

    public Category(String categoryname, List<Member> members) {
        Categoryname = categoryname;
        this.members = members;
    }

    public String getCategoryname() {
        return Categoryname;
    }

    public List<Member> getMembers() {
        return members;
    }
}
