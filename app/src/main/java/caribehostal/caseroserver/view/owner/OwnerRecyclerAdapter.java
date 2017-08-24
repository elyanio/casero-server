package caribehostal.caseroserver.view.owner;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import caribehostal.caseroserver.R;
import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Owner;

/**
 * Created by Fernando on 22/08/2017.
 **/

public class OwnerRecyclerAdapter extends RecyclerView.Adapter<OwnerRecyclerAdapter.MyViewHolder> {

    private List<Owner> dataSet;
    private Context context;

    public OwnerRecyclerAdapter(List<Owner> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.owner_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        holder.name.setText(dataSet.get(listPosition).getFullName());
        holder.cell.setText(dataSet.get(listPosition).getCell());
        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPassowrdDialog(listPosition);
            }
        });
    }

    private void removeItem(int listPosition) {
        DaoOwner daoOwner = new DaoOwner();
        daoOwner.remove(dataSet.get(listPosition));
        dataSet.remove(listPosition);
        notifyDataSetChanged();
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

    private void createPassowrdDialog(final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View item = inflater.inflate(R.layout.item_pass, null);
        new AlertDialog.Builder(context).setTitle("Eliminar Propietario")
                .setPositiveButton("Acepetar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeItem(position);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setView(item)
                .show();
    }
}