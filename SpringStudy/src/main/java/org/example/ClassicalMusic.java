package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component ("someClassicalMusic")
public class ClassicalMusic implements Music {
    private ClassicalMusic() {}

    private List<String> classicalMusicList = new ArrayList<String>();
    {
        classicalMusicList.add("Hungarian Rhapsody");
        classicalMusicList.add("Piano Sonata No. 14");
        classicalMusicList.add("Ave Maria");
    }

    @Override
    public List<String> getSong() {
        return classicalMusicList;
    }
}
