package com.example.darbas5;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataLoader extends AsyncTask<String, Void, InputStream> {
    private Context context;
    private CurrencyAdapter currencyAdapter;

    public DataLoader(Context context, CurrencyAdapter currencyAdapter) {
        this.context = context;
        this.currencyAdapter = currencyAdapter;
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            return connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        if (inputStream != null) {
            Parser parser = new Parser();
            parser.parse(inputStream, currencyAdapter);
        } else {
            Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show();
        }
    }
}

