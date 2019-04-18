package edu.csumb.nevayeh.temperatureconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView mTextView;
    Button button;
    Double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.inputCtoF);
        button = findViewById(R.id.buttonCtoF);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    value = Double.parseDouble(input.getText().toString());
                } catch (NumberFormatException e) {
                    Log.d("C_TO_F", "Coudln't convert to double");
                }

                button.setText(cToF());
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, FtoC.class);
                startActivity(intent);
                return false;
            }
        });
    }

    // F to C ==> ((val -32 ) * 5 ) / 9
    private String cToF(){
        return ((value * 9) / 5 + 32) + "";
    }
}
