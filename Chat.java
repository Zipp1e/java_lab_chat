package com.company;

class Chat {

    Person[] people = {new Feminist(), new Rapper()};
    private int i = 0;

    Chat() {
    }

    public String getReply(String message) { return people[i].answer(message); }

    public void setFeminist() {
        i = 0;
    }

    public void setRapper() {
        i = 1;
    }

    public Person[] getPeople() {
        return people;
    }
}
