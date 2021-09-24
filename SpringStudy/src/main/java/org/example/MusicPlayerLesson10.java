package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayerLesson10 {

    private Music classicalMusic;
    private Music rapMusic;
    private Music rockMusic;

    @Autowired
    public MusicPlayerLesson10
            (@Qualifier("someClassicalMusic") Music classicalMusic,
             @Qualifier("someRapMusic") Music rapMusic,
             @Qualifier("someRockMusic") Music rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rapMusic = rapMusic;
        this.rockMusic = rockMusic;
    }




    public String playMusic(MusicGenres musicGenres) {
        switch (musicGenres) {
            case CLASSICALMUSIC:
                return classicalMusic.getSong().get((int)(Math.random()*3));

            case RAPMUSIC:
                return rapMusic.getSong().get((int)(Math.random()*3));

            default:
                return rockMusic.getSong().get((int)(Math.random()*3));

        }
    }
}
