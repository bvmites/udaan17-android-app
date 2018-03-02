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
import in.udaan17.android.model.Developer;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 5/3/17.
 * Project: udaan17AndroidApp
 */

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {
    
    private List<Developer> developers;
    private Context context;
    private ListItemClickCallBack itemClickCallBack;
    private int lastPosition = -1;
    
    public DeveloperAdapter(List<Developer> developers, Context context) {
        this.developers = developers;
        this.context = context;
    }
    
    private static int getColorId(String category, Context context) {
        int id = context
            .getResources()
            .getIdentifier("color_" + Helper.getResourceNameFromTitle(category), "color", context.getPackageName());
        
        if (id == 0) {
            id = R.color.colorTeal;
        }
        return id;
    }
    
    private static int getIconId(String category, Context context) {
        int id = context
            .getResources()
            .getIdentifier(Helper.getResourceNameFromTitle(category), "drawable", context.getPackageName());
    
        if (id == 0) {
            id = R.drawable.github;
        }
        return id;
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(context).inflate(R.layout.developer_list_item, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorId = DeveloperAdapter.getColorId(developers.get(position).getCategory(), this.context);
        int iconId = DeveloperAdapter.getIconId(developers.get(position).getCategory(), this.context);

        holder.categoryIcon.setImageResource(iconId);
        holder.categoryIcon.setBackgroundColor(ContextCompat.getColor(this.context, colorId));
        holder.title.setText(developers.get(position).getTitle());
        holder.name.setText(developers.get(position).getName());

        setAnimation(holder.container, position);
    }
    
    private void setAnimation(View viewToAnimate, int position) {

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.swing_up_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return this.developers.size();
    }
    
    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        holder.clearAnimation();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView container;
        private AppCompatImageView categoryIcon;
        private AppCompatTextView title;
        private AppCompatTextView name;
        private AppCompatImageView mobile;
        private AppCompatImageView email;
        private AppCompatImageView github;
    
        public ViewHolder(CardView itemView) {
            super(itemView);
            this.container = itemView;
            this.categoryIcon = (AppCompatImageView) this.container.findViewById(R.id.developer_category_icon);
            this.title = (AppCompatTextView) this.container.findViewById(R.id.developer_title);
            this.name = (AppCompatTextView) this.container.findViewById(R.id.developer_name);
            this.mobile = (AppCompatImageView) this.container.findViewById(R.id.developer_mobile);
            this.email = (AppCompatImageView) this.container.findViewById(R.id.developer_email);
            this.github = (AppCompatImageView) this.container.findViewById(R.id.developer_github);
        
            this.email.setOnClickListener(this);
            this.github.setOnClickListener(this);
            this.mobile.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickCallBack.onItemClick(getAdapterPosition(), view.getId());
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }
}
