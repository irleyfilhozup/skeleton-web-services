package org.example.ws.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Greeting {

    @Id
    @GeneratedValue
    private int id;
    private String text;

    public Greeting() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}