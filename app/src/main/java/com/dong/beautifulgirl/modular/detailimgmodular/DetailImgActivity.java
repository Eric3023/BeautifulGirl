package com.dong.beautifulgirl.modular.detailimgmodular;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dong.beautifulgirl.R;
import com.squareup.picasso.Picasso;

public class DetailImgActivity extends AppCompatActivity {

    private String url;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_img);

        initIntent();

        initView();
    }

    private void initIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("URL");
    }

    private void initView() {
        img = findViewById(R.id.detail_img);
        Glide.with(this).load(url).fitCenter().into(img);
    }

}
