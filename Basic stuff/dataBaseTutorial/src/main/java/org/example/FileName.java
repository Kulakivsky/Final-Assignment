package org.example;


import java.util.ArrayList;

public class FileName {

    public void nameGetter(ArrayList<String> urlList) {


        ArrayList<String> splitedNamesArray = new ArrayList<>();
        ArrayList<String> extensionsArray = new ArrayList<>();



        for (String url : urlList) {
            String[] splitedUrl = url.split("/");
            System.out.println(url);

            String[] videoName = splitedUrl[4].split("\\?");
            System.out.println(videoName[0]);

            splitedNamesArray.add(splitedUrl[3]);
        }


        for(int i = 0; i < splitedNamesArray.size(); i++) {
            if ((splitedNamesArray.get(i).contains("."))) {
                String[] splitedNames = splitedNamesArray.get(i).split("(\\.)");
                for (int j = 0; j < splitedNames.length; j++){
                    System.out.println(splitedNames[j]);
                }

                extensionsArray.add(splitedNames[1]);
            } else {
                extensionsArray.add("false");
            }
        }



    }
}