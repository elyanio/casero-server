package caribehostal.caseroserver.view.action;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.R;
import caribehostal.caseroserver.dataaccess.DaoAction;
import caribehostal.caseroserver.dataaccess.DaoActionClient;
import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.Client;
import caribehostal.caseroserver.datamodel.LocalDateConverter;

public class ActionDetail extends AppCompatActivity {

    @BindView(R.id.action_detail_name) TextView ownerName;
    @BindView(R.id.action_detail_checkIn) TextView checkIn;
    @BindView(R.id.action_detail_checkOut) TextView checkOut;
    @BindView(R.id.action_detail_code) TextView code;
    @BindView(R.id.action_label_date) TextView messageDate;

    @BindView(R.id.recycler_client_passaport)
    RecyclerView recyclerView;
    private List<Client> clients;
    private ActionDetailRecyclerAdapter actionDetailRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        ButterKnife.bind(this);
        clients = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        int idPetition = extras.getInt("IDPET");
        DaoAction daoAction = new DaoAction();
        Action action = daoAction.getActionByPetitionId(idPetition);

        fillValues(action);
        findClients(action);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actionDetailRecyclerAdapter = new ActionDetailRecyclerAdapter(clients, this);
        recyclerView.setAdapter(actionDetailRecyclerAdapter);
    }

    private void findClients(Action action) {
        DaoActionClient daoActionClient = new DaoActionClient();
        daoActionClient.getClientsByAction(action).collect(clients);
    }

    private void fillValues(Action action) {
        ownerName.setText(action.getOwner().getFullName());
        checkIn.setText(new LocalDateConverter().convertToPersisted(action.getCheckIn()));
        checkOut.setText(new LocalDateConverter().convertToPersisted(action.getCheckOut()));
        messageDate.setText(new LocalDateConverter().convertToPersisted(action.getDateAction()));
        code.setText(action.getPetitionOwnerId());
    }
}
