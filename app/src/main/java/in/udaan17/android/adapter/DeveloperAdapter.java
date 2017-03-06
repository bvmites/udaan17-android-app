package in.udaan17.android.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
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
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 5/3/17.
 * Project: udaan17AndroidApp
 */

public class DeveloperAdapter extends RecyclerView.
        Adapter<DeveloperAdapter.ViewHolder> {

    private List<in.udaan17.android.model.Developer> developerList;
    private Context context;
    private ListItemClickCallBack itemClickCallBack;

    public DeveloperAdapter(List<Developer> developerList, Context context) {
        this.developerList = developerList;
        this.context = context;
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView eventCard = (CardView) LayoutInflater.from(context).inflate(R.layout.developer_list_item, parent, false);
        return new ViewHolder(eventCard);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.developerName.setText(developerList.get(position).getName());
        holder.developerTitle.setText(developerList.get(position).getTitle());

        int colorID;
        switch (developerList.get(position).getColor()) {
            case "android":
                colorID = R.color.colorAndroid;
                break;
            case "windows-phone":
                colorID = R.color.colorWindows;
                break;
            case "web":
                colorID = R.color.colorWeb;
                break;
            case "ui":
                colorID = R.color.colorUI;
                break;
            case "backend":
                colorID = R.color.colorBackend;
                break;
            default:
                colorID = R.color.colorPrimary;
                break;
        }

        holder.container.setCardBackgroundColor(ContextCompat.getColor(context, colorID));
    }

    @Override
    public int getItemCount() {
        return this.developerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView container;
        private AppCompatTextView developerTitle;
        private AppCompatTextView developerName;
        private AppCompatImageButton developerMobile;
        private AppCompatImageButton developerEmail;
        private AppCompatImageButton developerGithub;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.developer_list_item_card);
            developerTitle = (AppCompatTextView) itemView.findViewById(R.id.developer_list_item_title);
            developerName = (AppCompatTextView) itemView.findViewById(R.id.developer_list_item_name);
            developerMobile = (AppCompatImageButton) itemView.findViewById(R.id.developer_list_item_mobile);
            developerGithub = (AppCompatImageButton) itemView.findViewById(R.id.developer_list_item_github);
            developerEmail = (AppCompatImageButton) itemView.findViewById(R.id.developer_list_item_email);

            developerEmail.setOnClickListener(this);
            developerGithub.setOnClickListener(this);
            developerMobile.setOnClickListener(this);

            Animation cardAnimation;
            cardAnimation = AnimationUtils.loadAnimation(context, R.anim.swing_up_right);
            container.startAnimation(cardAnimation);


        }

        @Override
        public void onClick(View v) {
            itemClickCallBack.onItemClick(getAdapterPosition(), v.getId());
        }
    }

}
