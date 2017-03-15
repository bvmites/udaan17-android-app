package in.udaan17.android.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by pranshu on 7/3/17.
 */

public final class APIHelper {
    
    public static final String baseUrl = "https://udaan17.in";
    
    public static final String api_endpoint_info = "/api/data.json";
    public static final String api_endpoint_developers = "/api/developers.json";
    public static final String api_endpoint_team_udaan = "/api/teamUdaan.json";


    public static void fetchData(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {

        String dataUrl = APIHelper.baseUrl + APIHelper.api_endpoint_info;
    
        JsonObjectRequest dataRequest = new JsonObjectRequest(Request.Method.GET, dataUrl, new JSONObject(), responseListener, errorListener);

        VolleySingleton.getinstance(context).addToRequestQueue(dataRequest);

    }

    public static void fetchDeveloperData(Context context, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {

        String developerUrl = APIHelper.baseUrl + APIHelper.api_endpoint_developers;
        JsonArrayRequest developerDataRequest = new JsonArrayRequest(Request.Method.GET, developerUrl, new JSONArray(), responseListener, errorListener);

        VolleySingleton.getinstance(context).addToRequestQueue(developerDataRequest);
    }
    
    public static void fetchTeamUdaanData(Context context, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        String teamUdaanUrl = APIHelper.baseUrl + APIHelper.api_endpoint_team_udaan;
        JsonArrayRequest teamUdaanDataRequest = new JsonArrayRequest(Request.Method.GET, teamUdaanUrl, new JSONArray(), responseListener, errorListener);
        
        VolleySingleton.getinstance(context).addToRequestQueue(teamUdaanDataRequest);
    }
}
