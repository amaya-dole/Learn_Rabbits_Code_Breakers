package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class animalsPg extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private List<image> imageList;
    private imageAdaptor adaptor;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_animals_pg);

        viewPager2 = findViewById(R.id.viewPager2);
        imageList = new ArrayList<>();
        Intent intent1 = getIntent();

        imageList.add(new image(R.drawable.cat));
        imageList.add(new image(R.drawable.dog));
        imageList.add(new image(R.drawable.cow));
        imageList.add(new image(R.drawable.elephant));
        imageList.add(new image(R.drawable.giraffe));
        imageList.add(new image(R.drawable.horse));
        imageList.add(new image(R.drawable.lion));
        imageList.add(new image(R.drawable.monkey));
        imageList.add(new image(R.drawable.mouse));
        imageList.add(new image(R.drawable.pig));
        imageList.add(new image(R.drawable.rabbit));
        imageList.add(new image(R.drawable.tiger));
        imageList.add(new image(R.drawable.zebra));

        adaptor = new imageAdaptor(imageList , viewPager2);
        viewPager2.setAdapter(adaptor);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.14f);
            }
        });

        viewPager2.setPageTransformer(transformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable , 2000);
            }
        });


    }
    public void openHome(View view) {
        Intent intentHome1 = new Intent(this, ImageUI.class);

        startActivity(intentHome1);
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }
}
