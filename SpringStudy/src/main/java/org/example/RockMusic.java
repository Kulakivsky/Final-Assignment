package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("someRockMusic")
public class RockMusic implements Music{
    private RockMusic() {}

    private List<String> rockMusicList = new ArrayList<String>();
    {
        rockMusicList.add("Whole lotta Love");
        rockMusicList.add("You shook me all might long");
        rockMusicList.add("Born to be wild");
    }

    @Override
    public List<String> getSong() {
        return rockMusicList;
    }
}
