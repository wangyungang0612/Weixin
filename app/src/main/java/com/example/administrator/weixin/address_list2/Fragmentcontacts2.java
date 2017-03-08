package com.example.administrator.weixin.address_list2;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.weixin.R;
import com.example.administrator.weixin.address_list.Fragmentcontacts;
import com.example.administrator.weixin.interfaces.UpdateIndexUIListener;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("SdCardPath")
public class Fragmentcontacts2 extends Fragment implements UpdateIndexUIListener
        ,SideBar.OnTouchTextChangeListener {
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS=0x11;
    private static final String PHONE_BOOK_LABLE="phonebook_label";
    /**需要查询的字段**/
    private static final String[]PHONES_PROJECTION={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            , ContactsContract.CommonDataKinds.Phone.NUMBER,PHONE_BOOK_LABLE};
    /**联系人显示名称**/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    /**电话号码**/
    private static final int PHONES_NUMBER_INDEX = 1;


    private ListView listView;
    private SideBar sideBar;
    private TextView tv_toast;
    private TextView tv_index;
    private ContactsListAdapter mAdapter;
    private List<ContactsModel> contactsModelList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_contactlist, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                getActivity().checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            getActivity().requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS);
            //等待回调 onRequestPermissionsResult(int, String[], int[]) method

        }else{
            //没有获得授权，做相应的处理！
            getData();
        }


    }


    private void initViews() {
        listView= (ListView) getActivity().findViewById(R.id.listview);
        if(Build.VERSION.SDK_INT>9){
            listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
        tv_index= (TextView) getActivity().findViewById(R.id.index);
        sideBar= (SideBar) getActivity().findViewById(R.id.sideBar);
        sideBar.setToastTextView((TextView) getActivity().findViewById(R.id.tv_toast));
        sideBar.setOnTouchTextChangeListener(this);
    }

    private void getData() {

        new Thread(){
            @Override
            public void run() {
                try{
                    ContentResolver mResolver = getActivity().getContentResolver();
                    //查询联系人数据，query的参数Phone.SORT_KEY_PRIMARY表示将结果集按Phone.SORT_KEY_PRIMARY排序
                    Cursor cursor=mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            ,PHONES_PROJECTION,null,null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
                    if(cursor!=null){
                        while (cursor.moveToNext()){
                            ContactsModel model=new ContactsModel();
                            model.setPhone(cursor.getString(PHONES_NUMBER_INDEX));
                            if(TextUtils.isEmpty(model.getPhone())){
                                continue;
                            }
                            model.setName(cursor.getString(PHONES_DISPLAY_NAME_INDEX));
                            model.setPhonebook_label(cursor.getString(cursor.getColumnIndex(PHONE_BOOK_LABLE)));
                            contactsModelList.add(model);
                        }
                        cursor.close();
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter=new ContactsListAdapter(getActivity()
                                    ,contactsModelList);
                            mAdapter.setUpdateIndexUIListener(Fragmentcontacts2.this);
                            listView.setAdapter(mAdapter);
                            listView.setOnScrollListener(mAdapter);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.start();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 获得授权后处理方法
            getData();
        }
    }
    /**
     *更新tv_index的位置实现移动效果
     * */
    @Override
    public void onUpdatePosition(int position) {
        ViewGroup.MarginLayoutParams mp= (ViewGroup.MarginLayoutParams) tv_index.getLayoutParams();
        mp.topMargin=position;
        tv_index.setLayoutParams(mp);
    }
    /**
     *更新tv_index显示label
     * */
    @Override
    public void onUpdateText(String mText) {
        tv_index.setText(mText);
    }

    @Override
    public void onTouchTextChanged(String s) {
        int position=getPositionForSection(s);
        listView.setSelection(position);
    }
    /**
     *根据传入的section来找到第一个出现的位置
     * */
    private int getPositionForSection(String s){
        for(int i=0;i<contactsModelList.size();i++){
            if(s.equals(contactsModelList.get(i).getPhonebook_label())){
                return i;
            }else if(s.equals("↑")||s.equals("☆")){
                return 0;
            }
        }
        return -1;
    }




}

