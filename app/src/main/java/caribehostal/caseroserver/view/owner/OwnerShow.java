package caribehostal.caseroserver.view.owner;

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
import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Owner;
import caribehostal.caseroserver.view.owner.OwnerRecyclerAdapter;

public class OwnerShow extends AppCompatActivity {
    @BindView(R.id.owner_recycler_view)
    RecyclerView recyclerView;
    private List<Owner> dates;
    private OwnerRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_show);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DaoOwner daoOwner = new DaoOwner();
        dates = new ArrayList<Owner>();
        daoOwner.getAllOwner().collect(dates);

        adapter = new OwnerRecyclerAdapter(dates, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.updateView();
    }
}


