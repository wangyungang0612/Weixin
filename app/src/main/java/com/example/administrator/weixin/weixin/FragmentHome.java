package com.example.administrator.weixin.weixin;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.weixin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.TodayDoorModelAdapter;

/**
 * Created by Administrator on 2017/1/10 0010.
 */
public class FragmentHome extends Fragment {

    private ListView listView;
    private HomeSMSAdapter adapter;
    private RexseeSMS rsms;
    private Button newSms;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS=0x11;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            getActivity().requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS);
            //等待回调 onRequestPermissionsResult(int, String[], int[]) method

        }else{
            //没有获得授权，做相应的处理！
            init();
        }

    }

    public void init() {
        listView = (ListView) getActivity().findViewById(R.id.list);
        adapter = new HomeSMSAdapter(getActivity());
        rsms = new RexseeSMS(getActivity());
        List<SMSBean> list_mmt = rsms.getThreadsNum(rsms.getThreads(0));
        Log.d("2222", list_mmt.toString());
        adapter.assignment(list_mmt);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Map<String, String> map = new HashMap<String, String>();
                SMSBean sb = adapter.getItem(position);
                map.put("phoneNumber", sb.getAddress());
                map.put("threadId", sb.getThread_id());
                BaseIntentUtil.intentSysDefault(getActivity(), MessageBoxList.class, map);
            }
        });

        newSms = (Button) getActivity().findViewById(R.id.newSms);
        newSms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BaseIntentUtil.intentSysDefault(getActivity(), NewSMSActivity.class, null);
            }
        });

    }

}
