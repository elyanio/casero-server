package caribehostal.caseroserver.view.action;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import caribehostal.caseroserver.R;

/**
 * Created by asio on 8/27/2017.
 */

public class ActionDetailRecyclerAdapter extends RecyclerView.Adapter<ActionDetailRecyclerAdapter.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView action_owner;
        TextView action_id_petition;
        TextView action_date;
        TextView action_state;
        ImageView buttonRemove;

        public MyViewHolder(View itemView) {
            super(itemView);
            action_owner = (TextView) itemView.findViewById(R.id.item_action_owner);
            action_id_petition = (TextView) itemView.findViewById(R.id.item_action_id);
            action_date = (TextView) itemView.findViewById(R.id.item_action_date);
            action_state = (TextView) itemView.findViewById(R.id.item_action_state);
            buttonRemove = (ImageView) itemView.findViewById(R.id.item_action_delete);
        }
    }
}
