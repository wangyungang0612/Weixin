package com.example.administrator.weixin;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


public class SaoMaPopWindow extends PopupWindow {
    private View conentView;


	@SuppressLint("InflateParams")
	public SaoMaPopWindow(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_saoma, null);
 
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        
        
//        RelativeLayout   re_addfriends =(RelativeLayout) conentView.findViewById(R.id.re_addfriends);
//        RelativeLayout   re_chatroom =(RelativeLayout) conentView.findViewById(R.id.re_chatroom);
//
//        //群聊
//        re_chatroom.setOnClickListener(new OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context,CreatChatRoomActivity.class));
//                SaoMaPopWindow.this.dismiss();
//
//            }
//
//        } );
//
//        //添加朋友
//        re_addfriends.setOnClickListener(new OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context,AddFriendsOneActivity.class));
//                SaoMaPopWindow.this.dismiss();
//
//            }
//
//        } );

    }
    /**
     * 显示popupWindow
     *  @param parent
     *
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);// 底部  其它位置可以根据自己需求设置
           // this.showAsDropDown(parent, 0, 1800);
        } else {
            this.dismiss();
        }
    }
}
