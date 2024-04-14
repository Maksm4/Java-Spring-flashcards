package org.tpo3_1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entry
{

    @Id
    private int id;
    private String polish;
    private String english;
    private String german;

    public Entry() {}

    public Entry(String english, String german, String polish){
        this.id = (int)(Math.random() * 2000000000) + 1;
        this.polish = polish;
        this.english = english;
        this.german = german;
    }

    @Override
    public String toString() {
        return  "id: " + id + "|" +
                "polish= " + polish + "|" +
                "english= " + english + "|" +
                "german= " + german + "\n";
    }

    public String getPolish() {
        return polish;
    }

    public String getEnglish() {
        return english;
    }

    public String getGerman() {
        return german;
    }

    public int getId() {
        return id;
    }

    public void setPolish(String polish) {
        this.polish = polish;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public void setGerman(String german) {
        this.german = german;
    }
}
