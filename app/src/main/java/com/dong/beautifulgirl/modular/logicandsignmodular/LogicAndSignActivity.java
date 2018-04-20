package com.dong.beautifulgirl.modular.logicandsignmodular;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.transition.PathMotion;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionPropagation;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.guidemodular.GuideActivity;
import com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular.LogicActivity;
import com.dong.beautifulgirl.modular.mainmodular.MainActivity;
import com.dong.beautifulgirl.modular.mainmodular.minemodular.MineFragment;
import com.dong.circleimageview.widget.CircleImageView;
import com.squareup.picasso.Picasso;

public class LogicAndSignActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backgroundImg;
    private LinearLayout logicBtn;
    private LinearLayout signBtn;
    private TextView logicTextView;
    private TextView signTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logicandsign);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);

            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);

            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(slide);
        }

        initView();
    }

    private void initView() {
        backgroundImg = findViewById(R.id.logicandsign_backgroud);

        logicBtn = findViewById(R.id.logicandsign_logic_btn);
        signBtn = findViewById(R.id.logicandsign_sign_btn);
        logicBtn.setOnClickListener(this);
        signBtn.setOnClickListener(this);

        logicTextView = findViewById(R.id.logicandsign_logic_tv);
        signTextView = findViewById(R.id.logicandsign_sign_tv);
        logicTextView.setOnClickListener(this);
        signTextView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.logicandsign_logic_btn:
            case R.id.logicandsign_logic_tv:
                Intent logicIntent = new Intent(mContext, LogicActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<ImageView, String> backPair = Pair.create(backgroundImg, "share logic background");
                    Pair<TextView, String> logicPair = Pair.create(logicTextView, "share logic txt");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, new Pair[]{backPair, logicPair});
                    startActivity(logicIntent, activityOptions.toBundle());
                } else {
                    startActivity(logicIntent);
                }
                break;
            case R.id.logicandsign_sign_btn:
            case R.id.logicandsign_sign_tv:
                Intent signIntent = new Intent(mContext, LogicActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<ImageView, String> backPair = Pair.create(backgroundImg, "share logic background");
                    Pair<TextView, String> logicPair = Pair.create(logicTextView, "share logic txt");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, new Pair[]{backPair, logicPair});
                    startActivity(signIntent, activityOptions.toBundle());
                } else {
                    startActivity(signIntent);
                }
                break;
        }
    }
}
