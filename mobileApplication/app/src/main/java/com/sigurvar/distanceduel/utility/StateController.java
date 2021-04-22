package com.sigurvar.distanceduel.utility;

import com.sigurvar.distanceduel.states.State;

public class StateController {
    private static StateController stateController = new StateController();
    private StateController(){}
    public static StateController getInstance() {return stateController;}
    private State state;

    public State getState(){
        return state;
    }
    public void setState(State state){this.state = state;}
}
