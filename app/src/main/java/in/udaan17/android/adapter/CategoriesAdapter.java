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
import in.udaan17.android.model.Category;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 6/3/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    List<Category> categoryArrayList;
    private Context context;
    private ListItemClickCallBack itemClickCallBack;

    public CategoriesAdapter(List<Category> categoryArrayList, Context context) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView c = (CardView) LayoutInflater.from(context).inflate(R.layout.categories_list_item, parent, false);
        return new ViewHolder(c);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorPosition = position % Helper.colors.length;

        holder.container.setCardBackgroundColor(ContextCompat.getColor(context, Helper.colors[colorPosition]));
        holder.categoryTitle.setText(categoryArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView container;
        private AppCompatTextView categoryTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.category_list_item_card);
            categoryTitle = (AppCompatTextView) itemView.findViewById(R.id.category_list_view_title);

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
