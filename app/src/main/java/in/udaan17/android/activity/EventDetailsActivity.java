package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.v4.widget.Space;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import in.udaan17.android.R;
import in.udaan17.android.model.Event;

public class EventDetailsActivity extends AppCompatActivity {
  
  private Event event;
  
  private AppCompatTextView textViewName;
  private Space spaceNameDescription;
  private AppCompatTextView textViewDescriptionLabel;
  private AppCompatTextView textViewDescription;
  private Space spaceDescriptionParticipants;
  private AppCompatTextView textViewParticipantsLabel;
  private AppCompatTextView textViewParticipants;
  private Space spaceParticipantsFees;
  private AppCompatTextView textViewFeesLabel;
  private AppCompatTextView textViewFees;
  private Space spaceFeesRoundsInfo;
  private AppCompatTextView textViewRoundsInfoLabel;
  private Space spaceRoundsInfoRound1;
  private AppCompatTextView textViewRound1Label;
  private AppCompatTextView textViewRound1;
  private Space spaceRound1Round2;
  private AppCompatTextView textViewRound2Label;
  private AppCompatTextView textViewRound2;
  private Space spaceRound2Round3;
  private AppCompatTextView textViewRound3Label;
  private AppCompatTextView textViewRound3;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_event_details);
    
    event = Event.parseJson(this.getIntent().getStringExtra(this.getString(R.string.activity_key_event_data)));
    
    this.initializeElements();
  
    this.populateUI();
  }
  
  private void initializeElements() {
    Toolbar toolbar = (Toolbar) this.findViewById(R.id.appbar_toolbar);
    this.setSupportActionBar(toolbar);
    if (this.getSupportActionBar() != null) {
      ActionBar actionBar = this.getSupportActionBar();
      actionBar.setTitle(R.string.app_name);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  
    this.textViewName = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_name);
    this.spaceNameDescription = (Space) this.findViewById(R.id.space_event_details_name_description);
    this.textViewDescriptionLabel = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_description_label);
    this.textViewDescription = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_description);
    this.spaceDescriptionParticipants = (Space) this.findViewById(R.id.space_event_details_description_participants);
    this.textViewParticipantsLabel = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_participants_label);
    this.textViewParticipants = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_participants);
    this.spaceParticipantsFees = (Space) this.findViewById(R.id.space_event_details_participants_fees);
    this.textViewFeesLabel = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_fees_label);
    this.textViewFees = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_fees);
    this.spaceFeesRoundsInfo = (Space) this.findViewById(R.id.space_event_details_fees_round_info);
    this.textViewRoundsInfoLabel = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_round_info_label);
//        this.spaceRoundsInfoRound1 = (Space) this.findViewById(R.id.space_event_details_round_info_round_1);
//        this.textViewRound1Label = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_round_1_label);
//        this.textViewRound1 = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_round_1);
//        this.spaceRound1Round2 = (Space) this.findViewById(R.id.space_event_details_round_1_round_2);
//        this.textViewRound2Label = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_round_2_label);
//        this.textViewRound2 = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_round_2);
//        this.spaceRound2Round3 = (Space) this.findViewById(R.id.space_event_details_round_2_round_3);
//        this.textViewRound3Label = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_round_3_label);
//        this.textViewRound3 = (AppCompatTextView) this.findViewById(R.id.text_view_event_details_round_3);
  }
  
  private void populateUI() {
    if (this.event.getEventName() != null && this.event.getEventName().length() > 0) {
      this.textViewName.setText(this.event.getEventName());
    } else {
      this.textViewName.setVisibility(View.GONE);
      this.spaceNameDescription.setVisibility(View.GONE);
    }
    
    if (this.event.getEventDescription() != null && this.event.getEventDescription().length() > 0) {
      this.textViewDescription.setText(this.event.getEventDescription());
    } else {
      this.spaceNameDescription.setVisibility(View.GONE);
      this.textViewDescriptionLabel.setVisibility(View.GONE);
      this.textViewDescription.setVisibility(View.GONE);
    }
    
    if (this.event.getParticipants() != null && this.event.getParticipants().length() > 0) {
      this.textViewParticipants.setText(this.event.getParticipants());
    } else {
      this.spaceDescriptionParticipants.setVisibility(View.GONE);
      this.textViewParticipantsLabel.setVisibility(View.GONE);
      this.textViewParticipants.setVisibility(View.GONE);
    }
    
    if (this.event.getFees() != null && this.event.getFees().length() > 0) {
      this.textViewFees.setText(this.getString(R.string.symbol_rupee) + " " + this.event.getFees());
    } else {
      this.spaceParticipantsFees.setVisibility(View.GONE);
      this.textViewFeesLabel.setVisibility(View.GONE);
      this.textViewFees.setVisibility(View.GONE);
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
}
