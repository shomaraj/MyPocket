package com.example.administrator.mypocket.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mypocket.MainActivity;
import com.example.administrator.mypocket.R;
import com.example.administrator.mypocket.data.MyDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
    public class LoginFragment extends Fragment {
    private EditText editTextUserName,editTextPassword;
    private TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    private MyDatabase database;

    Button btnLogin;
    TextView textviewRegister;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database=new MyDatabase(getActivity());

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login,container,false);
// Declare and initialize

        textInputLayoutUsername = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        textInputLayoutPassword=(TextInputLayout) view.findViewById(R.id.input_layout_password);

        editTextUserName=(EditText)view.findViewById(R.id.edittext_Login_username);
        editTextPassword=(EditText)view.findViewById(R.id.edittext_Login_Password);

// If error
      //  textInputLayout.setError("Username and password must be greater an 5 characters");

// If no error
       // textInputLayout.setErrorEnabled(false);
        btnLogin=(Button)view.findViewById(R.id.buttonLogin);
        textviewRegister=(TextView)view.findViewById(R.id.textView_Register);


       /* textviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Username and password registering", Toast.LENGTH_SHORT).show();
               LoginActivity activity=(LoginActivity)getActivity();
                activity.registerButtonClicked();
            }
        });*/

       btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username=editTextUserName.getText().toString().trim().toLowerCase();
                String password=editTextPassword.getText().toString().trim();
                if(!validateUsername() || !validatePassword()) {
                    Toast.makeText(getActivity(), "Username atleast 5chars and password atleast 8 characters", Toast.LENGTH_SHORT).show();
                } else if (!database.checkIfUsernameExists(username)) {
                    textInputLayoutUsername.setError("Register credentials");
                   // textInputLayoutPassword.setError("Invalid credentials");
                } else {
                    Intent in = new Intent(getActivity(), MainActivity.class);
                    startActivity(in);
                    getActivity().finish();
                    Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Username and password registering", Toast.LENGTH_SHORT).show();

                LoginActivity loginActivity=(LoginActivity) getActivity();
                loginActivity.registerButtonClicked();
               // LoginActivity activity=(LoginActivity)getActivity();
                //activity.registerButtonClicked();
            }
        });
        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=editTextUserName.getText().toString().trim();
                String password=editTextPassword.getText().toString().trim();
                if(username.length()<=5||password.length()<=8){
                    Toast.makeText(getActivity(), "Username and password must be greater an 5 characters", Toast.LENGTH_SHORT).show();

                }
            }
        });*/

        return view;
    }

    private boolean validateUsername() {

        String username = editTextUserName.getText().toString().trim().toLowerCase();

        if (username.length() <= 5) {
            textInputLayoutUsername.setError("Username must be greater than 5 characters");
            return false;
        } else {
            textInputLayoutUsername.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validatePassword() {
        String password = editTextPassword.getText().toString().trim().toLowerCase();

        if (password.length() <= 8) {
            textInputLayoutPassword.setError("Password must be greater than 8 characters");
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

}
