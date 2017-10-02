package caribehostal.caseroserver.view.owner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.R;
import caribehostal.caseroserver.comunication.FixMessage;
import caribehostal.caseroserver.comunication.SmsSender;
import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Owner;
import caribehostal.caseroserver.settings.Settings;
import caribehostal.caseroserver.util.ContactInformation;

public class OwnerCheckRegister extends AppCompatActivity {

    private static int REGISTERED = 1;
    @BindView(R.id.checkBox_nombre) CheckBox checkBox_nombre;
    @BindView(R.id.checkBox_carnet) CheckBox checkBox_carnet;
    @BindView(R.id.checkBox_cell) CheckBox checkBox_cell;
    @BindView(R.id.checkBox_user) CheckBox checkBox_user;
    @BindView(R.id.checkBox_password) CheckBox checkBox_password;
    @BindView(R.id.checkBox_addr) CheckBox checkBox_address;
    @BindView(R.id.checkBox_description) CheckBox checkBox_description;

    @BindView(R.id.owner_check_name) EditText owner_check_name;
    @BindView(R.id.owner_check_carnet) EditText owner_check_carnet;
    @BindView(R.id.owner_check_cell) EditText owner_check_cell;
    @BindView(R.id.owner_check_user) EditText owner_check_user;
    @BindView(R.id.owner_check_pass) EditText owner_check_password;
    @BindView(R.id.owner_check_address) EditText owner_check_address;
    @BindView(R.id.owner_check_address_description) EditText owner_check_address_description;

    @BindView(R.id.owner_check_save) FloatingActionButton save;

    private String mensaje = "";
    private String cell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_check_register);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        cell = bundle.getString("CELL");
        getOwner(cell);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areAllValuesFine()) {
                    updateOwner();
                    Toast.makeText(OwnerCheckRegister.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                } else {
                    deletePetition();
                    Toast.makeText(OwnerCheckRegister.this, "Mensaje enviado.", Toast.LENGTH_SHORT).show();
                }
                SmsSender smsSender = new SmsSender();
                smsSender.enviarMensaje(OwnerCheckRegister.this.cell, mensaje);
                finish();
            }
        });
    }

    private boolean areAllValuesFine() {
        if (areAllFieldFine()) {
            mensaje = FixMessage.getActionSendCorrectOwner() + "#" + Settings.INSTANCE.pricePerDay() + "#" + ContactInformation.getContactInformation();
            return true;
        }
        return false;
    }

    private boolean areAllFieldFine() {
        boolean flag = true;
        mensaje = "" + FixMessage.getActionSendWrongOwner();
        if (checkBox_nombre.isChecked()) {
            mensaje += FixMessage.getNAME();
            flag = false;
        }
        if (checkBox_carnet.isChecked()) {
            mensaje += FixMessage.getCarnetIdentidad();
            flag = false;
        }
        if (checkBox_user.isChecked()) {
            mensaje += FixMessage.getUSER();
            flag = false;
        }
        if (checkBox_password.isChecked()) {
            mensaje += FixMessage.getPASSWORD();
            flag = false;
        }
        if (checkBox_address.isChecked()) {
            mensaje += FixMessage.getADDRESS();
            flag = false;
        }
        if (checkBox_description.isChecked()) {
            mensaje += FixMessage.getREFERENCIA();
            flag = false;
        }
        return flag;
    }

    private void getOwner(String cell) {
        DaoOwner daoOwner = new DaoOwner();
        Owner unregisteredOwnerByCell = daoOwner.getUnregisteredOwnerByCell(cell);
        owner_check_name.setText(unregisteredOwnerByCell.getFullName());
        owner_check_carnet.setText(unregisteredOwnerByCell.getCarnetId());
        owner_check_cell.setText(unregisteredOwnerByCell.getCell());
        owner_check_user.setText(unregisteredOwnerByCell.getUser());
        owner_check_password.setText(unregisteredOwnerByCell.getPassword());
        owner_check_address.setText(unregisteredOwnerByCell.getAddress());
        owner_check_address_description.setText(unregisteredOwnerByCell.getAddressDescription());
    }

    private void updateOwner() {
        DaoOwner daoOwner = new DaoOwner();
        Owner owner = new Owner();
        owner.setFullName(owner_check_name.getText().toString());
        owner.setCarnetId(owner_check_carnet.getText().toString());
        owner.setCell(owner_check_cell.getText().toString());
        owner.setUser(owner_check_user.getText().toString());
        owner.setPassword(owner_check_password.getText().toString());
        owner.setAddress(owner_check_address.getText().toString());
        owner.setAddressDescription(owner_check_address_description.getText().toString());
        owner.setRegister(REGISTERED);
        daoOwner.upsertOwner(owner);
    }

    private void deletePetition() {
        DaoOwner daoOwner = new DaoOwner();
        Owner unregisteredOwnerByCarnetId = daoOwner.getUnregisteredOwnerByCarnetId(owner_check_carnet.getText().toString());
        daoOwner.remove(unregisteredOwnerByCarnetId);
    }
}
