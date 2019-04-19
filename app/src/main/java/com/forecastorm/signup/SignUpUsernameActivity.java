package com.forecastorm.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpUsernameActivity extends AppCompatActivity {
    private Button backButton;
    private EditText usernameEditText;
    private Button continueButton;


    //fields passing by signUpName activity
    private String firstNameString;
    private String lastNameString;
    //fields passing by signUpName activity

    //fields passing by signUpPassword activity
    private String usernameString;
    //fields passing by signUpPassword activity

    private TextWatcher userNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameString = usernameEditText.getText().toString().trim();
            if (usernameString.isEmpty()) {
                continueButton.setBackgroundResource(R.drawable.rounded_button_grey);
                continueButton.setEnabled(false);
            } else {
                continueButton.setBackgroundResource(R.drawable.rounded_button_blue);
                continueButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_username);

        //find views
        backButton = findViewById(R.id.back_button);
        usernameEditText = findViewById(R.id.username_edit_text);
        continueButton = findViewById(R.id.continue_button);

        //assign fields passed by previous or later activity
        if (getIntent().getExtras() != null) {
            firstNameString = getIntent().getExtras().getString("firstNameStringKey");
            lastNameString = getIntent().getExtras().getString("lastNameStringKey");
        }

        // if it is calling from signUpPasswordActivity
        // assign userNameString
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("usernameStringKey")) {
                usernameString = getIntent().getExtras().getString("usernameStringKey");
                //tricks to move cursor to the end
                usernameEditText.setText("");
                usernameEditText.append(usernameString);
                //tricks to move cursor to the end ends

                continueButton.setBackgroundResource(R.drawable.rounded_button_blue);
                continueButton.setEnabled(true);
            }
        }

        //press backButton to go back
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpNameActivity();
            }
        });
        // add text watcher to user name editText
        usernameEditText.addTextChangedListener(userNameTextWatcher);


        //press continue button to go to signUpPasswordActivity
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpPasswordActivity();
            }
        });

    }

    private void openSignUpNameActivity() {
        Intent intent = new Intent(this, SignUpNameActivity.class);
        intent.putExtra("firstNameStringKey", firstNameString);
        intent.putExtra("lastNameStringKey", lastNameString);
        startActivity(intent);
    }

    private void openSignUpPasswordActivity() {
        Intent intent = new Intent(this, SignUpPasswordActivity.class);
        intent.putExtra("firstNameStringKey", firstNameString);
        intent.putExtra("lastNameStringKey", lastNameString);
        String usernameString = usernameEditText.getText().toString();
        intent.putExtra("usernameStringKey", usernameString);
        startActivity(intent);
    }


}
