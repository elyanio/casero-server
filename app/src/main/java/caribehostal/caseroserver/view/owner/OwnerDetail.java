package caribehostal.caseroserver.view.owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.R;
import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Owner;

public class OwnerDetail extends AppCompatActivity {

    @BindView(R.id.owner_datail_name) TextView ownerName;
    @BindView(R.id.owner_datail_id) TextView ownerId;
    @BindView(R.id.owner_datail_cell) TextView ownerCell;
    @BindView(R.id.owner_datail_user) TextView ownerUser;
    @BindView(R.id.owner_datail_password) TextView ownerPassword;
    @BindView(R.id.owner_datail_address) TextView ownerAddress;
    @BindView(R.id.owner_datail_referencia) TextView ownerDescriptioAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_detail);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        fillElements(extras.getString("CELL"));
    }

    private void fillElements(String cell) {
        DaoOwner daoOwner = new DaoOwner();
        Owner owner = daoOwner.getOwner(cell);
        ownerName.setText(owner.getFullName());
        ownerId.setText(owner.getCarnetId());
        ownerCell.setText(owner.getCell());
        ownerUser.setText(owner.getUser());
        ownerPassword.setText(owner.getPassword());
        ownerAddress.setText(owner.getAddress());
        ownerDescriptioAddress.setText(owner.getAddressDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            finish();
            return true;
        } else if (id == R.id.action_edit) {
            Bundle bundle = new Bundle();
            bundle.putInt("ACTION", OwnerRegister.ACTION_EDIT);
            bundle.putString("CELL", ownerCell.getText().toString());
            startActivity(new Intent(this, OwnerRegister.class).putExtras(bundle));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
