package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bt1.data.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<User> users;
    private Button loginButton;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String username = "user" + (1 + i);
            String password = "password" + (1 + i);
            users.add(new User(username, password)) ;
        }


        username = findViewById(R.id.Username);

        password = findViewById(R.id.Password);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                boolean userFound = false;
                User loginedUser  = null;

                for (User user : users) {
                    if (user.getUsername().equals(usernameText) && user.getPassword().equals(passwordText)) {
                        loginedUser = user;
                        userFound = true;
                        break;
                    }
                }
                if (userFound && loginedUser != null) {
                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("USERNAME", loginedUser.getUsername());
                    intent.putExtra("PASSWORD", loginedUser.getPassword());

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                }
                username.getText().clear();
                password.getText().clear();

            }
        });
    }
}