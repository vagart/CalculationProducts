package com.example.natali.calculationproducts;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ConnectionService(){
        ConnectToService conTS = new ConnectToService();
        conTS.execute();
    }

    public void onClikc(View view) {
        ConnectionService();
    }

    class ConnectToService extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            try {
                String myResponce = "";
                myResponce = sendGet();
                return myResponce;
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONObject datJsonObject = null;
            String responce = "";
            Integer kodResponce = 0;

            try {
                /*dataJsonObj = new JSONObject(s);
                JSONObject responseJs = dataJsonObj.getJSONObject("Error");
                response = responseJs.getString("description");
                kodresponse = responseJs.getInt("code");*/

                Toast toast = Toast.makeText(MainActivity.this,s,LENGTH_LONG);

                toast.setGravity(Gravity.CENTER, 0,160);
                toast.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public String sendGet() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        try{

            String mobServis = "http://86.62.72.22:8444/Razrabotka/hs/ListProducts/GETList";

            //используем http запрос
            URL objUrl = new URL(mobServis);
            HttpURLConnection conn = (HttpURLConnection) objUrl.openConnection();
            conn.setRequestMethod("GET");
            InputStream responcse = conn.getInputStream();
            Scanner s = new Scanner(responcse);
            String result = "";
            while (s.hasNext()) result = result + " " + s.next();

            /*TextView tt = findViewById(R.id.textViewPass);
            tt.setText(result);
            */
            return result;
        } catch (MalformedURLException e) {
            return e.toString();
        }
    }


}
