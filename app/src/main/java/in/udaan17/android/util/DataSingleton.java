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

    private List<Category> categoryList;
    private List<Department> departmentsList;
    private List<Developer> developersList;
    private List<Event> nonTechList;
    private List<Event> culturalList;


    /**
     * constructor for data singleton
     *
     * @param activity
     * @throws JSONException
     */
    private DataSingleton(Activity activity) throws JSONException {

        /**
         * Open shared preferences and get the json data
         */
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        String stringData = sharedPreferences.getString(activity.getString(R.string.prefs_data_json), "");
        String developersData = sharedPreferences.getString(activity.getString(R.string.prefs_developer_data_json), "");

        JSONObject data = new JSONObject(stringData);
        JSONArray developers = new JSONArray(developersData);

        this.parseAndLoadData(data, developers);
    }

    public static DataSingleton getInstance(Activity activity) throws JSONException {
        if (DataSingleton.instance == null) {
            DataSingleton.instance = new DataSingleton(activity);
        }
        return DataSingleton.instance;

    }


    private void parseAndLoadData(JSONObject data, JSONArray developers) throws JSONException {
        Gson gson = new Gson();

        //this.categoryList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("categories").toString(), Category[].class)));
        this.departmentsList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("tech").toString(), Department[].class)));
        this.nonTechList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("nonTech").toString(), Event[].class)));
        this.culturalList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("cultural").toString(), Event[].class)));
        this.developersList = new ArrayList<>(Arrays.asList(gson.fromJson(developers.toString(), Developer[].class)));

    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Department> getDepartmentsList() {
        return departmentsList;
    }

    public List<Developer> getDevelopersList() {
        return developersList;
    }

    public List<Event> getNonTechList() {
        return nonTechList;
    }

    public List<Event> getCulturalList() {
        return culturalList;
    }
}
