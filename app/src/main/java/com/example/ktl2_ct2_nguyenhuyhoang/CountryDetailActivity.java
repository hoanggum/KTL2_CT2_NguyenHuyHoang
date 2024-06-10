package com.example.ktl2_ct2_nguyenhuyhoang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ktl2_ct2_nguyenhuyhoang.Model.Country;
import com.example.ktl2_ct2_nguyenhuyhoang.databinding.ActivityCountryDetailBinding;

public class CountryDetailActivity extends AppCompatActivity {

    private ActivityCountryDetailBinding activityCountryDetailBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCountryDetailBinding = ActivityCountryDetailBinding.inflate(getLayoutInflater());
        setContentView(activityCountryDetailBinding.getRoot());
        Intent intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("country");

        Glide.with(this).load(country.getImgUrl()).into(activityCountryDetailBinding.imageViewItem);
        activityCountryDetailBinding.textViewCommonName.setText(country.getCommonName());
        activityCountryDetailBinding.textViewOfficialName.setText("Official Name: " + country.getOfficialName());
        activityCountryDetailBinding.textViewCapital.setText("Capital: " + country.getCapital());
        activityCountryDetailBinding.textViewContinent.setText("Continent: " + country.getContinent());
        activityCountryDetailBinding.textViewLanguage.setText("Language: " + country.getLanguage());
        activityCountryDetailBinding.textViewPopulation.setText("Population: " + String.valueOf(country.getPopulation()));

        addEvents();
    }
    void addEvents(){
        activityCountryDetailBinding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}