package in.udaan17.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.model.Category;
import in.udaan17.android.model.Department;
import in.udaan17.android.model.Developer;
import in.udaan17.android.model.Event;

/**
 * Created by pranshu on 5/3/17.
 * For: udaan17-android-app
 * <p>
 * Parses json and converts it into java objects
 * Singleton class so, only one instance of it will exist in the entire program
 */

public class DataSingleton {

    private static DataSingleton instance;
    
    private List<Department> departments;
    private List<Developer> developers;
    private List<Event> nonTechList;
    private List<Event> culturalList;
    private List<Category> teamUdaan;


    /**
     * constructor for data singleton
     *
     * @param context
     * @throws JSONException
     */
    private DataSingleton(Context context) throws JSONException {

        /**
         * Open shared preferences and get the json data
         */
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        String stringData = sharedPreferences.getString(context.getString(R.string.prefs_event_data_json), "");
        String developersData = sharedPreferences.getString(context.getString(R.string.prefs_developer_data_json), "");
        String teamUdaanData = sharedPreferences.getString(context.getString(R.string.prefs_team_udaan_data_json), "");

        JSONObject data = new JSONObject(stringData);
        JSONArray developers = new JSONArray(developersData);
        JSONArray teamUdaan = new JSONArray(teamUdaanData);
    
        this.parseAndLoadData(data, developers, teamUdaan);
    }

    public static DataSingleton getInstance(Activity activity) throws JSONException {
        if (DataSingleton.instance == null) {
            DataSingleton.instance = new DataSingleton(activity);
        }
        return DataSingleton.instance;

    }
    
    
    private void parseAndLoadData(JSONObject data, JSONArray developers, JSONArray teamUdaan) throws JSONException {
        Gson gson = new Gson();
        
        this.departments = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("tech").toString(), Department[].class)));
        this.nonTechList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("nonTech").toString(), Event[].class)));
        this.culturalList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("cultural").toString(), Event[].class)));
        this.developers = new ArrayList<>(Arrays.asList(gson.fromJson(developers.toString(), Developer[].class)));
        this.teamUdaan = new ArrayList<>(Arrays.asList(gson.fromJson(teamUdaan.toString(), Category[].class)));
    }

    public List<Department> getDepartmentsList() {
        return departments;
    }

    public List<Developer> getDevelopersList() {
        return developers;
    }

    public List<Event> getNonTechList() {
        return nonTechList;
    }

    public List<Event> getCulturalList() {
        return culturalList;
    }
    
    public List<Category> getTeamUdaan() {
        return teamUdaan;
    }
}
