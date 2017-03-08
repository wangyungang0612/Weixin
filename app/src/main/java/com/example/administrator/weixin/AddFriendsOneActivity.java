package com.example.administrator.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class AddFriendsOneActivity extends Activity {
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.view_temp)
    View viewTemp;
    @InjectView(R.id.title)
    RelativeLayout title;
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.iv_leida)
    ImageView ivLeida;
    @InjectView(R.id.iv_jianqun)
    ImageView ivJianqun;
    @InjectView(R.id.iv_lianxiren)
    ImageView ivLianxiren;
    @InjectView(R.id.iv_ggh)
    ImageView ivGgh;
    @InjectView(R.id.iv_saoyisao)
    ImageView ivSaoyisao;
    @InjectView(R.id.saoma)
    RelativeLayout saoma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriends);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.iv_back, R.id.view_temp, R.id.title, R.id.tv_search, R.id.iv_leida, R.id.iv_jianqun,R.id.saoma, R.id.iv_saoyisao, R.id.iv_lianxiren, R.id.iv_ggh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.view_temp:
                break;
            case R.id.title:
                break;
            case R.id.tv_search:
                startActivity(new Intent(AddFriendsOneActivity.this, AddFriendsTwoActivity.class));
                break;
            case R.id.iv_leida:
                break;
            case R.id.iv_jianqun:
                break;
            case R.id.saoma:
                startActivity(new Intent(AddFriendsOneActivity.this, SaoMaActivity.class));
                break;
            case R.id.iv_saoyisao:
                break;
            case R.id.iv_lianxiren:
                break;
            case R.id.iv_ggh:
                break;
        }
    }
}
