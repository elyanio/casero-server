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

class RegisterOwner(@BindView(R.id.owner_name) var name: EditText, @BindView(R.id.owner_cell) var cell: EditText
                    , @BindView(R.id.owner_user) var user: EditText, @BindView(R.id.owner_pass) var password: EditText
                    , @BindView(R.id.owner_address) var address: EditText
                    , @BindView(R.id.owner_address_description) var addreesDescription: EditText
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
            saveButton()
        })
    }


    private fun valideCell() {

    }

    private fun saveButton() {

    }
}
