package com.company;

public class Feminist implements Person {
    private Answer<String> stringAnswer = new Answer<>();
    private Answer<Emoji> emojiAnswer = new Answer<>();

    Feminist() {
        stringAnswer.set("ка ");
        emojiAnswer.set(new Emoji(5));
    }

    @Override
    public String answer(String word){
        return (word + stringAnswer.get() + emojiAnswer.get());
    }

    @Override
    public String name() {
        return "feminist";
    }
}
