package caribehostal.caseroserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import caribehostal.caseroserver.view.action.ActionShow;
import caribehostal.caseroserver.view.owner.OwnerRegister;
import caribehostal.caseroserver.view.owner.OwnerShow;
import caribehostal.caseroserver.view.report.ReportActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_owner)
    void showOwnerView() {
        startActivity(new Intent(this, OwnerShow.class));
    }

    @OnClick(R.id.button_register)
    void showRegisterView() {
        Bundle bundle = new Bundle();
        bundle.putInt(OwnerRegister.INTENT_ACTION, OwnerRegister.ACTION_INSERT);

        startActivity(new Intent(this, OwnerRegister.class).putExtras(bundle));
    }

    @OnClick(R.id.button_action)
    void showActionView() {
        startActivity(new Intent(MainActivity.this, ActionShow.class));
    }

    @OnClick(R.id.button_report)
    void showReportView() {
        startActivity(new Intent(this, ReportActivity.class));
    }
}
