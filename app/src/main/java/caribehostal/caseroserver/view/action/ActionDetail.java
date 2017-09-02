package caribehostal.caseroserver.view.action;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.R;
import caribehostal.caseroserver.comunication.SmsSender;
import caribehostal.caseroserver.dataaccess.DaoAction;
import caribehostal.caseroserver.dataaccess.DaoActionClient;
import caribehostal.caseroserver.datamodel.Action;
import caribehostal.caseroserver.datamodel.ActionClient;
import caribehostal.caseroserver.datamodel.ActionState;
import caribehostal.caseroserver.datamodel.LocalDateConverter;
import caribehostal.caseroserver.datamodel.LocalDateTimeConverter;

public class ActionDetail extends AppCompatActivity {

    @BindView(R.id.action_detail_name) TextView ownerName;
    @BindView(R.id.action_detail_user) TextView ownerUser;
    @BindView(R.id.action_detail_password) TextView ownerPassword;
    @BindView(R.id.action_detail_checkIn) TextView checkIn;
    @BindView(R.id.action_detail_checkOut) TextView checkOut;
    @BindView(R.id.action_detail_code) TextView code;
    @BindView(R.id.action_label_date) TextView messageDate;
    @BindView(R.id.recycler_client_passaport) RecyclerView recyclerView;

    private List<ActionClient> actionClients;
    private ActionDetailRecyclerAdapter actionDetailRecyclerAdapter;
    private DaoAction daoAction;
    private Action action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        ButterKnife.bind(this);
        actionClients = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        int idPetition = extras.getInt("IDPET");
        daoAction = new DaoAction();
        action = daoAction.getActionByPetitionId(idPetition);

        fillValues(action);
        findActionClients(action);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actionDetailRecyclerAdapter = new ActionDetailRecyclerAdapter(actionClients, this);
        recyclerView.setAdapter(actionDetailRecyclerAdapter);
    }

    private void findActionClients(Action action) {
        DaoActionClient daoActionClient = new DaoActionClient();
        daoActionClient.getActionClients(action).collect(actionClients);
    }

    private void fillValues(Action action) {
        ownerName.setText(action.getOwner().getFullName());
        ownerUser.setText(action.getOwner().getUser());
        ownerPassword.setText(action.getOwner().getPassword());
        checkIn.setText(new LocalDateConverter().convertToPersisted(action.getCheckIn()));
        checkOut.setText(new LocalDateConverter().convertToPersisted(action.getCheckOut()));
        messageDate.setText(new LocalDateTimeConverter().convertToPersisted(action.getReceiveDate()));
        code.setText(action.getPetitionOwnerId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_ic_done) {
            if (action.getActionState().equals(ActionState.PENDING)) {
                if (actionDetailRecyclerAdapter.isAllChecked()) {
                    action.setActionState(ActionState.FINISH);
                    daoAction.upsertAction(action);
                    String message = buildMessage();
//                    sendMessage(message);
                    finish();
                    return true;
                } else {
                    Toast.makeText(this, "Por favor, chequea todos los pasaportes", Toast.LENGTH_SHORT).show();
                    return true;
                }
            } else
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private String buildMessage() {
        List<ActionClient> actionClients = actionDetailRecyclerAdapter.getActionClients();
        String petitionOwnerId = action.getPetitionOwnerId();
        String message = petitionOwnerId;
        for (int i = 0; i < actionClients.size(); i++) {
            message += "#" + actionClients.get(i).getClient().getPassport() + "$" + actionClients.get(i).getActionClientCode();
        }
        return message;
    }

    private void sendMessage(String message) {
        String cell = action.getOwner().getCell();
        SmsSender smsSender = new SmsSender();
        smsSender.enviarMensaje(cell, message);
    }
}
