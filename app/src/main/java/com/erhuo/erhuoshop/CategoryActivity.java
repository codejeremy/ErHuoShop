package com.erhuo.erhuoshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String GOODS_NAME = "category_name";
    public static final String GOODS_IMAGE_ID = "category_image_id";
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorynew);
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra(GOODS_NAME);
        int categoryImageId = intent.getIntExtra(GOODS_IMAGE_ID, 0);
        Toolbar toolbar = findViewById(R.id.toorball);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        ImageView categoryImageView = findViewById(R.id.category_image_view);
        TextView categoryContentText = findViewById(R.id.category_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(categoryName);
        Glide.with(this).load(categoryImageId).into(categoryImageView);
        String categoryContent = generateFruitContent(categoryName);
        categoryContentText.setText(categoryContent);
        button = findViewById(R.id.floatingActionButton3);
        button.setOnClickListener(this);
    }

    private String generateFruitContent(String categoryName) {
        StringBuilder categoryContent = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            categoryContent.append(categoryName);
        }
        return categoryContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingActionButton3:
                Toast.makeText(CategoryActivity.this,"添加购车",Toast.LENGTH_SHORT).show();
        }
    }
}
