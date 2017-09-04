package caribehostal.caseroserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import caribehostal.caseroserver.view.action.ActionShow;
import caribehostal.caseroserver.view.owner.OwnerRegister;
import caribehostal.caseroserver.view.owner.OwnerShow;
import caribehostal.caseroserver.view.report.ReportActivity;
import caribehostal.caseroserver.view.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.card_view_owner)
    void showOwnerView() {
        startActivity(new Intent(this, OwnerShow.class));
    }

    @OnClick(R.id.card_view_register)
    void showRegisterView() {
        Bundle bundle = new Bundle();
        bundle.putInt(OwnerRegister.INTENT_ACTION, OwnerRegister.ACTION_INSERT);
        startActivity(new Intent(this, OwnerRegister.class).putExtras(bundle));
    }

    @OnClick(R.id.card_view_action)
    void showActionView() {
        startActivity(new Intent(MainActivity.this, ActionShow.class));
    }

    @OnClick(R.id.card_view_report)
    void showReportView() {
        startActivity(new Intent(this, ReportActivity.class));
    }

    @OnClick(R.id.card_view_settings)
    void showSettingsView() {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
