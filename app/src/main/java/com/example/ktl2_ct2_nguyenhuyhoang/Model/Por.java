package com.example.ktl2_ct2_nguyenhuyhoang.Model;

public class Por {
    private String official;
    private String common;
    public Por() {
    }

    // Constructor có tham số
    public Por(String official, String common) {
        this.official = official;
        this.common = common;
    }
    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}
