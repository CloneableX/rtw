package com.clo.rtw.friut;

public class Juicer {
    private Peelable peelable;
    private Peeler peeler;

    public Juicer(Peelable peelable, Peeler peeler) {
        this.peelable = peelable;
        this.peeler = peeler;
    }

    public Peelable getPeelable() {
        return peelable;
    }

    public Peeler getPeeler() {
        return peeler;
    }
}
