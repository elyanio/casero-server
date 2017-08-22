package caribehostal.caseroserver.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife

import caribehostal.caseroserver.R
import caribehostal.caseroserver.util.ValideString

class RegisterOwner(@BindView(R.id.owner_name) var name: EditText, @BindView(R.id.owner_cell) var cell: EditText
                    , @BindView(R.id.owner_user) var user: EditText, @BindView(R.id.owner_pass) var password: EditText
                    , @BindView(R.id.owner_address) var address: EditText
                    , @BindView(R.id.owner_address_description) var addressDescription: EditText
                    , @BindView(R.id.input_nombre) var inputName: TextInputLayout
                    , @BindView(R.id.input_cell) var inputCell: TextInputLayout
                    , @BindView(R.id.input_user) var inputUser: TextInputLayout
                    , @BindView(R.id.input_password) var inputPass: TextInputLayout
                    , @BindView(R.id.input_address) var inputAddress: TextInputLayout
                    , @BindView(R.id.input_address_description) var inputReference: TextInputLayout
                    , @BindView(R.id.owner_save) var save: FloatingActionButton) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_owner)
        ButterKnife.bind(this)
        save.setOnClickListener(View.OnClickListener { view ->
//            saveButton()
        })
    }
    private fun validateCell(): Boolean {
        val phone = ValideString.isPhone(cell.text.toString())
        if (phone) {
            inputCell.isEnabled = false
        } else {
            inputCell.setError(getString(R.string.err_validate_cell))
            return false
        }
        return true
    }


    private fun validateName(): Boolean {
        if (name.text.toString().trim().isEmpty()) {
            inputName.setError(getString(R.string.err_validate))
            return false
        } else {
            inputName.isEnabled = false
        }
        return true
    }

    private fun validateUser(): Boolean {
        if (user.text.toString().trim().isEmpty()) {
            inputUser.setError(getString(R.string.err_validate))
            return false
        } else {
            inputUser.isEnabled = false
        }
        return true
    }

    private fun validateAddress(): Boolean {
        if (address.text.toString().trim().isEmpty()) {
            inputAddress.setError(getString(R.string.err_validate))
            return false
        } else {
            inputAddress.isEnabled = false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        if (password.text.toString().trim().isEmpty()) {
            inputPass.setError(getString(R.string.err_validate))
            return false
        } else {
            inputPass.isEnabled = false
        }
        return true
    }

    private fun validateAddressDescription(): Boolean {
        if (addressDescription.text.toString().trim().isEmpty()) {
            inputReference.setError(getString(R.string.err_validate))
            return false
        } else {
            inputReference.isEnabled = false
        }
        return true
    }

}
