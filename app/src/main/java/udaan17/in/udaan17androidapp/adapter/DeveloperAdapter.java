package udaan17.in.udaan17androidapp.adapter;

import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import udaan17.in.udaan17androidapp.R;

/**
 * Created by pranshu on 5/3/17.
 * Project: udaan17AndroidApp
 */

public class DeveloperAdapter {

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
        }
    }
}
