package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext2.xml"
        );



        //Lesson 10
        MusicGenres musicGenres = MusicGenres.ROCKMUSIC;

        MusicPlayerLesson10 musicPlayerLesson10 = context.getBean("musicPlayerLesson10", MusicPlayerLesson10.class);
        System.out.println(musicPlayerLesson10.playMusic(musicGenres));

        //Lesson 9
//        List<Music> music = new ArrayList<>();
//
//        music.add(context.getBean("someClassicalMusic", Music.class));
//        music.add(context.getBean("someRapMusic", Music.class));
//        music.add(context.getBean("someRockMusic", Music.class));
//
//        MusicPlayer musicPlayer = new MusicPlayer();
//        musicPlayer.setMusicList(music);
//
//        musicPlayer.playMusic();


        // Everything before lesson 9
//        ClassicalMusic classicalMusic = context.getBean("classicalMusicBean", ClassicalMusic.class);
//        System.out.println(classicalMusic.getSong());

//        Music music = context.getBean("musicBean", Music.class);
//        MusicPlayer musicPlayer = new MusicPlayer(music);

//        MusicPlayer firstMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//        MusicPlayer secondMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//
//        boolean comparison = firstMusicPlayer == secondMusicPlayer;
//
//        System.out.println(comparison);
//        System.out.println(firstMusicPlayer);
//        System.out.println(secondMusicPlayer);
//
//        firstMusicPlayer.setVolume(20);
//
//        System.out.println(firstMusicPlayer.getVolume());
//        System.out.println(secondMusicPlayer.getVolume());
//
//        System.out.println(musicPlayer.getName());
//        System.out.println(musicPlayer.getVolume());

        context.close();
    }
}
