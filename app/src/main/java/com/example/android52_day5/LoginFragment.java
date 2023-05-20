package com.example.android52_day5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements IRegisterListener {

    private String p1;
    private String p2;

    @BindView(R.id.tv_SignUp)
    TextView tv_SignUp;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.btnLogin)
    Button btnLogin;





    public LoginFragment() {

    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
         args.putString("P1",param1);
        args.putString("P2",param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
             p1 = getArguments().getString("P1");
            p2 = getArguments().getString("P2");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        tv_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSignUp();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });
    }

    private void gotoLogin() {


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("LOGIN_STATE",true).apply();


        HomeFragment homeFragment  = new HomeFragment();
        homeFragment.setUserInfo(userInfo);
        getActivity().getSupportFragmentManager()
                .beginTransaction().add(R.id.container,homeFragment)
                .addToBackStack("LOGIN").commit();
    }

    private void gotoSignUp() {
        SignUpFragment signUpFragment = new SignUpFragment();
        signUpFragment.setRegisterListener(this);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,signUpFragment)
                .addToBackStack("SIGNUP").commit();
    }

    private  UserInfo userInfo;

    @Override
    public void onRegisterSuccess(UserInfo userInfo) {
        this.userInfo = userInfo;
        edtPhoneNumber.setText(userInfo.getPhone());
    }

    @Override
    public void onRegisterCancel() {

    }
}