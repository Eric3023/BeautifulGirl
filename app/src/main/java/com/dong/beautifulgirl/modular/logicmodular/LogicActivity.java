package com.dong.beautifulgirl.modular.logicmodular;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.TextView;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.circleimageview.widget.CircleImageView;

public class LogicActivity extends BaseActivity {

    private CircleImageView circleImageView;
    private final String IMG_URL = "http://a.hiphotos.baidu.com/image/pic/item/902397dda144ad34e98003fedca20cf431ad8588.jpg";
    private TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
            Transition explode= TransitionInflater.from(this).inflateTransition(R.transition.explode);
            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(explode);

            getWindow().setExitTransition(explode);
            getWindow().setReenterTransition(slide);
        }

        initView();
    }

    private void initView() {
        circleImageView = findViewById(R.id.logic_photo_circleImageView);
        circleImageView
                .setResourceID(R.drawable.home_pager_default)
                .setPath(IMG_URL)//圆形图片源为网络图片
                .setEdge(true)//设置是否显示边缘圆环
                .setEdgeColor(Color.WHITE)//设置边缘颜色
                .setEdgeWidth((int) getResources().getDimension(R.dimen.x3));//设置边缘宽度

        nameTextView = findViewById(R.id.logic_name_tv);
        nameTextView.setText("Eric");
    }
}
