package caribehostal.caseroserver.view.action;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.R;

public class ActionDetail extends AppCompatActivity {

    @BindView(R.id.action_detail_name) TextView ownerName;
    @BindView(R.id.action_detail_checkIn) TextView checkIn;
    @BindView(R.id.action_detail_checkOut) TextView checkOut;
    @BindView(R.id.action_detail_code) TextView code;
    @BindView(R.id.action_label_date) TextView messageDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_action_detail);
    }
}
