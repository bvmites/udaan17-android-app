package in.udaan17.android.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

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
        return eventDescription.trim();
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
    
    public List<String> getRounds() {
        return rounds;
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
    
    public String getShortDescription() {
        String shortDescription;
        
        if (this.getEventDescription().length() <= 50) {
            shortDescription = this.getEventDescription().trim();
        } else {
            shortDescription = this.getEventDescription().trim().substring(0, 47) + "...";
        }
        
        return shortDescription;
    }
    
    public String getRoundsDescription() {
        String roundsDescription = "";
        
        if (this.rounds != null) {
            for (int i = 0; i < this.rounds.size(); i++) {
                String roundInfo = this.rounds.get(i);
                if (roundInfo != null && roundInfo.trim().length() > 0) {
                    if (i > 0) {
                        roundsDescription += "\n\n";
                    }
                    roundsDescription += String.format(Locale.getDefault(), "Round %d:\n%s", (i + 1), roundInfo);
                }
            }
        }
        
        return roundsDescription;
    }
    
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
