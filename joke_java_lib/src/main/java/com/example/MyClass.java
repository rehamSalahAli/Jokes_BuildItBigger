package com.example;

import java.util.Random;

public class MyClass {

    private String[] jokesList;

    private Random randomjoke;


    public MyClass() {

        jokesList = new String[6];

        jokesList[0] = "Optimist: The glass is half full.\n" +
                "\n" +
                "Pessimist: The glass is half empty.\n" +
                "\n" +
                "Mother: Why did not you use a coaster!\n";

        jokesList[1] = "Web site log in: Sorry, your password 257EeffQ@# is not secure enough. \n" +
                "-\n" +
                "Cash machine login 1234: Here is your 1000 dollars.\n";

        jokesList[2] = "Do not go to the bathroom in a dream. It is a trap!\n" +
                "\n";

        jokesList[3] = "When somebody makes you really angry, count to three. When you get to two, punch them in the face. They will not be expecting that.\n" +
                "\n";

        jokesList[4] = "Do you know how to make a dumb person curious? \n" +
                "-\n" +
                "No, how? \n" +
                "-\n" +
                "I will tell you tomorrow  \n"
        ;

        jokesList[5] = " Husband: Wow, honey, you look really different today. Did you do something to your hair?\n" +
                "-\n" +
                "Wife: Michael, I am over here!";
        randomjoke = new Random();
    }


    public String[] getJoke() {

        return jokesList;
    }


    public String randomJoke() {

        return jokesList[randomjoke.nextInt(jokesList.length)];
    }


}
