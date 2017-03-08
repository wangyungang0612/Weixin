package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.weixin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangyungang on 2016/6/15.
 */
public class TodayDoorModelAdapter extends BaseAdapter {
    Context context;
    List<String>  doorList =new ArrayList<String>() ;
    OnItemClickLitener mOnItemClickLitener;

    public TodayDoorModelAdapter(List<String> doorList, Context context) {

        this.doorList = doorList;
        this.context = context;
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getCount() {
        return doorList.size();
    }

    @Override
    public Object getItem(int position) {
        return doorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v;
        if(view!=null){
            v = view;
        }else {
            v = View.inflate(context, R.layout.todaydoor_item, null);
        }

        TextView tv2 = (TextView) v.findViewById(R.id.todayDoor_Text);
        tv2.setText( doorList.get(position));
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        return v;
    }


}
