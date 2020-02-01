package com.clo.rtw.friut;

public class Peeler {
    private Peelable peelable;

    public Peeler(Peelable peelable) {
        this.peelable = peelable;
    }

    public Peelable getPeelable() {
        return peelable;
    }
}
