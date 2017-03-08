package com.example.administrator.weixin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setOverflowShowingAlways();//设置actionbar显示
        getActionBar().setDisplayShowHomeEnabled(false);//actionbar标题图表

    }

    //************************************************************//
    // 1.显示最右侧的菜单
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);

        //搜索按钮展开后的设置
        MenuItem searchItem2 = menu.findItem(R.id.action_search2);
        final SearchView searchView2 = (SearchView) searchItem2.getActionView();
        searchView2.setFocusable(true);
        searchView2.setFocusableInTouchMode(true);
        searchView2.requestFocus();
        searchView2.requestFocusFromTouch();
        searchItem2.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                searchView2.setQueryHint("搜索");
                return true;
            }
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
        return true;
    }

    //2.最右侧菜单的点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search2:
                Toast.makeText(this, "你点击了“”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_luyin:
                Toast.makeText(this, "你点击了“发布”按键！", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //3. 使最右侧的菜单弹出后可以显示图片
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null){
            if (menu.getClass().getSimpleName().equals("MenuBuilder")){
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e)
                {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
    //************************************************************//

    //设置actionbar在不同手机上，都能显示
    private void setOverflowShowingAlways(){
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
