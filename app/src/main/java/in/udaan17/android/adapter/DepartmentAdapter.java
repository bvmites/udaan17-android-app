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
import in.udaan17.android.model.Department;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 6/3/17.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    private Context context;
    private ListItemClickCallBack itemClickCallBack;
    private List<Department> departmentList;
    private int lastPosition = -1;

    public DepartmentAdapter(List<Department> departmentList, Context context) {
        this.context = context;
        this.departmentList = departmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView c = (CardView) LayoutInflater.from(context).inflate(R.layout.department_list_item, parent, false);
        return new ViewHolder(c);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorPosition = position % Helper.colors.length;

        holder.container.setCardBackgroundColor(ContextCompat.getColor(context, Helper.colors[colorPosition]));
        holder.departmentTitle.setText(departmentList.get(position).getName());
        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    public void setAnimation(View viewToAnimate, int position) {

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.swing_up_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        holder.clearAnimation();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView container;
        private AppCompatTextView departmentTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.department_list_item_card);
            departmentTitle = (AppCompatTextView) itemView.findViewById(R.id.department_list_view_title);

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
