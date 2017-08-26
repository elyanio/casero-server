package caribehostal.caseroserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.dataaccess.DatabaseSetup;
import caribehostal.caseroserver.view.action.ActionShow;
import caribehostal.caseroserver.view.owner.OwnerRegister;
import caribehostal.caseroserver.view.owner.OwnerShow;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button btRegister;
    @BindView(R.id.button2)
    Button btShow;

    @BindView(R.id.button3)
    Button btShowAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DatabaseSetup databaseSetup = new DatabaseSetup();
        databaseSetup.mockDatabase();
        databaseSetup.testExistAction();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShow();
            }
        });
        btShowAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActionShow.class));
            }
        });
    }

    private void initShow() {
        startActivity(new Intent(this, OwnerShow.class));
    }

    private void initRegister() {
        Bundle bundle = new Bundle();
        bundle.putInt(OwnerRegister.INTENT_ACTION, OwnerRegister.ACTION_INSERT);

        startActivity(new Intent(this, OwnerRegister.class).putExtras(bundle));
    }


}
