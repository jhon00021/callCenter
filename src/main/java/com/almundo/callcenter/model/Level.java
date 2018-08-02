package com.almundo.callcenter.model;

/**
 * Created by gdxdeveloper1 on 2/08/18.
 */
public enum Level {


    OPERATOR(1),
    SUPERVISOR(2),
    DIRECTOR(3);

    private final Integer value;

    Level(Integer levelCode) {
        this.value = levelCode;
    }

    public Integer getValue() {
        return this.value;
    }


}
