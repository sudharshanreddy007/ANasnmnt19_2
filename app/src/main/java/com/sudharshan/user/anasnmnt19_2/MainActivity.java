package com.sudharshan.user.anasnmnt19_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//created main activity whcih extends appcompatactivity
public class MainActivity extends AppCompatActivity {
    //taking variables
    ListView listView;
    List<Model> modelList ;
    Model model;
    MyAdapter adapter;
    //it is called once the main activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        //inserting the url of the movie task to execute it
        new MovieTask().execute("http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c");

    }
    //creating a private class called movie task which extends asyntask with the params of String void and result of the list<model>.
    private class MovieTask extends AsyncTask<String,Void,List<Model>>{

        //Asyntask has three properties which is first do in background with the string params
        @Override
        protected List<Model> doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream stream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String inputstring=null;
                while ((inputstring =reader.readLine()) != null) {

                    buffer.append(inputstring);


                }//creating  json object  to get the strings
                JSONObject jsonObject =new JSONObject(buffer.toString());
                //inside the json object we have square brackets which is known as jsonarray and  the string values of getjsonarray
                JSONArray jsonArray =jsonObject.getJSONArray("results");
                modelList=new ArrayList<>();

                for (int i =0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String name =jsonObject1.getString("original_name");
                    String votes =jsonObject1.getString("vote_count");
                    String id =jsonObject1.getString("id");
                    model=new Model();
                    model.setName(name);
                    model.setVotes("Vote: "+votes);
                    model.setId("Id: "+id);
                    modelList.add(model);

                }
                return modelList;

            } catch (Exception e) {
            }
            return null;

        }

        //in this onPostExecute method  call the  listmodel from the myadapter and view the list
        @Override
        protected void onPostExecute(List<Model> models) {
            adapter= new MyAdapter(MainActivity.this,R.layout.list_model,models);
            listView.setAdapter(adapter);
        }
    }
}