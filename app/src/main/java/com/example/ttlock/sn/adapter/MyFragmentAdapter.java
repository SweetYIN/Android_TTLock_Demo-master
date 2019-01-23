package com.example.ttlock.sn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ttlock.R;
import com.example.ttlock.sn.customView.BadgeView;

import java.util.List;

public class MyFragmentAdapter extends FragmentPagerAdapter {

	private static final String TAG = "MyFragmentAdapter";

	private Context mContext;

	private List<Fragment> fragments;

	private List<String> tabList;

	private List<Integer> mBadgeCountList;

	private int[] images = {R.mipmap.one,R.mipmap.two,R.mipmap.three};



	//写构造方法，方便赋值调用
	public MyFragmentAdapter(Context context,
							 FragmentManager fm,
							 List<Fragment> fragments,
							 List<String> tabList,
							 List<Integer> badgeCountList) {
		super(fm);
		this.mContext = context;
		this.fragments = fragments;
		this.tabList = tabList;
		this.mBadgeCountList = badgeCountList;

	}

	////根据Item的位置返回对应位置的Fragment，绑定item和Fragment
	@Override
	public Fragment getItem(int arg0) {

		return fragments.get(arg0);
	}

	@Override
	public CharSequence getPageTitle(int position) {
//		return tabList.get(position);
		return "";
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}


	public View getTabItemView(int position){
		View view = LayoutInflater.from(mContext).inflate(R.layout.activity_tab_item, null);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(tabList.get(position));
		ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
		imageView.setBackgroundResource(images[position]);
		View target = view.findViewById(R.id.badgeview_target);

		BadgeView badgeView = new BadgeView(mContext);
		badgeView.setTargetView(target);
		badgeView.setBadgeMargin(0, 6, 10, 0);
		badgeView.setTextSize(10);
		badgeView.setText(formatBadgeNumber(mBadgeCountList.get(position)));


		return view;
	}
	public static String formatBadgeNumber(int value) {
		if (value <= 0) {
			return null;
		}

		if (value < 100) {
			// equivalent to String#valueOf(int);
			return Integer.toString(value);
		}

		// my own policy
		return "99+";
	}

}
