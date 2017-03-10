package in.udaan17.android.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.model.Event;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 6/3/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
  
  private Context context;
  private ListItemClickCallBack itemClickCallBack;
  private List<Event> eventList;
  private int lastPosition = -1;
  
  public EventAdapter(List<Event> eventList, Context context) {
    this.context = context;
    this.eventList = eventList;
  }
  
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView c = (CardView) LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);
    return new ViewHolder(c);
  }
  
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int colorPosition = position % Helper.colors.length;
    
    Event event = eventList.get(position);
    
    int drawableId = this
        .context
        .getResources()
        .getIdentifier(Helper.getResourceNameFromTitle(event.getEventName()), "drawable", context.getPackageName());
    
    holder.eventIcon.setImageResource(drawableId != 0 ? drawableId : R.drawable.github);
    holder.eventTitle.setText(event.getEventName());
    holder.eventShortDescription.setText(event.getShortDescription());
    setAnimation(holder.container, position);
  }
  
  @Override
  public int getItemCount() {
    return eventList.size();
  }
  
  public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
    this.itemClickCallBack = itemClickCallBack;
  }
  
  public void onViewDetachedFromWindow(ViewHolder holder) {
    holder.clearAnimation();
  }
  
  public void setAnimation(View viewToAnimate, int position) {
    
    if (position > lastPosition) {
      Animation animation = AnimationUtils.loadAnimation(context, R.anim.swing_up_left);
      viewToAnimate.startAnimation(animation);
      lastPosition = position;
    }
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    
    public CardView container;
    private AppCompatImageView eventIcon;
    private AppCompatTextView eventTitle;
    private AppCompatTextView eventShortDescription;
    
    public ViewHolder(CardView card) {
      super(card);
      this.container = card;
      this.eventIcon = (AppCompatImageView) this.container.findViewById(R.id.event_list_view_icon);
      this.eventTitle = (AppCompatTextView) this.container.findViewById(R.id.event_list_view_title);
      this.eventShortDescription = (AppCompatTextView) this.container.findViewById(R.id.event_list_view_short_description);
      
      this.container.setCardBackgroundColor(ContextCompat.getColor(this.container.getContext(), R.color.colorCardBackground));
      container.setOnClickListener(this);
      
      Animation cardAnimation;
      cardAnimation = AnimationUtils.loadAnimation(context, R.anim.swing_up_left);
      container.startAnimation(cardAnimation);
      
    }
    
    @Override
    public void onClick(View v) {
      itemClickCallBack.onItemClick(getAdapterPosition(), v.getId());
    }
    
    public void clearAnimation() {
      container.clearAnimation();
    }
  }
}
