package com.example.ktl2_ct2_nguyenhuyhoang.Model;

public class NativeName {
    private Por por;
    public NativeName() {
    }

    // Constructor có tham số
    public NativeName(Por por) {
        this.por = por;
    }
    public Por getPor() {
        return por;
    }

    public void setPor(Por por) {
        this.por = por;
    }
}
