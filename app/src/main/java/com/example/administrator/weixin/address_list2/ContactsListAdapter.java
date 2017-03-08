package com.example.administrator.weixin.address_list2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.weixin.R;
import com.example.administrator.weixin.interfaces.UpdateIndexUIListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class ContactsListAdapter extends BaseAdapter implements AbsListView.OnScrollListener{

    private Context mContext;
    private List<ContactsModel> contactsModelList=new ArrayList<>();
    private UpdateIndexUIListener listener;
    private int mCurrentFirstPosition=0;
    private int lastFirstPosition=-1;
    public ContactsListAdapter(Context mContext, List<ContactsModel> contactsModelList) {
        this.mContext = mContext;
        this.contactsModelList=contactsModelList;
    }

    @Override
    public int getCount() {
        return contactsModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if(convertView==null){
            mViewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_contact_listview,null);
            mViewHolder.huatou= (LinearLayout) convertView.findViewById(R.id.huatou);
            mViewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.tv_number= (TextView) convertView.findViewById(R.id.tv_number);
            mViewHolder.tv_group_index= (TextView) convertView.findViewById(R.id.tv_group_index);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder= (ViewHolder) convertView.getTag();
        }
        mViewHolder.tv_name.setText(contactsModelList.get(position).getName());
        mViewHolder.tv_number.setText(contactsModelList.get(position).getPhone());
        if(position>0&&contactsModelList.get(position).getPhonebook_label().equals(contactsModelList.get(position-1).getPhonebook_label())){
            mViewHolder.tv_group_index.setText("qqqqq");
            mViewHolder.tv_group_index.setVisibility(View.GONE);
            mViewHolder.huatou.setVisibility(View.GONE);
        }
        if(position>0&&!contactsModelList.get(position).getPhonebook_label().equals(contactsModelList.get(position-1).getPhonebook_label())){
            mViewHolder.tv_group_index.setText(contactsModelList.get(position).getPhonebook_label());
            mViewHolder.tv_group_index.setBackgroundColor(Color.argb(255, 235, 235, 235));
            mViewHolder.tv_group_index.setVisibility(View.VISIBLE);

        }else if(position==0){
            mViewHolder.huatou.setVisibility(View.VISIBLE);
            mViewHolder.tv_group_index.setBackgroundColor(Color.argb(255, 235, 235, 235));
            mViewHolder.tv_group_index.setVisibility(View.VISIBLE);
            mViewHolder.tv_group_index.setText(contactsModelList.get(position).getPhonebook_label());
        }
        else {
            mViewHolder.tv_group_index.setText("");
        }
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mCurrentFirstPosition=firstVisibleItem;
//        if(listener!=null){
////            listener.onUpdateText(contactsModelList.get(mCurrentFirstPosition).getPhonebook_label());
//        }
//        if(firstVisibleItem!=lastFirstPosition){
//            if(listener!=null){
//                listener.onUpdatePosition(0);
//            }
//        }
//        if(lastFirstPosition!=-1&&!contactsModelList.get(firstVisibleItem).getPhonebook_label()
//                .equals(contactsModelList.get(firstVisibleItem+1).getPhonebook_label())){
//            View childView=view.getChildAt(0);
//            int bottom=childView.getBottom();
//            int height=childView.getHeight();
//            int distance=bottom-height;
//            if(distance<0){//如果新的section
//                listener.onUpdatePosition(distance);
//            }else {
//                listener.onUpdatePosition(0);
//            }
//        }
//        lastFirstPosition=firstVisibleItem;
    }
    public void setUpdateIndexUIListener(UpdateIndexUIListener listener){
        this.listener=listener;
    }
    private class ViewHolder{
        private TextView tv_name;
        private LinearLayout huatou;
        private TextView tv_number;
        private TextView tv_group_index;
    }
}
