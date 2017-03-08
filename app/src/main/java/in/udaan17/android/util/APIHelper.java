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
    
    public static final String baseUrl = "http://192.168.1.102:8000";
    
    public static final String api_endpoint_info = "/event-data.json";
    public static final String api_endpoint_developers = "/developers.json";


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
}
