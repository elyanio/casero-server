package caribehostal.caseroserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import caribehostal.caseroserver.dataaccess.DatabaseSetup;
import caribehostal.caseroserver.view.RegisterOwner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseSetup databaseSetup = new DatabaseSetup();
        databaseSetup.mockDatabase();
        databaseSetup.testExistAction();

        startActivity(new Intent(this, RegisterOwner.class));
    }
}
