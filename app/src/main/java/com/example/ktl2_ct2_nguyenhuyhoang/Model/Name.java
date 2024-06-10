package com.example.ktl2_ct2_nguyenhuyhoang.Model;

public class Name {
    private String common;
    private String official;
    private NativeName nativeName;
    public Name() {
    }

    // Constructor có tham số
    public Name(String common, String official, NativeName nativeName) {
        this.common = common;
        this.official = official;
        this.nativeName = nativeName;
    }
    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public NativeName getNativeName() {
        return nativeName;
    }

    public void setNativeName(NativeName nativeName) {
        this.nativeName = nativeName;
    }
}
