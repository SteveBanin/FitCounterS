package com.example.fitcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.fitcounter.extra.MESSAGE";

    Button btnOutdoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btnOutdoor = findViewById(R.id.btn_outdoor);

        btnOutdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Main2Activity.this, Main3Activity.class);
                String message = btnOutdoor.getText().toString();
                x.putExtra(EXTRA_MESSAGE, message);
                startActivity(x);
            }
        });

    }
}
