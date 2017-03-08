package com.example.administrator.weixin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class AddFriendsTwoActivity extends Activity {
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.view_temp)
    View viewTemp;
    @InjectView(R.id.title)
    RelativeLayout title;
    @InjectView(R.id.iv_find)
    ImageView ivFind;
    @InjectView(R.id.tv_temp)
    TextView tvTemp;
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.re_search)
    RelativeLayout reSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriends_two);
        ButterKnife.inject(this);

        etSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.length() > 0) {
                    reSearch.setVisibility(View.VISIBLE);
                    tvSearch.setText(etSearch.getText().toString().trim());
                } else {
                    reSearch.setVisibility(View.GONE);
                    tvSearch.setText("");
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {

            }
        });
        reSearch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String uid = etSearch.getText().toString().trim();
                if (uid == null || uid.equals("")) {
                    return;
                }
              //  searchUser(uid);

            }

        });

    }

//    private void searchUser(String uid) {
//        final ProgressDialog dialog = new ProgressDialog(
//                AddFriendsTwoActivity.this);
//        dialog.setMessage("正在查找联系人...");
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.show();
//        Map<String, String> map = new HashMap<String, String>();
//
//        map.put("uid", uid);
//
//        LoadDataFromServer task = new LoadDataFromServer(
//                AddFriendsTwoActivity.this, Constant.URL_Search_User, map);
//
//        task.getData(new DataCallBack() {
//
//            @Override
//            public void onDataCallBack(JSONObject data) {
//                try {
//                    dialog.dismiss();
//                    int code = data.getInteger("code");
//                    if (code == 1) {
//
//                        JSONObject json = data.getJSONObject("user");
//                        String nick = json.getString("nick");
//                        String avatar = json.getString("avatar");
//                        String sex = json.getString("sex");
//
//                        String hxid = json.getString("hxid");
//
//                        Intent intent = new Intent();
//                        intent.putExtra("hxid", hxid);
//                        intent.putExtra("avatar", avatar);
//                        intent.putExtra("nick", nick);
//
//                        intent.putExtra("sex", sex);
//                        intent.setClass(AddFriendsTwoActivity.this,
//                                UserInfoActivity.class);
//                        startActivity(intent);
//                    } else if (code == 2) {
//
//                        Toast.makeText(AddFriendsTwoActivity.this, "用户不存在",
//                                Toast.LENGTH_SHORT).show();
//                    } else if (code == 3) {
//
//                        Toast.makeText(AddFriendsTwoActivity.this,
//                                "服务器查询错误...", Toast.LENGTH_SHORT).show();
//                    } else {
//
//                        Toast.makeText(AddFriendsTwoActivity.this,
//                                "服务器繁忙请重试...", Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    dialog.dismiss();
//                    Toast.makeText(AddFriendsTwoActivity.this, "数据解析错误...",
//                            Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
//            }
//        });
//    }


    @OnClick({R.id.iv_back, R.id.et_search, R.id.view_temp, R.id.title, R.id.iv_find, R.id.tv_temp, R.id.tv_search, R.id.re_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_search:
                break;
            case R.id.view_temp:
                break;
            case R.id.title:
                break;
            case R.id.iv_find:
                break;
            case R.id.tv_temp:
                break;
            case R.id.tv_search:
                break;
            case R.id.re_search:
                break;
        }
    }
}