package in.udaan17.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import in.udaan17.android.R;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.VolleySingleton;

public class SplashActivity extends AppCompatActivity {

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
            String timeStampUrl = this.getString(R.string.api_endpoint_timestamp);
            JsonObjectRequest timeStampRequest = new JsonObjectRequest(Request.Method.GET, timeStampUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        float lastModified = Float.parseFloat(response.getString("message"));
                        SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(SplashActivity.this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);

                        if (sharedPreferences.getFloat(SplashActivity.this.getString(R.string.prefs_last_modified), 0) < lastModified) {
                            sharedPreferences.edit().putFloat(SplashActivity.this.getString(R.string.prefs_last_modified), lastModified).apply();

                            String dataUrl = SplashActivity.this.getString(R.string.api_endpoint_info);

                            JsonObjectRequest dataRequest = new JsonObjectRequest(Request.Method.GET, dataUrl, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    SplashActivity.this.successfulDataResponse(response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("Data Request Error", String.valueOf(error.networkResponse));
                                }
                            });
                            VolleySingleton.getinstance(SplashActivity.this).addToRequestQueue(dataRequest);
                        } else {
                            SplashActivity.this.startMainActivity();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Timestamp request error", String.valueOf(error.networkResponse));
                }
            });

            VolleySingleton.getinstance(this).addToRequestQueue(timeStampRequest);
        } else if (!this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE).contains("data_json")) {
            Helper.showNetworkAlertPopup(this);
        } else {
            this.startMainActivity();
        }

    }

    private void initializeElements() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar_splash_screen);
    }

    private void startMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }

    private void successfulDataResponse(JSONObject response) {
        try {
            SharedPreferences sharedPreferances = this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
            sharedPreferances.edit().putString(this.getString(R.string.prefs_data_json), response.getJSONObject("message").toString()).apply();
            this.startMainActivity();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
