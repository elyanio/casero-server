package caribehostal.caseroserver.view.action;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.R;
import caribehostal.caseroserver.dataaccess.DaoAction;
import caribehostal.caseroserver.datamodel.Action;

public class ActionShow extends AppCompatActivity {

    @BindView(R.id.action_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    private List<Action> actions;
    private ActionRecyclerAdapter adapter;
    private DaoAction daoAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_show);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actions = new ArrayList<>();
        daoAction = new DaoAction();
        daoAction.getPendingActions().collect(actions);
        adapter = new ActionRecyclerAdapter(actions, this);
        recyclerView.setAdapter(adapter);
        bottomEvent();

    }

    private void bottomEvent() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.pending) {
                    actions = new ArrayList<>();
                    daoAction.getPendingActions().collect(actions);
                    adapter.setActions(actions);
                    return true;
                } else if (item.getItemId() == R.id.all) {
                    actions = new ArrayList<>();
                    daoAction.getAllActions().collect(actions);
                    adapter.setActions(actions);
                    return true;
                } else {
                    actions = new ArrayList<>();
                    daoAction.getFinishActions().collect(actions);
                    adapter.setActions(actions);
                    return true;
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        actions = new ArrayList<>();
        daoAction.getPendingActions().collect(actions);
        adapter.setActions(actions);
    }
}
