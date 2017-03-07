package in.udaan17.android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import in.udaan17.android.R;
import in.udaan17.android.util.APIHelper;
import in.udaan17.android.util.Helper;

public class SplashActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(this.getString(R.string.title_activity_splash));

        this.initializeElements();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Helper.hasNetworkConnection(this)) {
            //String timeStampUrl = this.getString(R.string.api_endpoint_timestamp);
            /*JsonObjectRequest timeStampRequest = new JsonObjectRequest(Request.Method.GET, timeStampUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        float lastModified = Float.parseFloat(response.getString("message"));
                        SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(SplashActivity.this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);

                        if (sharedPreferences.getFloat(SplashActivity.this.getString(R.string.prefs_last_modified), 0) < lastModified) {
                            sharedPreferences.edit().putFloat(SplashActivity.this.getString(R.string.prefs_last_modified), lastModified).apply();
    */
//                            String dataUrl = APIHelper.api_endpoint_info;
//                            String developerUrl = APIHelper.api_endpoint_developers;
//                            JsonObjectRequest dataRequest = new JsonObjectRequest(Request.Method.GET, dataUrl, new Response.Listener<JSONObject>() {
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    SplashActivity.this.successfulDataResponse(response);
//                                }
//                            }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    Log.e("Data Request Error", String.valueOf(error.networkResponse));
//                                }
//                            });
//
//                            VolleySingleton.getinstance(SplashActivity.this).addToRequestQueue(dataRequest);

            APIHelper.fetchData(this, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(SplashActivity.this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString(SplashActivity.this.getString(R.string.prefs_data_json), response.toString()).apply();
                        APIHelper.fetchDeveloperData(SplashActivity.this, SplashActivity.this, SplashActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


            //                   } else {
            //                       SplashActivity.this.startMainActivity();
            //                   }
            //               } catch (JSONException e) {
            //                  e.printStackTrace();
            //               }
            //           }
            //       }, new Response.ErrorListener() {
            //           @Override
            //           public void onErrorResponse(VolleyError error) {
            //               Log.e("Timestamp request error", String.valueOf(error.networkResponse));
            //           }
            //       });

            //       VolleySingleton.getinstance(this).addToRequestQueue(timeStampRequest);
        } else if (!this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE).contains("data_json")) {
            Helper.showNetworkAlertPopup(this);
        } else {
            MainActivity.startActivity(this);
        }

    }

    private void initializeElements() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar_splash_screen);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("DEVELOPER_FETCH_ERROR", error.networkResponse.toString());
    }

    @Override
    public void onResponse(JSONArray response) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(this.getString(R.string.prefs_developer_data_json), response.toString()).apply();
        MainActivity.startActivity(this);
    }
}
