package com.example.android52_day5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment {


    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.btnJoinUs)
    Button btnJoinUs;

    public void setRegisterListener(IRegisterListener registerListener) {
        this.registerListener = registerListener;
    }

    private IRegisterListener registerListener;

    public SignUpFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });

        btnJoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register() {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(edtName.getText().toString());
        userInfo.setPasswords(edtPassword.getText().toString());
        userInfo.setEmail(edtEmail.getText().toString());
        userInfo.setPhone(edtPhoneNumber.getText().toString());

        if(registerListener != null){
            registerListener.onRegisterSuccess(userInfo);
            getActivity().getSupportFragmentManager().popBackStack();
        }


    }

    private void backToLogin() {
        if(registerListener != null){
            registerListener.onRegisterCancel();
        }
        getActivity().getSupportFragmentManager().popBackStack();
    }
}