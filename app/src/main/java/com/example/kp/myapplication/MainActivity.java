package com.example.kp.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    SimpleAdapter sa = null;
    List<HashMap<String, Object>> list = null;
    HashMap<String, Object> map_personn = null;
    ListView lv = null;
    static int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        String[] from = new String[]{"nom", "prenom","icon"};
        int[] to = new int[] {R.id.nom, R.id.prenom,R.id.photo};
        final int[] images = {R.drawable.femme,R.drawable.homme};


        sa = new CustomAdapter(this,list,R.layout.listcontenent, from, to);
        lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(sa);

        HashMap<String,Object> map1=new HashMap<>();
        map1.put("nom","medjir");
        map1.put("prenom","zakaria");
        map1.put("icon",R.drawable.homme);
        list.add(map1);
        sa.notifyDataSetChanged();

        Button buton_add = (Button)findViewById(R.id.buttonAdd);
        buton_add.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        map_personn = new HashMap<String, Object>();
                        map_personn.put("icon",images[i%images.length]);
                        map_personn.put("nom", "nom" +i);
                        map_personn.put("prenom", "prenom" +i);


                        i++;

                        list.add(map_personn);
                        sa.notifyDataSetChanged();
                        lv.setAdapter(sa);

                    }
                }
        );


        Button buton_clear = (Button)findViewById(R.id.buttonReset);
        buton_clear.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        list.clear();
                        sa.notifyDataSetChanged();
                        i=0;
                    }
                }
        );

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {



            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                list.remove(position);

                sa.notifyDataSetChanged();

                return true;

            }

        });

    }


    }

