package xanctuary.transgames_client;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import ivb.com.materialstepper.stepperFragment;


public class IsiDataFragment extends stepperFragment {
    private EditText inputNama, inputNomorTelp, inputEmail, inputNomorRek;
    private TextInputLayout inputLayoutNama, inputLayoutNomorTelp, inputLayoutEmail, inputLayoutNomorRek;


    public IsiDataFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean onNextButtonHandler() {
        return submitForm();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_isi_data, container, false);

        inputLayoutNama = (TextInputLayout) view.findViewById(R.id.input_nama_layout);
        inputLayoutNomorTelp = (TextInputLayout) view.findViewById(R.id.input_nomor_telepon_layout);
        inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_email_layout);
        inputLayoutNomorRek = (TextInputLayout) view.findViewById(R.id.input_nomor_rekening_layout);
        inputNama = (EditText) view.findViewById(R.id.input_nama);
        inputNomorTelp = (EditText) view.findViewById(R.id.input_nomor_telepon);
        inputEmail = (EditText) view.findViewById(R.id.input_email);
        inputNomorRek = (EditText) view.findViewById(R.id.input_nomor_rekening);

        inputNama.addTextChangedListener(new MyTextWatcher(inputNama));
        inputNomorTelp.addTextChangedListener(new MyTextWatcher(inputNomorTelp));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputNomorRek.addTextChangedListener(new MyTextWatcher(inputNomorRek));

        return view;
    }

    private Boolean submitForm() {
        if (!validateNama()) {
            return false;
        }

        if (!validateNomorTelp()) {
            return false;
        }

        if (!validateEmail()) {
            return false;
        }

        if (!validateNomorRek()) {
            return false;
        }

        Toast.makeText(getContext(), "Thank You!", Toast.LENGTH_SHORT).show();
        return true;
    }

    private boolean validateNama() {
        if (inputNama.getText().toString().trim().isEmpty()) {
            inputLayoutNama.setError(getString(R.string.err_msg_nama));
            requestFocus(inputNama);
            return false;
        } else {
            inputLayoutNama.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNomorTelp() {
        if (inputNomorTelp.getText().toString().trim().isEmpty()) {
            inputLayoutNomorTelp.setError(getString(R.string.err_msg_nomor_telepon));
            requestFocus(inputNomorTelp);
            return false;
        } else {
            inputLayoutNomorTelp.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNomorRek() {
        if (inputNomorRek.getText().toString().trim().isEmpty()) {
            inputLayoutNomorRek.setError(getString(R.string.err_msg_nomor_rekening));
            requestFocus(inputNomorRek);
            return false;
        } else {
            inputLayoutNomorRek.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                case R.id.input_nama:
                    validateNama();
                    break;
                case R.id.input_nomor_telepon:
                    validateNomorTelp();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_nomor_rekening:
                    validateNomorRek();
                    break;
            }
        }
    }
}
