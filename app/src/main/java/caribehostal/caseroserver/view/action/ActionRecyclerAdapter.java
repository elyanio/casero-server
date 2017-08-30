package caribehostal.caseroserver.view.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import caribehostal.caseroserver.R;
import caribehostal.caseroserver.dataaccess.DaoAction;
import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.ActionStateConverter;
import caribehostal.caseroserver.datamodel.LocalDateConverter;

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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.action_id_petition.setText(actions.get(position).getPetitionOwnerId());
        holder.action_owner.setText(actions.get(position).getOwner().getFullName());
        holder.action_state.setText(new ActionStateConverter().convertToPersisted(actions.get(position).getActionState()));
        holder.action_date.setText(new LocalDateConverter().convertToPersisted(actions.get(position).getDateAction()));

        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("IDPET", actions.get(position).getId());
                context.startActivity(new Intent(context, ActionDetail.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return actions.size();
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
        notifyDataSetChanged();
    }

    private void removeItem(int position) {
        DaoAction daoAction = new DaoAction();
        daoAction.removeAction(actions.get(position));
        actions.remove(position);
        notifyDataSetChanged();
    }
}