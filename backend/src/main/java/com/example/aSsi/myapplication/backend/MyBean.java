package com.example.aSsi.myapplication.backend;

//import com.example.aSsi.myapplication.TellJokes;

import com.example.MyClass;


public class MyBean {

    private MyClass jokeString;

    public MyBean() {
        jokeString = new MyClass();
    }

    public String getJoke() {

        return  jokeString.randomJoke();

    }

}
