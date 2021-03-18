package com.sigurvar.distanceduel.states;

import com.sigurvar.distanceduel.utility.StateController;

public abstract class State {

    public void render(){

    }

    public StateController getStateController(){
        return null;
    }

    public void dispose(){

    }
}
