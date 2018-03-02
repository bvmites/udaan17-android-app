package in.udaan17.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import in.udaan17.android.R;
import in.udaan17.android.adapter.ManagerAdapter;
import in.udaan17.android.databinding.ActivityEventDetailsBinding;
import in.udaan17.android.model.Event;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {
  private ActivityEventDetailsBinding dataBinding;
  
  private Event event;
  
  private String resourceName;
  
  public static void startActivity(Activity activity, String fileName, Event event) {
    Intent intent = new Intent(activity, EventDetailsActivity.class);
    intent.putExtra(activity.getString(R.string.activity_key_event_data), event.toString());
    intent.putExtra(activity.getString(R.string.activity_key_title_name), fileName);
    activity.startActivity(intent);
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_details);
    
    event = Event.parseJson(this.getIntent().getStringExtra(this.getString(R.string.activity_key_event_data)));
    resourceName = Helper.getResourceNameFromTitle(this.getIntent().getStringExtra(this.getString(R.string.activity_key_title_name)));
    
    this.initializeElements();
    this.populateUI();
  }
  
  private void initializeElements() {
    int resourceId = getResources().getIdentifier(this.resourceName + "land", "drawable", getPackageName());
    this.dataBinding
        .bannerImageView
        .setImageResource(resourceId);
    
    this.setSupportActionBar(this.dataBinding.toolbar);
    if (this.getSupportActionBar() != null) {
      ActionBar actionBar = this.getSupportActionBar();
      actionBar.setTitle(this.event.getEventName());
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    this.dataBinding
        .buttonContact
        .setOnClickListener(this);
    
    this.dataBinding
        .buttonContact
        .setBackgroundColor(ContextCompat.getColor(
            this,
            this.getResources().getIdentifier(
                "color_" + this.resourceName,
                "color", this.getPackageName()
            )
        ));
    
    Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/udaanRegular.ttf");
    
    this.dataBinding
        .description
        .setTypeface(customFont);
    this.dataBinding
        .participants
        .setTypeface(customFont);
    this.dataBinding
        .rounds
        .setTypeface(customFont);
    this.dataBinding
        .fees
        .setTypeface(customFont);
    this.dataBinding
        .prize
        .setTypeface(customFont);
  }
  
  private void populateUI() {
    if (this.event.getEventDescription() != null && this.event.getEventDescription().length() > 0) {
      this.dataBinding
          .description
          .setText(this.event.getEventDescription());
    } else {
      this.dataBinding
          .descriptionLabel
          .setVisibility(View.GONE);
      this.dataBinding
          .description
          .setVisibility(View.GONE);
    }
    
    if (this.event.getParticipants() != null && this.event.getParticipants().length() > 0) {
      this.dataBinding
          .participants
          .setText(this.event.getParticipants());
    } else {
      this.dataBinding
          .spaceDescriptionParticipants
          .setVisibility(View.GONE);
      this.dataBinding
          .participantsLabel
          .setVisibility(View.GONE);
      this.dataBinding
          .participants
          .setVisibility(View.GONE);
    }
    
    if (this.event.getRoundsDescription().length() > 0) {
      this.dataBinding
          .rounds
          .setText(this.event.getRoundsDescription());
    } else {
      this.dataBinding
          .spaceParticipantsRounds
          .setVisibility(View.GONE);
      this.dataBinding
          .roundsLabel
          .setVisibility(View.GONE);
      this.dataBinding
          .rounds
          .setVisibility(View.GONE);
    }
    
    if (this.event.getFees() != null && this.event.getFees().length() > 0) {
      this.dataBinding
          .fees
          .setText(this.getString(R.string.symbol_rupee) + " " + this.event.getFees());
    } else {
      this.dataBinding
          .spaceRoundsFees
          .setVisibility(View.GONE);
      this.dataBinding
          .feesLabel
          .setVisibility(View.GONE);
      this.dataBinding
          .fees
          .setVisibility(View.GONE);
    }
    
    if (!(this.event.getEventManagers() != null && this.event.getEventManagers().size() > 0)) {
      this.dataBinding
          .spacePrizesContact
          .setVisibility(View.GONE);
      this.dataBinding
          .buttonContact
          .setVisibility(View.GONE);
    }
    
    if (this.event.getPrizeDescription(this.getString(R.string.symbol_rupee)).length() > 0) {
      this.dataBinding
          .prize
          .setText(this.event.getPrizeDescription(this.getString(R.string.symbol_rupee)));
    } else {
      this.dataBinding
          .spaceFeesPrizes
          .setVisibility(View.GONE);
      this.dataBinding
          .prizeLabel
          .setVisibility(View.GONE);
      this.dataBinding
          .prize
          .setVisibility(View.GONE);
    }
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        this.onBackPressed();
        return true;
    }
    
    return super.onOptionsItemSelected(item);
  }
  
  @Override
  public void onClick(View view) {
    int id = view.getId();
    switch (id) {
      case R.id.button_contact:
        this.showManagersDialog();
        break;
    }
  }
  
  private void showManagersDialog() {
    ManagerAdapter adapter = new ManagerAdapter(this.event.getEventManagers(),
        this.resourceName,
        this,
        new ListItemClickCallBack() {
          @Override
          public void onItemClick(int position, int viewId) {
            Helper.makeCall(
                EventDetailsActivity.this.event.getEventManagers().get(position).getContactInfo(),
                EventDetailsActivity.this
            );
          }
        }
    );
    
    MaterialDialog dialog =
        new MaterialDialog.Builder(this)
            .title("Event Managers")
            .adapter(adapter, new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
            .cancelable(true)
            .build();
    
    dialog.show();
  }
}
