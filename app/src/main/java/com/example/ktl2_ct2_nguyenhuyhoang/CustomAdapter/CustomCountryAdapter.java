package com.example.ktl2_ct2_nguyenhuyhoang.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.ktl2_ct2_nguyenhuyhoang.CountryDetailActivity;
import com.example.ktl2_ct2_nguyenhuyhoang.Model.Country;
import com.example.ktl2_ct2_nguyenhuyhoang.R;

import java.util.ArrayList;

public class CustomCountryAdapter extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<Country> list = new ArrayList<>();
    public CustomCountryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Country> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutItem = resource;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Country item = list.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewRegion = convertView.findViewById(R.id.textViewRegion);
        ImageView imageViewItem = convertView.findViewById(R.id.imageViewItem);

        textViewName.setText(item.getCommonName());
        textViewRegion.setText("Continent: " + item.getContinent());
        Glide.with(context).load(item.getImgUrl()).into(imageViewItem);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryDetailActivity.class);
                intent.putExtra("country", item);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
