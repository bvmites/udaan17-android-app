package in.udaan17.android.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pranshu on 5/3/17.
 * For: udaan17-android-app
 */

public class Event {

    @SerializedName("name")
    private String eventName;

    @SerializedName("description")
    private String eventDescription;

    @SerializedName("participants")
    private String participants;

//    @SerializedName("round1Description")
//    private String round1Description;
//
//    @SerializedName("round2Description")
//    private String round2Description;
//
//    @SerializedName("round3Description")
//    private String round3Description;

    @SerializedName("rounds")
    private List<String> rounds;

    @SerializedName("fees")
    private String fees;

    @SerializedName("managers")
    private List<Manager> eventManagers;


    public Event(String eventName, String eventDescription, String participants, List<String> rounds, String fees, List<Manager> eventManagers) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.participants = participants;
        this.rounds = rounds;
        this.fees = fees;
        this.eventManagers = eventManagers;
    }

    /**
     * Dunno what this does.
     *
     * @param jsonEvent
     * @return an event
     */
    public static Event parseJson(String jsonEvent) {
        Gson gson = new Gson();
        Event event = gson.fromJson(jsonEvent, Event.class);
        return event;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public void setRounds(List<String> rounds) {
        this.rounds = rounds;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public List<Manager> getEventManagers() {
        return eventManagers;
    }

    public void setEventManagers(List<Manager> eventManagers) {
        this.eventManagers = eventManagers;
    }
}
