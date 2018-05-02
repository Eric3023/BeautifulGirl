package com.dong.beautifulgirl.modular.scanmodular;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dong.beautifulgirl.R;
import com.dong.beautifulgirl.base.BaseActivity;
import com.dong.beautifulgirl.modular.mainmodular.mainmodular.MainActivity;
import com.dong.beautifulgirl.util.ToastUtil;
import com.google.zxing.Result;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.URIParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerOptions;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.result.AddressBookResult;

public class ScanActivity extends BaseActivity implements View.OnClickListener, OnScannerCompletionListener {

    private ScannerView scannerView;
    private boolean isLightOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        initSwichView();
        initDBV();
    }

    private void initSwichView() {
        Button swichLight = findViewById(R.id.btn_switch);
        swichLight.setOnClickListener(this);
        if(!hasFlash()) {
            swichLight.setVisibility(View.GONE);
        }
    }

    private void initDBV() {
        scannerView = findViewById(R.id.scanner_view);
        ScannerOptions scannerOptions = new ScannerOptions.Builder()
                .setLaserStyle(ScannerOptions.LaserStyle.COLOR_LINE, getResources().getColor(R.color.colorblue))
//                .setLaserStyle(ScannerOptions.LaserStyle.RES_LINE, R.drawable.guide_btn)
                .setFrameCornerColor(getResources().getColor(R.color.colorblue))
                .setLaserMoveSpeed(3)
                .setLaserLineHeight((int) getResources().getDimension(R.dimen.x1))
                .setFrameCornerWidth((int) getResources().getDimension(R.dimen.x1))
                .build();
        scannerView.setScannerOptions(scannerOptions);
        scannerView.setOnScannerCompletionListener(this);
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    protected void onPause() {
        scannerView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        scannerView.onResume();
        super.onResume();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }

    @Override
    public void onClick(View v) {
        if(isLightOn){
            scannerView.toggleLight(false);
            isLightOn = false;
        }else{
            scannerView.toggleLight(true);
            isLightOn =true;
        }
    }

    /**
     * 扫描成功后将调用
     * @param rawResult    扫描结果
     * @param parsedResult 结果类型
     * @param barcode      扫描后的图像
     */
    @Override
    public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        Bundle bundle = new Bundle();
        ParsedResultType type = parsedResult.getType();
        switch (type) {
            case ADDRESSBOOK:
                AddressBookParsedResult addressBook = (AddressBookParsedResult) parsedResult;
                bundle.putSerializable(Intents.Scan.RESULT, new AddressBookResult(addressBook));
                break;
            case URI:
                URIParsedResult uriParsedResult = (URIParsedResult) parsedResult;
                bundle.putString(Intents.Scan.RESULT, uriParsedResult.getURI());
                break;
            case TEXT:
                bundle.putString(Intents.Scan.RESULT, rawResult.getText());
                break;
        }

        Intent intent = new Intent();
        intent.putExtra("RESULT", rawResult.getText());
        setResult(MainActivity.REQUEST_CODE_SCANNER, intent);
        finish();
    }
}
