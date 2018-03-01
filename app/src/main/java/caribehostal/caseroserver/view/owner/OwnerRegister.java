package caribehostal.caseroserver.view.owner;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import caribehostal.caseroserver.R;
import caribehostal.caseroserver.dataaccess.DaoOwner;
import caribehostal.caseroserver.datamodel.Owner;

import static caribehostal.caseroserver.util.StringValidation.isCubanCellphone;
import static caribehostal.caseroserver.util.StringValidation.isCubanIdCard;

public class OwnerRegister extends AppCompatActivity {

    public static final String INTENT_ACTION = "ACTION";
    public static final int ACTION_INSERT = 0;
    public static final int ACTION_EDIT = 1;
    public static final String INTENT_CELL = "CELL";

    @BindView(R.id.owner_name)
    EditText name;
    @BindView(R.id.owner_carnet)
    EditText carnet;
    @BindView(R.id.owner_cell)
    EditText cell;
    @BindView(R.id.owner_user)
    EditText user;
    @BindView(R.id.owner_pass)
    EditText password;
    @BindView(R.id.owner_address)
    EditText address;
    @BindView(R.id.owner_address_description)
    EditText addressDescription;

    @BindView(R.id.input_nombre)
    TextInputLayout inputName;
    @BindView(R.id.input_carnet)
    TextInputLayout inputCarnet;
    @BindView(R.id.input_cell)
    TextInputLayout inputCell;
    @BindView(R.id.input_user)
    TextInputLayout inputUser;
    @BindView(R.id.input_password)
    TextInputLayout inputPass;
    @BindView(R.id.input_address)
    TextInputLayout inputAddress;
    @BindView(R.id.input_address_description)
    TextInputLayout inputReference;
    @BindView(R.id.owner_save)
    FloatingActionButton save;

    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_register);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();

        if ((int) bundle.get(INTENT_ACTION) == ACTION_EDIT)
            fillElements();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValideInfo()) {
                    Owner owner = getOwner();
                    DaoOwner daoOwner = new DaoOwner();
                    daoOwner.upsertOwner(owner);
                    finish();
                }
            }
        });
    }

    private void fillElements() {
        DaoOwner daoOwner = new DaoOwner();
        Owner owner = daoOwner.getOwnerByCell(bundle.getString(INTENT_CELL));
        name.setText(owner.getFullName());
        carnet.setText(owner.getCarnetId());
        cell.setText(owner.getCell());
        user.setText(owner.getUser());
        password.setText(owner.getPassword());
        address.setText(owner.getAddress());
        addressDescription.setText(owner.getAddressDescription());
    }

    private Owner getOwner() {
        return new Owner()
                .setFullName(name.getText().toString())
                .setCarnetId(carnet.getText().toString())
                .setCell(cell.getText().toString())
                .setUser(user.getText().toString())
                .setPassword(password.getText().toString())
                .setAddress(address.getText().toString())
                .setRegister(1)
                .setAddressDescription(addressDescription.getText().toString());
    }

    private boolean isValideInfo() {
        return !(!validateName() | !validateCell() | !validateAddress() | !validateUser() | !validatePassword()
                | !validateAddressDescription() | !validateCarnet());
    }

    private boolean validateCell() {
        return validate(isCubanCellphone(cell.getText()), inputCell, R.string.err_validate_cell);
    }

    private boolean validateCarnet() {
        return validate(isCubanIdCard(carnet.getText()), inputCarnet, R.string.err_validate_carnet);
    }

    private boolean validateName() {
        return validateEmptyField(name, inputName);
    }

    private boolean validateUser() {
        return validateEmptyField(user, inputUser);
    }

    private boolean validateAddress() {
        return validateEmptyField(address, inputAddress);
    }

    private boolean validatePassword() {
        return validateEmptyField(password, inputPass);
    }

    private boolean validateAddressDescription() {
        return validateEmptyField(addressDescription, inputReference);
    }

    private boolean validateEmptyField(EditText input, TextInputLayout layout) {
        return validate(!input.getText().toString().isEmpty(), layout, R.string.err_validate);
    }

    private boolean validate(boolean check, TextInputLayout layout, @StringRes int message) {
        if (check) {
            layout.setErrorEnabled(false);
            return true;
        } else {
            layout.setError(getString(message));
            return false;
        }
    }
}