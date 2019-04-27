package edu.csumb.nevayeh.airline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    Button backButton;
    Button logInButton;

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = findViewById(R.id.logInUsernameInput);
        password = findViewById(R.id.logInPasswordInput);

        logInButton = findViewById(R.id.logInLogInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LogInUsername: ", username.getText().toString());
                Log.i("LogInPassword: ", password.getText().toString());
            }
        });

        backButton = findViewById(R.id.logInBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
