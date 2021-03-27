package com.sigurvar.distanceduel.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.sigurvar.distanceduel.R;

public class SettingsState extends State {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_state);
    }
}