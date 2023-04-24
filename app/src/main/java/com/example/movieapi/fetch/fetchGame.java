package com.example.movieapi.fetch;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi.adapter.movieAdapter;
import com.example.movieapi.data.movieData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class fetchGame extends AsyncTask<String,Void,String> {

    private ArrayList<movieData> values;
    private com.example.movieapi.adapter.movieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private Context context;

    public fetchGame(ArrayList<movieData> values, com.example.movieapi.adapter.movieAdapter movieAdapter,
                        RecyclerView recyclerView,Context context){
        this.values=values;
        this.movieAdapter=movieAdapter;
        this.recyclerView=recyclerView;
        this.context=context;
    }
    @Override
    protected String doInBackground(String... strings) {
        String query=strings[0];
        String BASE_URL="https://omdbapi.com/?apikey=5e20535a&type=game&";
        String QUERY_PARAM="s";
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        String movieJSONString=null;
        Uri build=Uri.parse(BASE_URL).buildUpon().appendQueryParameter(QUERY_PARAM,query).build();
        try {
            URL requestURL=new URL(build.toString());
            urlConnection=(HttpURLConnection)requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            StringBuilder builder=new StringBuilder();
            InputStream inputStream=urlConnection.getInputStream();
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line=reader.readLine())!=null){
                builder.append(line+"\n");
            }
            if (builder.length()==0){
                return null;
            }
            movieJSONString=builder.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return movieJSONString;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        values = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray movieArray = jsonObject.getJSONArray("Search");
            String title = null;
            String year = null;
            String gambar = null;
            String tipe = null;
            int i = 0;
            while(i<movieArray.length()){
                JSONObject movie = movieArray.getJSONObject(i);
                try{
                    title = movie.getString("Title");
                    gambar = movie.getString("Poster");
                    tipe = movie.getString("Type");
                    if(movie.has("Year")){
                        year = movie.getString("Year");
                    }else{
                        year = "";
                    }
                    movieData movieData= new movieData();
                    movieData.movieTitle = title;
                    movieData.movieYear = year;
                    movieData.movieGambar = gambar;
                    movieData.movieTipe = tipe;
                    values.add(movieData);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.movieAdapter = new movieAdapter(context, values);
        this.recyclerView.setAdapter(this.movieAdapter);
    }
}
