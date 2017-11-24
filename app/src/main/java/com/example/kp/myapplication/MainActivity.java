package com.example.kp.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SimpleAdapter sa = null;
    private static String urlImage = "http://lorempixel.com/200/200/nature/";
    List<HashMap<String, Object>> list = null;
    HashMap<String, Object> map_film = null;
    ListView lv = null;
    static int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        String[] from = new String[]{"titre", "acteur","icon"};
        int[] to = new int[] {R.id.titre, R.id.acteur,R.id.photo};
        final int[] images = {R.drawable.nophoto};
        ImageView photo=(ImageView) findViewById(R.id.photo);

        sa = new CustomAdapter(this,list,R.layout.listcontenent, from, to);
        lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(sa);

        HashMap<String,Object> map1=new HashMap<>();
        map1.put("titre","Fast & Furious");
        map1.put("acteur","Vin Diesel");
        map1.put("icon",R.drawable.nophoto);
        list.add(map1);
        sa.notifyDataSetChanged();

        Button buton_add = (Button)findViewById(R.id.buttonAdd);
        buton_add.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        map_film = new HashMap<String, Object>();
                        map_film.put("icon",R.drawable.nophoto);
                        map_film.put("titre", "titre" +i);
                        map_film.put("acteur", "acteur" +i);


                        i++;

                        list.add(map_film);
                        sa.notifyDataSetChanged();
                        lv.setAdapter(sa);

                    }
                }
        );


        Button update = (Button) findViewById(R.id.buttonUpdate);

        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });



        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

             /*   list.remove(position);
                sa.notifyDataSetChanged();
                return true;
                */

                DownloadImageTask downloadImageTask = new DownloadImageTask();
                //map.put("photo", images[i % images.length] + "");

                map_film.put("photo", downloadImageTask.execute(urlImage));
                
                sa.notifyDataSetChanged();
                return true ;

            }

        });

    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... stringUrl) {

            Log.i(this.getClass().getCanonicalName(), "Entre doInBackground");
            InputStream input = null;
            HttpURLConnection connection = null;
            Bitmap bitmap = null;

            try {

                URL url = new URL(stringUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                input = new BufferedInputStream(connection.getInputStream());
                bitmap = BitmapFactory.decodeStream(input);

            } catch (Exception e) {
                Log.e(this.getClass().getCanonicalName(), "Erreur lors de la création de l'image");
            } finally {
                try {
                    if (input != null)
                        input.close();
                } catch(IOException ignored) {
                    Log.e(this.getClass().getCanonicalName(), "Erreur input");
                }
                if (connection != null)
                    connection.disconnect();
            }
            Log.i(this.getClass().getCanonicalName(), "Quitte doInBackground");
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //imageView.setImageBitmap(bitmap);
            //map.put("photo", bitmap);
            Log.i(this.getClass().getCanonicalName(), "Entre onPostExecute");
        }
    }
}

