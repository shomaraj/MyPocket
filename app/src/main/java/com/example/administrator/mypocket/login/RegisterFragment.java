package com.example.administrator.mypocket.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mypocket.R;
import com.example.administrator.mypocket.data.MyDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
private MyDatabase database;
private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    TextView textViewgotoLogin;
    Button buttonRegister;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database = new MyDatabase(getActivity());

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register,container,false);

        editTextUsername = (EditText) view.findViewById(R.id.edittextRegisterUsername);
        editTextPassword = (EditText) view.findViewById(R.id.edittextRegisterPassword);
        editTextConfirmPassword = (EditText) view.findViewById(R.id.edittext_confirmPassword);
        buttonRegister = (Button) view.findViewById(R.id.buttonRegister);
        textViewgotoLogin = (TextView) view.findViewById(R.id.text_goto_Login);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim().toLowerCase();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                if(username.length() < 5 || password.length() <=8){
                    Toast.makeText(getActivity(), "Username atleast 5 characters or password atleast 8 characters", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(confirmPassword)){
                    Toast.makeText(getActivity(), "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
                } else if(database.checkIfUsernameExists(username)) {
                    Toast.makeText(getActivity(), "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  view;


    }


}
