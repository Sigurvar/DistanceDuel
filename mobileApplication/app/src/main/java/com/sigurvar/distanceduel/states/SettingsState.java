package com.sigurvar.distanceduel.states;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;

import com.sigurvar.distanceduel.R;

public class SettingsState extends State {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_state);
        aSwitch = (Switch)findViewById(R.id.switch1);
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
        }
    }

    public void switchTheme(View view){
        if(aSwitch.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}