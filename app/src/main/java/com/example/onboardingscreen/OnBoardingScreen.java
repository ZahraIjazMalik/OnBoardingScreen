package com.example.onboardingscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class OnBoardingScreen extends AppCompatActivity {
    OnBoardingAdapter onBoardingAdapter;
    OnBoardingData onBoardingData;
    ArrayList<OnBoardingData> onBoardingDataArrayList;
    ViewPager2 viewPager;
    Button btn;
    ImageView skipBtn;
    MaterialCardView c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        viewPager = findViewById(R.id.viewPager);
        skipBtn = findViewById(R.id.skipBtn);
        btn = findViewById(R.id.nextBtn);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);

        onBoardingDataArrayList = new ArrayList<>();

        onBoardingData = new OnBoardingData();
        onBoardingData.setImg(R.drawable.amico);
        onBoardingData.setTitle("Fresh Meals");
        onBoardingData.setDetails("Discover fresh,healthy meals,\n delivered straight to your \n door.");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingData = new OnBoardingData();
        onBoardingData.setImg(R.drawable.pana);
        onBoardingData.setTitle("Quick Delivery");
        onBoardingData.setDetails("Get your meals delivered quickly \n and conveniently");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingData = new OnBoardingData();
        onBoardingData.setImg(R.drawable.pana1);
        onBoardingData.setTitle("Start Today!");
        onBoardingData.setDetails("Start your culinary journey \n with us today!");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingAdapter = new OnBoardingAdapter(this, onBoardingDataArrayList);
        viewPager.setAdapter(onBoardingAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changedIndicator(position);
            }
        });

        skipBtn.setOnClickListener(view -> {
            startActivity(new Intent(OnBoardingScreen.this, HomeActivity.class));
        });

        btn.setOnClickListener(view -> {
            if (viewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {


                SharedPreferences sharedPreferences = getSharedPreferences("OnBoarding", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isOnBoardingComplete", true);
                editor.apply();

                startActivity(new Intent(OnBoardingScreen.this, HomeActivity.class));
            }
        });
    }

    private void changedIndicator(int position) {
        switch (position) {
            case 0:
                c1.setCardBackgroundColor(getResources().getColor(R.color.Orange));
                c2.setCardBackgroundColor(getResources().getColor(R.color.white));
                c3.setCardBackgroundColor(getResources().getColor(R.color.white));
                break;
            case 1:
                c1.setCardBackgroundColor(getResources().getColor(R.color.white));
                c2.setCardBackgroundColor(getResources().getColor(R.color.Orange));
                c3.setCardBackgroundColor(getResources().getColor(R.color.white));
                break;
            case 2:
                c1.setCardBackgroundColor(getResources().getColor(R.color.white));
                c2.setCardBackgroundColor(getResources().getColor(R.color.white));
                c3.setCardBackgroundColor(getResources().getColor(R.color.Orange));
                break;
            default:


        }

        if (position == 2) {
            btn.setText("Start");
        } else {
            btn.setText("Next");
        }
    }
}