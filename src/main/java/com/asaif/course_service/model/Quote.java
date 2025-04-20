package com.asaif.course_service.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Quote {
    private String q;
    private String a;

    public Quote(String q, String a) {
        this.q = q;
        this.a = a;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
