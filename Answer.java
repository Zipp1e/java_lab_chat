package com.company;

public class Answer<T> {
    private String ans;
    Answer(){
        ans = "";
    }

    public void set(T some) {
        ans = some.toString();
    }

    public String get() {
        return ans;
    }
}
