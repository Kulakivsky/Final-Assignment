package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private JSONObject tempObject;
    private String url;
    private String fileName;
    private String fileExtension;
    private String caption;
    private int score;
    private boolean is_Video;
    private int size;
    private String id;
    private String telegramChannel;



    public static void main(String[] args) {

       long periodOfRedditTokenAccess = TimeUnit.SECONDS.toMillis(10);

        Timer timer = new Timer();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Action was done");
            }
        }, 0, periodOfRedditTokenAccess);


    }

}



    /*


    public static void main(String[] args) throws URISyntaxException, IOException {


        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = "1856381804:AAGY6hRvSIF5BEJMTjDtlBDB4tdUheijs78";
        String chatId = "@sdfgdgrgdgfgr";
        String text = "Hello world!";


        urlString = String.format(urlString, apiToken, chatId, text);

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        StringBuilder sb = new StringBuilder();
        InputStream is = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        String response = sb.toString();

     *
     */



// Do what you want with response



/*
        AccessToken accessToken = new AccessToken();

        String responseString = accessToken.accessTokeGetter();

        System.out.println(responseString);

        JSONObject jsonObject = new JSONObject(responseString);

        String value = jsonObject.getString("token_type") + " " + jsonObject.getString("access_token");

        String jsonResponseString = accessToken.JsonGetter(value);
        JSONObject jsonResponse = new JSONObject(jsonResponseString);
        JSONArray jsonChildrenArray = jsonResponse.getJSONObject("data").getJSONArray("children");

        ArrayList<String> urlList = new ArrayList<>(25);


        for (int i = 0; i < jsonChildrenArray.length();i++) {
            JSONObject tempObject = jsonChildrenArray.getJSONObject(i);

            // checking if it`s a video or not
            if (tempObject.getJSONObject("data").getBoolean("is_video")) {
                urlList.add(tempObject.getJSONObject("data").getJSONObject("media").getJSONObject("reddit_video").getString("fallback_url"));
            } else {}
        }

        FileName fileName = new FileName();
        fileName.nameGetter(urlList);
*/



