package edu.csumb.nevayeh.temperatureconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FtoC extends AppCompatActivity {

    EditText input;
    Button button;
    Double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fto_c);

        input = findViewById(R.id.inputFtoC);
        button = findViewById(R.id.buttonFtoC);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    value = Double.parseDouble(input.getText().toString());
                } catch (NumberFormatException e) {
                    Log.d("F_to_C", "Couldn't convert to double");
                    input.setText("0.0");
                    value = 0.0;
                }

                button.setText(fToC());
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(FtoC.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

    }

    private String fToC() {
        return String.format("%.2f", ((value-32) * 5 / 9)) + "";
    }
}
