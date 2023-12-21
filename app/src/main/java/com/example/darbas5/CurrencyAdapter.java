package com.example.darbas5;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CurrencyAdapter extends ArrayAdapter<Currency> {
    private Context context;
    private List<Currency> currencyList;
    private List<Currency> originalList;

    public CurrencyAdapter(Context context, ArrayList<Currency> list) {
        super(context, 0, list);
        this.context = context;
        this.currencyList = list;
        this.originalList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        Currency currentCurrency = currencyList.get(position);

        TextView currencyCode = listItem.findViewById(R.id.textViewCurrencyCode);
        TextView exchangeRate = listItem.findViewById(R.id.textViewExchangeRate);

        currencyCode.setText(currentCurrency.getCurrencyCode());
        exchangeRate.setText(String.format(Locale.getDefault(), "%.2f", Double.parseDouble(currentCurrency.getExchangeRate())));

        return listItem;
    }

    public void filter(ArrayList<Currency> filteredList) {
        currencyList.clear();
        currencyList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public List<Currency> getOriginalList() {
        return originalList;
    }

    public void add(Currency currency) {
        currencyList.add(currency);
        originalList.add(currency);
        notifyDataSetChanged();
    }
}