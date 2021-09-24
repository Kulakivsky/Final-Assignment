package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Computer  {
    private int id;
    private MusicPlayerLesson10 musicPlayerLesson10;

    @Autowired
    public Computer(MusicPlayerLesson10 musicPlayerLesson10) {
        this.id = 1;
        this.musicPlayerLesson10 = musicPlayerLesson10;
    }

    @Override
    public String toString() {
        return "Computer " + id;
    }
}
