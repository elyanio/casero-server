package caribehostal.caseroserver.view.action;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private List<Action> actions;
    private ActionRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_show);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actions = new ArrayList<>();
        DaoAction daoAction = new DaoAction();
        daoAction.getAllActions().collect(actions);
        adapter = new ActionRecyclerAdapter(actions, this);
        recyclerView.setAdapter(adapter);
    }
}
