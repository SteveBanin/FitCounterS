package com.example.fitcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Annotation extends AppCompatActivity implements SensorEventListener{


    private TextToSpeech textToSpeech;

    TextView txt1;

    SensorManager sensorManager;

    boolean running = false;

    TextView txtStatus;

    ListView listView;

    TextView txtListening;

    SlidingWindow sw = new SlidingWindow();

    Button btn;

    boolean sensorFlag = true;

    int steps;

    String currentActivity;

    String selectedItem;

    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        Intent intent = getIntent();
        value = intent.getStringExtra("status");

        txt1 = (TextView) findViewById(R.id.txt1);
        txtListening = findViewById(R.id.txtSlidingWindow);
        txtStatus = (TextView) findViewById(R.id.labelStatus);
        btn = (Button) findViewById(R.id.btn);
        listView = findViewById(R.id.listview);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorFlag = !sensorFlag;
                if (sensorFlag) {
                    txtStatus.setText("Sensor is on!");
//                    Intent intent = getIntent();
//                    String val = intent.getStringExtra("status");
                    //Log.i("myTag", value);
                    txtListening.setText(value);

                    String carry = txtListening.getText().toString();

                    Intent x = new Intent(Annotation.this, Annotation.class);
                    x.putExtra("status", carry);
                    startActivity(x);
                }
                else {
                    txtStatus.setText("Sensor is off!");
                }
            }
        });


    }
    @Override
    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor !=null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        }else {
            Toast.makeText(this,"Sensor not found", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onPause(){
        super.onPause();
        running = false;

    }
    @Override
    public void onSensorChanged(SensorEvent event){

        if (sensorFlag) {

            steps += 1;



            sw.add();

            ArrayList<String> count;

            txt1.setText(Integer.toString(steps));

            if (sw.getAvg() < 300) {
                updateActivity("Running");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, String(steps));
                listView.setAdapter(adapter);
            }
            else {
                updateActivity("Walking");
            }


        }

//        if(running){
//            txt1.setText(String.valueOf(event.values[0]));
//        }



    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    private void updateActivity(String currentActivity) {

        if (currentActivity != this.currentActivity) {
            textToSpeech.speak(currentActivity, TextToSpeech.QUEUE_FLUSH, null);
            this.currentActivity = currentActivity;
        }

    }


}
