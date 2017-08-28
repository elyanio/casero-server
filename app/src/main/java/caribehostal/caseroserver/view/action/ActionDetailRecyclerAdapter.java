package caribehostal.caseroserver.view.action;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import caribehostal.caseroserver.R;
import caribehostal.caseroserver.datamodel.Client;

/**
 * Created by asio on 8/27/2017.
 */

public class ActionDetailRecyclerAdapter extends RecyclerView.Adapter<ActionDetailRecyclerAdapter.MyViewHolder> {

    private List<Client> clients;
    private Context context;

    public ActionDetailRecyclerAdapter(List<Client> clients, Context context) {
        this.clients = clients;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_item, parent, false);

        ActionDetailRecyclerAdapter.MyViewHolder myViewHolder = new ActionDetailRecyclerAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.actionClientPassaport.setText(clients.get(position).getPassport());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView actionClientPassaport;
        TextView actionClientCode;

        public MyViewHolder(View itemView) {
            super(itemView);
            actionClientPassaport = (TextView) itemView.findViewById(R.id.client_passaport);
            actionClientCode = (TextView) itemView.findViewById(R.id.client_code_action);
        }
    }
}
