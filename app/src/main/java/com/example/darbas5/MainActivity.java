package com.example.darbas5;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editTextCurrency;
    private CurrencyAdapter currencyAdapter;
    private DataLoader dataLoader;
    private ArrayList<Currency> originalCurrencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editTextCurrency = findViewById(R.id.editTextCurrency);

        ArrayList<Currency> currencyList = new ArrayList<>();
        currencyAdapter = new CurrencyAdapter(this, currencyList);
        listView.setAdapter(currencyAdapter);

        dataLoader = new DataLoader(this, currencyAdapter);
        dataLoader.execute("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");

        // TextWatcher to filter currency list as user types
        editTextCurrency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCurrencyList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Method for filtering currency list based on user input
    private void filterCurrencyList(String text) {
        if (originalCurrencyList.isEmpty()) {
            originalCurrencyList.addAll(currencyAdapter.getOriginalList());
        }

        if (text.isEmpty()) {
            currencyAdapter.filter(originalCurrencyList);
        } else {
            ArrayList<Currency> filteredList = new ArrayList<>();
            for (Currency currency : originalCurrencyList) {
                if (currency.getCurrencyCode().equalsIgnoreCase(text)) {
                    filteredList.add(currency);
                }
            }

            currencyAdapter.filter(filteredList);
        }
    }
}