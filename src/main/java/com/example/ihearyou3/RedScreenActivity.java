package com.example.ihearyou3;



import android.graphics.Color;
import android.os.Bundle;

public class RedScreenActivity  extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_screen);
        getWindow().getDecorView().setBackgroundColor(Color.RED);
    }

}

