package com.aarzu.waqt.model;

/**
 * Created by Drac Android on 2/10/2017.
 */

public class JSONResponse {


    private AllTask[] event;
    private AllTask[] tshirt;
    private AllTask[] sher;
    private AllTask[] iftar;
    private AllTask[] mosque;
    private AllTask[] namaz;

    public AllTask[] getNamaz() {
        return namaz;
    }

    public AllTask[] getIftar() {
        return iftar;
    }

    public AllTask[] getSher() {
        return sher;
    }

    public AllTask[] getTshirt() {
        return tshirt;
    }

    public AllTask[] getMosque() {
        return mosque;
    }

    public AllTask[] getEvent() {
        return event;
    }
}

