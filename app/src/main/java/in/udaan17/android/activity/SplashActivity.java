package in.udaan17.android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import in.udaan17.android.R;
import in.udaan17.android.databinding.ActivitySplashBinding;
import in.udaan17.android.util.APIHelper;
import in.udaan17.android.util.Helper;
import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener, ParticleView.ParticleAnimListener {
  private ActivitySplashBinding dataBinding;
  
  private volatile boolean dataFetched = false;
  private volatile boolean animationComplete = false;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
  }
  
  @Override
  protected void onStart() {
    super.onStart();
    
    this.initializeElements();
    
    if (Helper.hasNetworkConnection(this)) {
      APIHelper.fetchData(this, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
          try {
            SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(SplashActivity.this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(SplashActivity.this.getString(R.string.prefs_event_data_json), response.toString()).apply();
            APIHelper.fetchDeveloperData(SplashActivity.this, new Response.Listener<JSONArray>() {
              @Override
              public void onResponse(JSONArray response) {
                SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(SplashActivity.this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(SplashActivity.this.getString(R.string.prefs_developer_data_json), response.toString()).apply();
                APIHelper.fetchTeamUdaanData(SplashActivity.this, SplashActivity.this, SplashActivity.this);
              }
            }, SplashActivity.this);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Log.d("NETWORK", "Error in response");
        }
      });
    } else if (!this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE).contains(this.getString(R.string.prefs_event_data_json))) {
      Helper.showNetworkAlertPopup(this);
    } else {
      this.dataFetched = true;
      if (animationComplete) {
        MainActivity.startActivity(this);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
      }
    }
  }
  
  private void initializeElements() {
    this.dataBinding
        .udaanLogo
        .startAnim();
    this.dataBinding
        .udaanLogo
        .setOnParticleAnimListener(this);
  }
  
  @Override
  public void onErrorResponse(VolleyError error) {
    Log.e("DEVELOPER_FETCH_ERROR", error.networkResponse.toString());
  }
  
  @Override
  public void onResponse(JSONArray response) {
    dataFetched = true;
    SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
    sharedPreferences.edit().putString(this.getString(R.string.prefs_team_udaan_data_json), response.toString()).apply();
    if (animationComplete) {
      MainActivity.startActivity(this);
      overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
  }
  
  @Override
  public void onAnimationEnd() {
    animationComplete = true;
    if (dataFetched) {
      MainActivity.startActivity(this);
      overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    } else {
      TransitionManager.beginDelayedTransition((ViewGroup) this.dataBinding.getRoot());
      this.dataBinding
          .containerProgress
          .setVisibility(View.VISIBLE);
    }
  }
}