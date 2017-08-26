package caribehostal.caseroserver.view.owner;

import android.os.Bundle;
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
import caribehostal.caseroserver.util.ValideString;

public class OwnerRegister extends AppCompatActivity {
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

        int action = (int) bundle.get("ACTION"); // 1 para editar 0 para insertar
        if (action == 1)
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
        Owner owner = daoOwner.getOwner(bundle.getString("CELL"));
        name.setText(owner.getFullName());
        carnet.setText(owner.getCarnetId());
        cell.setText(owner.getCell());
        user.setText(owner.getUser());
        password.setText(owner.getPassword());
        address.setText(owner.getAddress());
        addressDescription.setText(owner.getAddressDescription());
    }

    private Owner getOwner() {
        return new Owner().setFullName(name.getText().toString()).setCarnetId(carnet.getText().toString()).
                setCell(cell.getText().toString()).setUser(user.getText().toString()).setPassword(password.getText().toString())
                .setAddress(address.getText().toString()).setAddressDescription(addressDescription.getText().toString());
    }

    private Boolean isValideInfo() {
        return !(!validateName() | !validateCell() | !validateAddress() | !validateUser() | !validatePassword()
                | !validateAddressDescription() | !validateCarnet());
    }

    private Boolean validateCell() {
        boolean phone = ValideString.isPhone(cell.getText().toString());
        if (phone) {
            inputCell.setErrorEnabled(false);
        } else {
            inputCell.setError(getString(R.string.err_validate_cell));
            return false;
        }
        return true;
    }

    private Boolean validateCarnet() {
        boolean carnetID = ValideString.isCarnet(carnet.getText().toString());
        if (carnetID) {
            inputCarnet.setErrorEnabled(false);
        } else {
            inputCarnet.setError(getString(R.string.err_validate_carnet));
            return false;
        }
        return true;
    }

    private Boolean validateName() {
        if (name.getText().toString().trim().isEmpty()) {
            inputName.setError(getString(R.string.err_validate));
            return false;
        } else {
            inputName.setErrorEnabled(false);
        }
        return true;
    }

    private Boolean validateUser() {
        if (user.getText().toString().trim().isEmpty()) {
            inputUser.setError(getString(R.string.err_validate));
            return false;
        } else
            inputUser.setErrorEnabled(false);
        return true;
    }

    private Boolean validateAddress() {
        if (address.getText().toString().trim().isEmpty()) {
            inputAddress.setError(getString(R.string.err_validate));
            return false;
        } else
            inputAddress.setErrorEnabled(false);
        return true;
    }

    private Boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            inputPass.setError(getString(R.string.err_validate));
            return false;
        } else
            inputPass.setErrorEnabled(false);
        return true;
    }

    private Boolean validateAddressDescription() {
        if (addressDescription.getText().toString().trim().isEmpty()) {
            inputReference.setError(getString(R.string.err_validate));
            return false;
        } else
            inputReference.setErrorEnabled(false);
        return true;
    }
}