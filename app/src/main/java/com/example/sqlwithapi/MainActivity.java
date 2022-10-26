package com.example.sqlwithapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdapterMask adapterMask;
    private List<Mask> listAirlines=  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView airlinesList = findViewById(R.id.AirList);
        adapterMask = new AdapterMask(MainActivity.this,listAirlines);
        airlinesList.setAdapter(adapterMask);

        new GetAirLines().execute();
    }
    private class GetAirLines extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:5101/NGKNN/ЗименковНИ/api/airlines");//Строка подключения к нашей API
                //URL url = new URL("https://ngknn.ru:5101/NGKNN/%D0%9C%D0%B0%D0%BC%D1%88%D0%B5%D0%B2%D0%B0%D0%AE%D0%A1/api/Products");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //вызываем нашу API

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                /*
                BufferedReader успрощает чтение текста из потока символов
                InputStreamReader преводит поток байтов в поток символов
                connection.getInputStream() получает поток байтов
                */
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    result.append(line);//кладет строковое значение в потоке
                }
                return result.toString();

            } catch (Exception exception) {

                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                JSONArray tempArray = new JSONArray(s);//преоброзование строки в json массив
                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject airlinesJson = tempArray.getJSONObject(i);//Преобразование json объекта в нашу структуру
                    Mask tempProduct = new Mask(
                            airlinesJson.getInt("ID"),
                            airlinesJson.getString("airline_name"),
                            airlinesJson.getString("airline_website"),
                            airlinesJson.getString("image")
                    );
                    listAirlines.add(tempProduct);
                    adapterMask.notifyDataSetInvalidated();
                }
            } catch (Exception ignored) {


            }
        }
    }
}