package com.lovelystickersua.controller;

/**
 * Created by devnull on 12.11.16.
 */
public enum PAGE {
    REFRESH("redirect:/"), HOME("home"), PROFILE("profile"),
    PRODUCT("product"), LOGIN("loginpage"), REGISTER("register"),
    USERS("users"), LOADING("loading"), STORE("store");
    private String value;

    PAGE(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
