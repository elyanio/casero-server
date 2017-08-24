package caribehostal.caseroserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.dataaccess.DatabaseSetup;
import caribehostal.caseroserver.view.owner.OwnerRegister;
import caribehostal.caseroserver.view.owner.OwnerShow;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button btRegister;
    @BindView(R.id.button2)
    Button btShow;

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
    }

    private void initShow() {
        startActivity(new Intent(this, OwnerShow.class));
    }

    private void initRegister() {
        startActivity(new Intent(this, OwnerRegister.class));
    }


}
