package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("someRapMusic")
public class RapMusic implements Music{
    private RapMusic() {}

    private List<String> rapMusicList = new ArrayList<String>();
    {
        rapMusicList.add("Passin me by");
        rapMusicList.add("My name is");
        rapMusicList.add("Fuck tha police");
    }

    @Override
    public List<String> getSong() {
        return rapMusicList;
    }
}
