package com.example.ktl2_ct2_nguyenhuyhoang.Model;

public class Flag {
    private String png;
    private String svg;
    private String alt;
    public Flag() {
    }

    // Constructor có tham số
    public Flag(String png, String svg, String alt) {
        this.png = png;
        this.svg = svg;
        this.alt = alt;
    }
    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
