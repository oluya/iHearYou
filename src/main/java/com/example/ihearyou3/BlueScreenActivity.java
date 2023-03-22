package com.example.ihearyou3;

import android.graphics.Color;
import android.os.Bundle;

public class BlueScreenActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_screen); // replace with your layout
        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
    }

}
