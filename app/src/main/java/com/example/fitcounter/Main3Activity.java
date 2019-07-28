package com.example.fitcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    //TextView textView;
    Spinner spinnerOutdoor;
    Spinner spinnerIndoor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main3);





        spinnerOutdoor = findViewById(R.id.spinnerOutdoor);

        spinnerOutdoor.setPrompt("Select Outdoor Activity");

        String[] items = new String[]{"Select Outdoor Activity","Running", "Walking", "Jogging", "Cycling", "Hiking"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerOutdoor.setAdapter(adapter);
        spinnerOutdoor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Intent x;

                String label = spinnerOutdoor.getSelectedItem().toString();
                String selectedItem = spinnerOutdoor.getItemAtPosition(i).toString();

                switch (label) {
                    case "Running":
                        x = new Intent(Main3Activity.this, MainActivity.class);
                        x.putExtra("status", selectedItem);
                        startActivity(x);
                    case "Walking":
                        x = new Intent(Main3Activity.this, MainActivity.class);
                        x.putExtra("status", selectedItem);
                        startActivity(x);
                        break;
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //////////////////////////////////////////////////////////////

        spinnerIndoor = findViewById(R.id.spinnerIndoor);

        spinnerIndoor.setPrompt("Select Indoor Activity");

        String[] itemss = new String[]{ "Select Indoor Activity","Swimming", "Climbing stair", "Squatting", "PushUps" };

        ArrayAdapter<String> adapters = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemss);
        spinnerIndoor.setAdapter(adapters);







//        Intent x = getIntent();
//        String message = x.getStringExtra(Main2Activity.EXTRA_MESSAGE);
//        textView = findViewById(R.id.textView2);
//        textView.setText(message);
    }

}
