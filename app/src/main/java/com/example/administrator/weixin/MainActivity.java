package com.example.administrator.weixin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.administrator.weixin.address_list.Fragmentcontacts;
import com.example.administrator.weixin.address_list2.Fragmentcontacts2;
import com.example.administrator.weixin.faxian.FragmentFaxian;
import com.example.administrator.weixin.weixin.FragmentHome;
import com.example.administrator.weixin.wo.FragmentProfile;

import adapter.FragmentAdapter;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener {

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;

	private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();//最下边的tab标题
	private List<Fragment> fragments = new ArrayList<Fragment>();

	private ImageView iv_add;
	private ImageView iv_search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		setOverflowShowingAlways();//设置actionbar显示
		getActionBar().setDisplayShowHomeEnabled(false);//actionbar标题图表
		getActionBar().hide();//隐藏标题栏

		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		//初始化
		initDatas();
		mAdapter=new FragmentAdapter(getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);//功能：Fragment页面改变事件

		iv_add = (ImageView) this.findViewById(R.id.iv_add);
		iv_search = (ImageView) this.findViewById(R.id.iv_search);
		iv_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddPopWindow addPopWindow = new AddPopWindow(MainActivity.this);
				addPopWindow.showPopupWindow(iv_add);
			}

		});
		iv_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});


	}
	//*********初始化**********************//
	private void initDatas() {
		//1.初始化最底部标签栏
		ChangeColorIconWithTextView one = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_one);
		ChangeColorIconWithTextView two = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_two);
		ChangeColorIconWithTextView three = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_three);
		ChangeColorIconWithTextView four = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_four);
		mTabIndicator.add(one);
		mTabIndicator.add(two);
		mTabIndicator.add(three);
		mTabIndicator.add(four);
		//2.底部标签栏的点击响应
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		one.setIconAlpha(1.0f);
        //3.为viewPaper设置内容
		fragments.add(new FragmentHome());
		fragments.add(new Fragmentcontacts2());
		fragments.add(new FragmentFaxian());
		fragments.add(new FragmentProfile());

	}

	//***********功能：Fragment 滑动页面改变事件*******//
	//1.当新的页面被选中时调用
	@Override
	public void onPageSelected(int arg0) {
	}
	//2.当前页面被滑动时调用
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if (positionOffset > 0) {
			ChangeColorIconWithTextView left = mTabIndicator.get(position);
			ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);
			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}
	}
	//3.当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int state) {
	}

	//*************************************************//


	//************************************************************//
	 //1.重置其他的Tab
	private void resetOtherTabs() {
		for (int i = 0; i < mTabIndicator.size(); i++) {
			mTabIndicator.get(i).setIconAlpha(0);
		}
	}
	//2.滑动到另外一个页面
	@Override
	public void onClick(View v) {
		resetOtherTabs();
		switch (v.getId()) {
			case R.id.id_indicator_one:
				mTabIndicator.get(0).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(0, false);
				break;
			case R.id.id_indicator_two:
				mTabIndicator.get(1).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(1, false);
				break;
			case R.id.id_indicator_three:
				mTabIndicator.get(2).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(2, false);
				break;
			case R.id.id_indicator_four:
				mTabIndicator.get(3).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(3, false);
				break;
		}
	}
   //************************************************************//


	//************************************************************//
	// 1.显示最右侧的菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		//搜索按钮展开后的设置
		MenuItem searchItem = menu.findItem(R.id.action_search);
		final SearchView searchView = (SearchView) searchItem.getActionView();
		searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				searchView.setQueryHint("搜索");
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
			case R.id.action_search:
				Toast.makeText(this, "你点击了“”按键！", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
				return true;
			case R.id.action_group_chat:
				Toast.makeText(this, "你点击了“发布”按键！", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_add_friend:
				Toast.makeText(this, "你点击了“收藏”按键！", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_scan:
				Toast.makeText(this, "你点击了“收藏”按键！", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_fukuan:
				Toast.makeText(this, "你点击了“收藏”按键！", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_feed:
				Toast.makeText(this, "你点击了“收藏”按键！", Toast.LENGTH_SHORT).show();
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
