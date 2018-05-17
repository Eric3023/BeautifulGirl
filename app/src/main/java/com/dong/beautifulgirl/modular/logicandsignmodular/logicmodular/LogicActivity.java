package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.mainmodular.mainmodular.MainActivity;
import com.dong.beautifulgirl.util.ToastUtil;

public class LogicActivity extends BaseActivity implements View.OnClickListener, LogicContract.View {

    private EditText accountEditView;
    private EditText passwordEditView;
    private Button commit;

    private LogicContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);

            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);

            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(slide);
        }

        initView();
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.viewDestroyed();
    }

    private void initPresenter() {
        presenter = new LogicPresent();
        presenter.viewCreated(this);
    }

    private void initView() {

        accountEditView = findViewById(R.id.logic_account);
        passwordEditView = findViewById(R.id.logic_password);

        commit = findViewById(R.id.logic_commit);
        commit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.logic_commit:
                String accout = accountEditView.getText().toString();
                String password = passwordEditView.getText().toString();

                if(presenter!=null &&!TextUtils.isEmpty(accout)&&!TextUtils.isEmpty(password)){
                    presenter.loadLogicData(this, accout, password);
                }else{
                    ToastUtil.toastLong(this, "账号密码不能为空");
                }
                break;
        }
    }

    @Override
    public void setPresenter(LogicContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void logicDataChanged(LogicBean logicBean) {
        boolean messeage = logicBean.getMesseage();
        if(messeage){
            startComponent(MainActivity.class);
        }else{
            ToastUtil.toastLong(this, "登陆失败");
        }
    }
}
