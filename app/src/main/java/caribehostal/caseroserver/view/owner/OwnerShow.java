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

public class OwnerShow extends AppCompatActivity {
    @BindView(R.id.owner_recycler_view)
    RecyclerView recyclerView;
    private static int REGIST = 0;
    private static int SHOW = 1;

    private List<Owner> owners;
    private OwnerRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_show);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (bundle.getInt("ACTION") == REGIST) {
            owners = new ArrayList<>(new DaoOwner().getAllUnregisteredOwners());
            adapter = new OwnerRecyclerAdapter(owners, this, REGIST);
            this.setTitle("Registrar Propietario");
        } else if (bundle.getInt("ACTION") == SHOW) {
            owners = new ArrayList<>(new DaoOwner().getAllOwners());
            adapter = new OwnerRecyclerAdapter(owners, this, SHOW);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.updateView();
    }
}