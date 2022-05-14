package com.List.listimplementationsqlite.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.example.miniprojet.R;

import com.google.android.material.textfield.TextInputLayout;

import com.List.listimplementationsqlite.entity.Personne;

public class AddPersonneDialog extends Dialog {

    public Context context;
    private EditText firstNameET, lastNameET, emailET, phoneET, ageET;
    private TextInputLayout firstNameTIL, lastNameTIL, emailTIL, phoneTIL, ageTil;
    private Button addBTN;

    private AddPersonCallBack personCallBack;

    public AddPersonneDialog(Context context, AddPersonCallBack personCallBack) {
        super(context);
        this.context = context;
        this.personCallBack = personCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_personne_dialog);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        setCanceledOnTouchOutside(true);
        getWindow().getAttributes().height =
                (int) (getDeviceMetrics(context).heightPixels * 0.8);


        initView();
        initEvent();

    }

    private void initView() {

        firstNameTIL = findViewById(R.id.firstNameTIL);
        lastNameTIL = findViewById(R.id.lastNameTIL);
        emailTIL = findViewById(R.id.emailTIL);
        phoneTIL = findViewById(R.id.phoneTIL);
        ageTil = findViewById(R.id.ageTIL);

        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        emailET = findViewById(R.id.emailET);
        phoneET = findViewById(R.id.phoneET);
        ageET = findViewById(R.id.ageET);

        addBTN = findViewById(R.id.addBTN);

    }

    private void initEvent() {

        firstNameET.addTextChangedListener(new MyTextWatcher(firstNameET));
        lastNameET.addTextChangedListener(new MyTextWatcher(lastNameET));
        emailET.addTextChangedListener(new MyTextWatcher(emailET));
        phoneET.addTextChangedListener(new MyTextWatcher(phoneET));
        ageET.addTextChangedListener(new MyTextWatcher(ageET));

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }


    public static DisplayMetrics getDeviceMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics;
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.firstNameET:
                    validateInput(firstNameET, firstNameTIL, context.getString(R.string.error_message_first_name));
                    break;
                case R.id.lastNameET:
                    validateInput(lastNameET, lastNameTIL, context.getString(R.string.error_message_last_name));
                    break;
                case R.id.emailET:
                    validateInput(emailET, emailTIL, context.getString(R.string.error_message_email));
                    break;
                case R.id.phoneET:
                    validateInput(phoneET, phoneTIL, context.getString(R.string.error_message_phone));
                    break;
                case R.id.ageET:
                    validateInput(ageET, ageTil, context.getString(R.string.error_message_age));
                    break;
            }
        }
    }

    private boolean validateInput(EditText editText, TextInputLayout textInputLayout, String errorMessage) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(context.getString(R.string.err_msg_name));
            requestFocus(editText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateInput(firstNameET, firstNameTIL, context.getString(R.string.error_message_first_name))
        ) {
            return;
        }

        if (!validateInput(lastNameET, lastNameTIL, context.getString(R.string.error_message_last_name))) {
            return;
        }

        if (!validateInput(emailET, emailTIL, context.getString(R.string.error_message_email))) {
            return;
        }

        if (!validateInput(ageET, ageTil, context.getString(R.string.error_message_age))) {
            return;
        }

        if (!validateInput(phoneET, phoneTIL, context.getString(R.string.error_message_phone))) {
            return;
        }

        Personne personne = new Personne(firstNameET.getText().toString().trim(), lastNameET.getText().toString().trim(), emailET.getText().toString().trim(), Integer.parseInt(ageET.getText().toString().trim()), phoneET.getText().toString().trim());
        personCallBack.onPersonAdded(personne);
        dismiss();

    }


    public interface AddPersonCallBack {

        void onPersonAdded(Personne personne);
    }


}
