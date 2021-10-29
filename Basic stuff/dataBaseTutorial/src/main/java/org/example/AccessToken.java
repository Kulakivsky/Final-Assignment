package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AccessToken {

    final String USERNAME = "rep4yk";
    final String PASSWORD = "MACros1997";
    final String CLIENT_ID = "zQ7-W9Hmp-NUqCIcECVNcA";
    final String CLIENT_SECRET = "E2OQqKiZ-7WdohvKeR6Mlqdx-HZTOQ";


    public String accessTokeGetter() {

        String stringUrl = "https://www.reddit.com/api/v1/access_token";

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(CLIENT_ID, CLIENT_SECRET) // client id and secret
        );
        HttpClient httpClient = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        HttpPost httpPost = new HttpPost(stringUrl); // creat post

        List<NameValuePair> params = new ArrayList<NameValuePair>(3); //setup post params

        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("username", USERNAME));
        params.add((new BasicNameValuePair("password", PASSWORD)));

        String responseString = null;

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            httpPost.setHeader("User-Agent", "/u/user v1.0");
            HttpResponse response;

            try {
                response = httpClient.execute(httpPost);
                try {
                    responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
//                    System.out.printf(responseString);
                } catch (IIOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IIOException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String JsonGetter(String auth) throws URISyntaxException {

        String stringUrl = "https://oauth.reddit.com/r/oddlysatisfying/top";

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(CLIENT_ID, CLIENT_SECRET) // client id and secret
        );
        HttpClient httpClient = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        HttpGet httpGet = new HttpGet(stringUrl); // get request

        List<NameValuePair> params = new ArrayList<NameValuePair>(3); //setup post params

        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("username", USERNAME));
        params.add((new BasicNameValuePair("password", PASSWORD)));

        String responseString = null;

            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("grant_type", "password")
                    .addParameter("username", USERNAME)
                    .addParameter("password", PASSWORD)
                    .addParameter("limit", "25")
                    .build();
            ((HttpRequestBase) httpGet).setURI(uri);

            httpGet.addHeader("User-Agent", "/u/user v1.0");
            httpGet.addHeader("Authorization", auth);
            HttpResponse response;
            try {
                response = httpClient.execute(httpGet);
                try {
                    responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
//                    System.out.printf(responseString);
                } catch (IIOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IIOException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return responseString;
    }
}

