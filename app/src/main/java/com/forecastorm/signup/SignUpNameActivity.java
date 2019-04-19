package com.forecastorm.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpNameActivity extends AppCompatActivity {

    private Button backButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private TextView agreementTextView;
    private Button signUpAndAcceptButton;

    //fields passing from later activity
    private String firstNameString;
    private String lastNameString;



    // a text watcher to keep track of input in editText
    private TextWatcher signUpAndAcceptTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String firstNameString = firstNameEditText.getText().toString().trim();
            String lastNameString = lastNameEditText.getText().toString().trim();

            if (firstNameString.isEmpty() || lastNameString.isEmpty()) {
                signUpAndAcceptButton.setBackgroundResource(R.drawable.rounded_button_grey);
                signUpAndAcceptButton.setEnabled(false);

            } else {
                signUpAndAcceptButton.setBackgroundResource(R.drawable.rounded_button_blue);
                signUpAndAcceptButton.setEnabled(true);
            }
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_name);

        //find views
        backButton = findViewById(R.id.back_button);
        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        signUpAndAcceptButton = findViewById(R.id.sign_up_and_accept_button);

        // assign extras if it is not null
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("firstNameStringKey") && getIntent().getExtras().containsKey("lastNameStringKey")) {
                firstNameString = getIntent().getExtras().getString("firstNameStringKey");
                lastNameString = getIntent().getExtras().getString("lastNameStringKey");

                //tricks to move cursor to the end
                firstNameEditText.setText("");
                firstNameEditText.append(firstNameString);
                //tricks to move cursor to the end ends

                lastNameEditText.setText(lastNameString);
                // if we are passing back from signUpUsername Activity, then button is always enabled
                signUpAndAcceptButton.setBackgroundResource(R.drawable.rounded_button_blue);
                signUpAndAcceptButton.setEnabled(true);
            }
        }



        //press back button go back to main
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        //add text change listener
        firstNameEditText.addTextChangedListener(signUpAndAcceptTextWatcher);
        lastNameEditText.addTextChangedListener(signUpAndAcceptTextWatcher);

        //  agreement text view
        agreementTextView = findViewById(R.id.agreement_text_view);

        String text = "By tapping Sign Up & Accept, you acknowledge that you have read the Privacy Policy and agree to the Terms of Service.";

        SpannableString ss = new SpannableString(text);


        ClickableSpan privacyPolicyClickableSpan = new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(getResources().getColor((R.color.blue)));
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View widget) {
                openPrivacyPolicyActivity();
            }
        };


        ClickableSpan termsOfServiceClickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(getResources().getColor(R.color.blue));
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View widget) {
                openTermsOfServiceActivity();
            }
        };

        ss.setSpan(privacyPolicyClickableSpan, 68, 82, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(termsOfServiceClickableSpan, 100, 117, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        agreementTextView.setText(ss);
        agreementTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //  agreement text view ends


    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void openPrivacyPolicyActivity() {
        Intent intent = new Intent(this, PrivacyPolicyActivity.class);
        startActivity(intent);
    }

    private void openTermsOfServiceActivity() {
        Intent intent = new Intent(this, TermsOfServiceActivity.class);
        startActivity(intent);
    }


}
