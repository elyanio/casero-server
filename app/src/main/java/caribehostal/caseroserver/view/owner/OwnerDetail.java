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

public class OwnerDetail extends AppCompatActivity {

    @BindView(R.id.owner_datail_id) TextView owner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_detail);
        ButterKnife.bind(this);
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
            bundle.putInt("ACTION", 1); //1 para Editar 0 para Insertar
            bundle.putString("ID", owner_id.getText().toString());
            startActivity(new Intent(this, OwnerRegister.class).putExtras(bundle));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
