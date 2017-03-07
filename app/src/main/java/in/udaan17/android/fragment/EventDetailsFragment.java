package in.udaan17.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.udaan17.android.R;
import in.udaan17.android.model.Event;

/**
 * Created by pranshu on 7/3/17.
 */

public class EventDetailsFragment extends Fragment {
    View rootView;
    
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
    
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.event_details_fragment, container, false);
        
        this.event = Event.parseJson(this.getArguments().getString(this.getString(R.string.activity_key_event_data)));
        
        this.initializeElements();
        
        this.populateUI();
        
        return this.rootView;
    }
    
    private void initializeElements() {
        this.textViewName = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_name);
        this.spaceNameDescription = (Space) this.rootView.findViewById(R.id.space_event_details_name_description);
        this.textViewDescriptionLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_description_label);
        this.textViewDescription = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_description);
        this.spaceDescriptionParticipants = (Space) this.rootView.findViewById(R.id.space_event_details_description_participants);
        this.textViewParticipantsLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_participants_label);
        this.textViewParticipants = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_participants);
        this.spaceParticipantsFees = (Space) this.rootView.findViewById(R.id.space_event_details_participants_fees);
        this.textViewFeesLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_fees_label);
        this.textViewFees = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_fees);
        this.spaceFeesRoundsInfo = (Space) this.rootView.findViewById(R.id.space_event_details_fees_round_info);
        this.textViewRoundsInfoLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_info_label);
//        this.spaceRoundsInfoRound1 = (Space) this.rootView.findViewById(R.id.space_event_details_round_info_round_1);
//        this.textViewRound1Label = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_1_label);
//        this.textViewRound1 = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_1);
//        this.spaceRound1Round2 = (Space) this.rootView.findViewById(R.id.space_event_details_round_1_round_2);
//        this.textViewRound2Label = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_2_label);
//        this.textViewRound2 = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_2);
//        this.spaceRound2Round3 = (Space) this.rootView.findViewById(R.id.space_event_details_round_2_round_3);
//        this.textViewRound3Label = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_3_label);
//        this.textViewRound3 = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_3);
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
}
