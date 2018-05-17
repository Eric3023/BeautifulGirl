package com.dong.beautifulgirl.modular.scanmodular;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.mylhyl.zxing.scanner.decode.QRDecode;
import com.mylhyl.zxing.scanner.result.AddressBookResult;

public class ScanActivity extends BaseActivity implements View.OnClickListener, OnScannerCompletionListener {

    private ScannerView scannerView;
    private boolean isLightOn;
    private final int CHOOSE_PIC = 10;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        initSwichView();
        initDBV();
    }

    private void initSwichView() {
        Button swichLight = findViewById(R.id.btn_switch);
        TextView photoTextView = findViewById(R.id.scan_photo_text);
        swichLight.setOnClickListener(this);
        photoTextView.setOnClickListener(this);
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
        int id = v.getId();
        switch (id){
            case R.id.btn_switch:
                if(isLightOn){
                    scannerView.toggleLight(false);
                    isLightOn = false;
                }else{
                    scannerView.toggleLight(true);
                    isLightOn =true;
                }
                break;

            case R.id.scan_photo_text:
                parseQRCodeFromPick();
                break;
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

    //通过图库获取扫描二维码
    public void parseQRCodeFromPick(){
        Intent intent1 = new Intent();
        if(android.os.Build.VERSION.SDK_INT < 19){
            intent1.setAction(Intent.ACTION_GET_CONTENT);
        }else{
            intent1.setAction(Intent.ACTION_OPEN_DOCUMENT);
        }
        intent1.setAction(Intent.ACTION_PICK);

        intent1.setType("image/*");

        Intent intent2 =  Intent.createChooser(intent1, "选择二维码图片");
        startActivityForResult(intent2, CHOOSE_PIC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imgPath = null;
        if(resultCode == RESULT_OK && requestCode == CHOOSE_PIC){
            String[] proj = new String[]{MediaStore.Images.Media.DATA};
            Cursor cursor = this.getContentResolver().query(data.getData(), proj, null, null, null);

            if(cursor.moveToFirst()){
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                //获取到用户选择的二维码图片的绝对路径
                imgPath = cursor.getString(columnIndex);
            }
            cursor.close();

            //获取解析结果
//            QRDecode.decodeQR(imgPath, this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
