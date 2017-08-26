package caribehostal.caseroserver.view.action;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import caribehostal.caseroserver.R;
import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.view.owner.OwnerRecyclerAdapter;

/**
 * Created by asio on 8/26/2017.
 */

public class ActionRecyclerAdapter extends RecyclerView.Adapter<ActionRecyclerAdapter.MyViewHolder> {

    private List<Action> actions;
    private Context context;

    public ActionRecyclerAdapter(List<Action> actions, Context context) {
        this.actions = actions;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.action_item, parent, false);

        ActionRecyclerAdapter.MyViewHolder myViewHolder = new ActionRecyclerAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.action_name.setText(actions.get(position).getPetitionOwnerId());
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView action_name;
        TextView action_date;
        TextView action_state;
        ImageView buttonRemove;

        public MyViewHolder(View itemView) {
            super(itemView);
            action_name = (TextView) itemView.findViewById(R.id.item_action_name);
            action_date = (TextView) itemView.findViewById(R.id.item_action_date);
            action_state = (TextView) itemView.findViewById(R.id.item_action_state);
            buttonRemove = (ImageView) itemView.findViewById(R.id.item_action_delete);
        }
    }
}
