package com.example.administrator.mypocket.login;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.mypocket.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager =getSupportFragmentManager();
        LoginFragment loginFragment= new LoginFragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_container,loginFragment,"LoginFragment").commit();

    }
    public void loginButtonClicked(){

        FragmentManager fragmentManager =getSupportFragmentManager();
        fragmentManager.popBackStack();
    }
    public void registerButtonClicked(){

        Toast.makeText(LoginActivity.this, "Username and password registering", Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager =getSupportFragmentManager();
        RegisterFragment registerFragment= new RegisterFragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_container,registerFragment,"RegisterFragment").commit();
        fragmentTransaction.addToBackStack(null).commit();
    }


  /*  public void gotoLoginClicked(){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        RegisterFragment registerFragment= (RegisterFragment) fragmentManager.findFragmentById(R.id.login_container);
        if(registerFragment instanceof RegisterFragment){
                   registerFragment.onBackPressed();
          } else {
            super.onBackPressed();
           }
        fragmentTransaction.add(R.id.login_container,registerFragment).commit();
        fragmentTransaction.addToBackStack(null).commit();

    }*/
}
