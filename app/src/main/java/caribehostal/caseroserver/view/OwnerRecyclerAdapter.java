package caribehostal.caseroserver.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.CaseroServerApplication;
import caribehostal.caseroserver.R;
import caribehostal.caseroserver.datamodel.Owner;

/**
 * Created by Fernando on 22/08/2017.
 */

public class OwnerRecyclerAdapter extends RecyclerView.Adapter<OwnerRecyclerAdapter.MyViewHolder> {

    private List<Owner> dataSet;


    public OwnerRecyclerAdapter(List<Owner> data) {
        this.dataSet = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.owner_item, parent, false);

//        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.name.setText(dataSet.get(listPosition).getFullName());
        holder.cell.setText(dataSet.get(listPosition).getCell());
//        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView cell;
        ImageView buttonRemove;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_owner_name);
            cell = (TextView) itemView.findViewById(R.id.item_owner_cell);
            buttonRemove = (ImageView) itemView.findViewById(R.id.item_owner_imageButton);
        }
    }
}