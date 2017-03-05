package udaan17.in.udaan17androidapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pranshu on 5/3/17.
 * For: udaan17-android-app
 */

public class Department {

    //An annotation that indicates this member should be serialized to JSON
    // with the provided name value as its field name.
    // Helps gson detect by what name is the object referred to in json
    // and to use that while filling in values.
    @SerializedName("alias")
    private String alias;

    @SerializedName("name")
    private String name;

    @SerializedName("events")
    private List<Event> events;

    @SerializedName("branchHeads")
    private List<Manager> branchHeads;

    @SerializedName("coheads")
    private List<Manager> coheads;

    public Department(String alias, String name, List<Event> events, List<Manager> branchHeads, List<Manager> coheads) {
        this.alias = alias;
        this.name = name;
        this.events = events;
        this.branchHeads = branchHeads;
        this.coheads = coheads;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Manager> getBranchHeads() {
        return branchHeads;
    }

    public void setBranchHeads(List<Manager> branchHeads) {
        this.branchHeads = branchHeads;
    }

    public List<Manager> getCoheads() {
        return coheads;
    }

    public void setCoheads(List<Manager> coheads) {
        this.coheads = coheads;
    }
}
