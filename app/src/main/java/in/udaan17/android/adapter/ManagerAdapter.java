package in.udaan17.android.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.model.Manager;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 7/3/17.
 */

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder> {
  
  private Context context;
  private ListItemClickCallBack itemClickCallBack;
  private List<Manager> managers;
  
  public ManagerAdapter(List<Manager> managers, Context context, ListItemClickCallBack itemClickCallBack) {
    if (managers != null) {
      this.managers = managers;
    } else {
      this.managers = new ArrayList<>();
    }
    this.context = context;
    this.itemClickCallBack = itemClickCallBack;
  }
  
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.managers_list_item, parent, false);
    return new ViewHolder(view, this.itemClickCallBack);
  }
  
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.managerName.setText(managers.get(position).getName());
    holder.managerMobile.setText(managers.get(position).getContactInfo());
  }
  
  @Override
  public int getItemCount() {
    return managers.size();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    
    private View rootView;
    private AppCompatTextView managerName;
    private AppCompatTextView managerMobile;
    private ListItemClickCallBack itemClickCallBack;
    
    private ViewHolder(View rootView, ListItemClickCallBack itemClickCallBack) {
      super(rootView);
      this.rootView = rootView;
      managerName = (AppCompatTextView) rootView.findViewById(R.id.textView_name);
      managerMobile = (AppCompatTextView) rootView.findViewById(R.id.textView_mobile);
      this.itemClickCallBack = itemClickCallBack;
      
      this.rootView.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
      this.itemClickCallBack.onItemClick(getAdapterPosition(), view.getId());
    }
  }
}
