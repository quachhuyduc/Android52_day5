package com.example.android52_day5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnReplace)
    Button btnReplace;
    @BindView(R.id.btnRemove)
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        addFrag();
    }

    private void addFrag() {
        loginFragment = LoginFragment.newInstance("Duc","Quach");

        getSupportFragmentManager().beginTransaction().add(R.id.container, loginFragment).addToBackStack("ADD").commit();
    }

    private void initView() {
        ButterKnife.bind(this);
        btnAdd.setOnClickListener(this);
        btnReplace.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        boolean isLogin = getSharedPreferences("LOGIN",MODE_PRIVATE).getBoolean("LOGIN_STATE",false);
        if(isLogin){
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container,homeFragment).addToBackStack("ADD").commit();

        }else{
            loginFragment  = LoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, loginFragment).addToBackStack("ADD").commit();
        }





    }


    private void removeFrag() {
    //    getSupportFragmentManager().beginTransaction().remove(loginFragment).commit();
        getSupportFragmentManager().popBackStack();
    }

    LoginFragment loginFragment;

    private void replaceFrag() {
       loginFragment = LoginFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addFrag();
                break;
            case R.id.btnReplace:
                replaceFrag();
                break;
            case R.id.btnRemove:
                removeFrag();
                break;
        }
    }
}