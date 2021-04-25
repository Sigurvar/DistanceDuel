package com.sigurvar.distanceduel.utility;

import android.content.Context;

import com.sigurvar.distanceduel.R;

public enum Unit {

    BANANA((float)0.15, R.string.bananas),
    KILOMETER(1000, R.string.kilometers),
    METER(1, R.string.meters),
    CAR((float)4.5, R.string.cars);

    private float length;
    private int resourceId;

    Unit(float length, int resourceId) {
        this.length = length;
        this.resourceId = resourceId;
    }

    public float getLength() {
        return length;
    }
    public int getResourceId(){
        return resourceId;
    }
}
