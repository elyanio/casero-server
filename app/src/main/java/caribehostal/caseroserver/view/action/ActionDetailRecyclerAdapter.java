package caribehostal.caseroserver.view.action;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import caribehostal.caseroserver.R;
import caribehostal.caseroserver.dataaccess.DaoActionClient;
import caribehostal.caseroserver.datamodel.ActionClient;

/**
 * Created by asio on 8/27/2017.
 */

public class ActionDetailRecyclerAdapter extends RecyclerView.Adapter<ActionDetailRecyclerAdapter.MyViewHolder> {

    private List<ActionClient> actionClients;
    private Context context;

    public ActionDetailRecyclerAdapter(List<ActionClient> actionClients, Context context) {
        this.actionClients = actionClients;
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.actionClientPassaport.setText(actionClients.get(position).getClient().getPassport());
        holder.actionClientCode.setText(actionClients.get(position).getActionClientCode());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCodeDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return actionClients.size();
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

    private void createCodeDialog(final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View item = inflater.inflate(R.layout.item_code, null);
        new AlertDialog.Builder(context).setTitle("Ingresar Código")
                .setPositiveButton("Acepetar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView = (TextView) item.findViewById(R.id.textCode);
                        ActionClient actionClient = actionClients.get(position).setActionClientCode(textView.getText().toString());
                        actionClients.remove(position);
                        actionClients.add(position, actionClient);
                        updateActionClient(actionClient);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setView(item)
                .show();
    }

    private void updateActionClient(ActionClient actionClient) {
        DaoActionClient daoActionClient = new DaoActionClient();
        daoActionClient.upsertAction(actionClient);
        notifyDataSetChanged();
    }
}
