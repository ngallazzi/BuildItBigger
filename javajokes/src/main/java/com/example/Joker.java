package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Joker {
    ArrayList<String> jokes = new ArrayList<>();

    public Joker() {
        jokes.add("Most people believe that if it ain't broke, don't fix it.\n" +
                "Engineers believe that if it ain't broke, it doesn't have enough features yet.");
        jokes.add("How do cows do mathematics? They use a cow-culator");
        jokes.add("Why did the blonde become a big basketball fan?\n" +
                "Because every time they stopped the clock, she thought that she had stopped aging.");
        jokes.add("What is the difference between a snowman and a snowwoman?\n" +
                "-\n" + "Snowballs.\n" + "\n");
        jokes.add("My wife’s cooking is so bad we usually pray after our food\n");
        jokes.add("Coco Chanel once said that you should put perfume on places where you want to be kissed by a man. But hell does that burn!");
        jokes.add("After many years of studying at a university, I’ve finally become a PhD… or Pizza Hut Deliveryman as people call it.");
    }

    public String getRandomJoke(){
        int index = randInt(0,jokes.size());
        return jokes.get(index);
    }

    private int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
