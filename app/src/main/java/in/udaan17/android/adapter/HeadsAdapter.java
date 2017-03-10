package in.udaan17.android.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import in.udaan17.android.model.Manager;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 7/3/17.
 */

public class HeadsAdapter extends RecyclerView.Adapter<HeadsAdapter.ViewHolder> {

    private Context context;
    private ListItemClickCallBack itemClickCallBack;
    private List<Manager> headList;

    public HeadsAdapter(List<Manager> headList, Context context) {
        this.context = context;
        this.headList = headList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView c = (CardView) LayoutInflater.from(context).inflate(R.layout.heads_list_item, parent, false);
        return new ViewHolder(c);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.headsTitle.setText(headList.get(position).getName());
        holder.headsMobile.setText(headList.get(position).getContactInfo());
    }

    @Override
    public int getItemCount() {
        if (headList == null)
            return 0;
        return headList.size();
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView container;
        public AppCompatTextView headsTitle;
        public AppCompatTextView headsMobile;
    
        public ViewHolder(CardView itemView) {
            super(itemView);
            container = itemView;
            headsTitle = (AppCompatTextView) itemView.findViewById(R.id.heads_list_item_title);
            headsMobile = (AppCompatTextView) itemView.findViewById(R.id.heads_list_item_mobile);
        
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
    }
}
