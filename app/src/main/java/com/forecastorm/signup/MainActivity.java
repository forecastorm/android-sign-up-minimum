package com.forecastorm.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signUpButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find views
        signUpButton = findViewById(R.id.sign_up_button);
        loginButton = findViewById(R.id.log_in_button);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpNameActivity();
            }
        });
    }
    private void openSignUpNameActivity() {

        Intent intent = new Intent(this, SignUpNameActivity.class);
        startActivity(intent);
    }
}


