package com.dong.beautifulgirl.modular.logicandsignmodular.signmodular;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular.LogicActivity;
import com.dong.beautifulgirl.util.ToastUtil;

public class SignActivity extends BaseActivity implements View.OnClickListener, SignContract.View {

    private SignContract.Presenter present;
    private EditText accoutEditView;
    private EditText passwordEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);

            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);

            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(slide);
        }

        initView();

        initPresent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        present.viewDestroyed();
    }

    private void initPresent() {
        present = new SignPresent();
        present.viewCreated(this);
    }

    private void initView() {

        accoutEditView = findViewById(R.id.sign_account);
        passwordEditView = findViewById(R.id.sign_password);

        Button commit = findViewById(R.id.sign_commit);

        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.sign_commit:
                String acount = accoutEditView.getText().toString();
                String password = passwordEditView.getText().toString();
                if(present!=null&& !TextUtils.isEmpty(acount)&& !TextUtils.isEmpty(password)){
                    present.loadSignData(this, acount, password);
                }else{
                    ToastUtil.toastLong(this, "账号密码不能为空");
                }
                break;
        }
    }

    @Override
    public void setPresenter(SignContract.Presenter presenter) {
        this.present = presenter;
    }

    @Override
    public void signDataChanged(SignBean signBean) {
        if(signBean.getMesseage()){
            startComponent(LogicActivity.class);
            finish();
        }else{
            ToastUtil.toastLong(this, "注册失败");
        }
    }
}
