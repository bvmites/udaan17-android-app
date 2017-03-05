package udaan17.in.udaan17androidapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pranshu on 5/3/17.
 * For: udaan17-android-app
 */

public class Category {

    @SerializedName("name")
    private String name;

    @SerializedName("alias")
    private String alias;

    public Category(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
