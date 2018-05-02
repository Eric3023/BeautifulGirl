package com.dong.beautifulgirl.modular.passwordmodular;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular.LogicActivity;
import com.dong.beautifulgirl.util.ToastUtil;

public class PasswordActivity extends BaseActivity implements View.OnClickListener, PasswordContract.View {

    private EditText oldPasswordEdit;
    private EditText newPasswordEdit;
    private EditText newPasswordConfirmEdit;

    private PasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);

            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);

            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(slide);
        }

        initView();
        initPresenter();
    }

    private void initView() {
        oldPasswordEdit = findViewById(R.id.password_old_password);
        newPasswordEdit = findViewById(R.id.password_new_password);
        newPasswordConfirmEdit = findViewById(R.id.password_new_password_confirm);

        Button commitButton = findViewById(R.id.password_commit);
        commitButton.setOnClickListener(this);
    }

    private void initPresenter() {
        presenter = new PasswordPresent(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.password_commit:
                String oldPassword = oldPasswordEdit.getText().toString();
                String newPassword = newPasswordEdit.getText().toString();
                String newPasswordConfirm = newPasswordConfirmEdit.getText().toString();

                presenter.changePassword(this, oldPassword, newPassword, newPasswordConfirm);
                break;
        }
    }

    @Override
    public void setPresenter(PasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void passwordChanged(PasswordBean passwordBean) {
        boolean sucess = passwordBean.isSucess();
        if(sucess){
            startComponent(LogicActivity.class);
            finish();
        }else{
            String messeage = passwordBean.getMesseage();
            ToastUtil.toastLong(this, messeage);
        }
    }
}
