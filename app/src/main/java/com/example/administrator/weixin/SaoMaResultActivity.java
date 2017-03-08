package com.example.administrator.weixin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SaoMaResultActivity extends Activity {

    @InjectView(R.id.result)
    TextView result;
    @InjectView(R.id.qrcode_bitmap)
    ImageView qrcodeBitmap;

    private final static int SCANNIN_GREQUEST_CODE = 1;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.view_temp)
    View viewTemp;
    @InjectView(R.id.title)
    RelativeLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_ma_result);
        ButterKnife.inject(this);

        // 接受传入的intent对象
        Intent intent = getIntent();
        if (intent != null) {
            // 获取Bundle对象中的参数
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
//                String accountName = bundle.getString("result");
//                Parcelable accountAge = bundle.getParcelable("bitmap");
                result.setText(bundle.getString("result"));
                qrcodeBitmap.setImageBitmap((Bitmap) intent.getParcelableExtra("bitmap"));
            }
        }

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case SCANNIN_GREQUEST_CODE:
//                if(resultCode == RESULT_OK){
//                    Bundle bundle = data.getExtras();
//                    //显示扫描到的内容
//                    result.setText(bundle.getString("result"));
//                    //显示
//                    qrcodeBitmap.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
//                }
//                break;
//        }
//    }

    @OnClick({R.id.result, R.id.qrcode_bitmap, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.result:
                break;
            case R.id.qrcode_bitmap:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
