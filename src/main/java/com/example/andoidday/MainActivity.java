package com.example.andoidday;

import static com.example.andoidday.Function.wishMe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SpeechRecognizer sr;
    private TextView tvResult;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        initializeTextToSpeech();
        initializeResult();
    }

    private void initializeTextToSpeech() {
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (tts.getEngines().size()==0){
                    Toast.makeText(MainActivity.this, "Engine not Availalble", Toast.LENGTH_SHORT).show();
                }
                else{
                    String s = wishMe();
                    speak("Initializing Jarvis "+s);
                }
            }
        });
    }


    private void speak(String msg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null,null);
        }else{
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    private void findById() {
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    private void initializeResult() {
        if (SpeechRecognizer.isRecognitionAvailable(this)){
            sr = SpeechRecognizer.createSpeechRecognizer(this);
            sr.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    ArrayList<String> result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    Toast.makeText(MainActivity.this, ""+result.get(0), Toast.LENGTH_SHORT).show();
                    tvResult.setText(result.get(0));
                    response(result.get(0));

                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }
    }

    private void response(String msg) {
        String msgs = msg.toLowerCase();
        if (msgs.indexOf("hi")!= -1){
            speak("Hello Sir I How are you ?");
        }
        if (msgs.indexOf("fine") == -1) {
         speak("it's good to know that you are fine....How may I help you 7");
     }
     else if (msgs.indexOf("1 am not fine")!= -1){
    speak( "Please take care Master");
    }
    }



public void startRecording(View view) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);

        sr.startListening(i);
    }
}