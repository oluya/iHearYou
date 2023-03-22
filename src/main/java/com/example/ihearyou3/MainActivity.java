package com.example.ihearyou3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private ImageButton imageButton;
    private static final int SPEECH_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this,this);
        imageButton = findViewById(R.id.clickbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySpeechRecognizer();
            }
        });
    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, " I ONLY PROCESS RED OR BLUE ");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0).toLowerCase();
            if (spokenText.equals("blue")) {
                displayBlueScreen();
                speakText("Here is the blue screen");
            } else if (spokenText.equals("red")) {
                displayRedScreen();
                speakText("Here is the red screen");
            } else {
                String message = "Sorry,I am only modelled to process Red or Blue";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                speakText(message);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void displayBlackScreen() {
        Intent intent = new Intent(this, BlackScreen.class);
        startActivity(intent);
    }


    private void displayBlueScreen() {
        Intent intent = new Intent(this, BlueScreenActivity.class);
        startActivity(intent);
    }

    private void displayRedScreen() {
        Intent intent = new Intent(this, RedScreenActivity.class);
        startActivity(intent);
    }

    private void speakText(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.UK);
        } else {
            Toast.makeText(this, "Text-to-speech initialization failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}