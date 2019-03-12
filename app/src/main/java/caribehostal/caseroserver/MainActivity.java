package caribehostal.caseroserver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import caribehostal.caseroserver.dataaccess.DaoAction;
import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.view.action.ActionShow;
import caribehostal.caseroserver.view.owner.OwnerRegister;
import caribehostal.caseroserver.view.owner.OwnerShow;
import caribehostal.caseroserver.view.report.ReportActivity;
import caribehostal.caseroserver.view.settings.SettingsActivity;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

public class MainActivity extends AppCompatActivity {
    private static int UNREGISTERED = 0;
    private static int REGIST = 1;
    @BindView(R.id.count_owners) TextView count_owners;
    @BindView(R.id.count_clients) TextView count_clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        checkPermissions();
        init();
    }

    @OnClick(R.id.card_view_owner)
    void showOwnerView() {
        startActivity(new Intent(this, OwnerShow.class).putExtra("ACTION", REGIST));
    }

    @OnClick(R.id.card_view_register)
    void showRegisterView() {
        Bundle bundle = new Bundle();
        bundle.putInt(OwnerRegister.INTENT_ACTION, OwnerRegister.ACTION_INSERT);
        startActivity(new Intent(this, OwnerRegister.class).putExtras(bundle));
    }

    @OnClick(R.id.card_view_owner_petition)
    void launchOwnerShow() {
        startActivity(new Intent(this, OwnerShow.class).putExtra("ACTION", UNREGISTERED));
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

    @SuppressLint("HardwareIds")
    private void checkPermissions() {
        Set<String> allowedNumbers = allowedNumbers();
        Set<String> allowedIds = allowedIds();
        TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (!allowedNumbers.contains(t.getLine1Number()) && !allowedIds.contains(getDeviceId())) {
            this.finish();
        }
    }

    @NonNull
    private Set<String> allowedNumbers() {
        return unmodifiableSet(new HashSet<>(asList(
                "+5353746802",
                "+5354150751",
                "+5354520426",
                "+5358955505",
                "+5353850863"
        )));
    }

    @NonNull
    private Set<String> allowedIds() {
        return unmodifiableSet(new HashSet<>(asList(
                "8f62659ba29f3e7",
                "924c46dff1449660",
                "db46c116811c16e3",
                "d288c9a4d3124b4e",
                "d6b7c24c1e59d756"
        )));
    }

    @SuppressLint("HardwareIds")
    private String getDeviceId() {
        return Settings.Secure.getString(CaseroServerApplication.instance().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private void init() {
        DaoOwner daoOwner = new DaoOwner();
        DaoAction daoAction = new DaoAction();
        int pendingActionCount = daoAction.getPendingActions().toList().size();
        int owners_count = daoOwner.getAllUnregisteredOwners().size();
        if (owners_count > 0) {
            count_owners.setVisibility(View.VISIBLE);
            count_owners.setText("" + owners_count);
        } else {
            count_owners.setVisibility(View.INVISIBLE);
        }
        if (pendingActionCount > 0) {
            count_clients.setVisibility(View.VISIBLE);
            count_clients.setText("" + pendingActionCount);
        } else {
            count_clients.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_ic_refresh) {
           onResume();
        }
        return super.onOptionsItemSelected(item);
    }
}
