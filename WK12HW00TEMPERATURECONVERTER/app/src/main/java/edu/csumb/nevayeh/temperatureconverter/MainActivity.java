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
                    input.setText("0.0");
                    value = 0.0;
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


    private String cToF(){
        return String.format("%.2f", ((value * 9 / 5 + 32))) + "";
    }
}
