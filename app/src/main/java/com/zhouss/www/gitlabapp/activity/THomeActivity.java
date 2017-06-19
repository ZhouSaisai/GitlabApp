package com.zhouss.www.gitlabapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.fragment.StudentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2017/6/14.
 */

public class THomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{
    private BottomNavigationBar mBottomNavigationBar;
    private StudentFragment studentFragment;


    private FragmentManager fm;

    List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.t_home);

        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_one).setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.tab_two).setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.tab_three).setActiveColorResource(R.color.red))
                .setFirstSelectedPosition(0)//设置默认选择item
                .initialise();//初始化
        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = fm.beginTransaction();
        /**
         *每次添加之前隐藏所有正在显示的Fragment
         *然后如果是第一次添加的话使用transaction.add();
         *但第二次显示的时候,使用transaction.show();
         *这样子我们就可以保存Fragment的状态了
         */
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (studentFragment == null) {
                    studentFragment = new StudentFragment();
                    transaction.add(R.id.content, studentFragment);
                    list.add(studentFragment);
                } else {
                    transaction.show(studentFragment);
                }
                break;
//            case 1:
//                if (musicFragment == null) {
//                    musicFragment = new MusicFragment();
//                    transaction.add(R.id.layFrame, musicFragment);
//                    list.add(musicFragment);
//                } else {
//                    transaction.show(musicFragment);
//                }
//                break;
//            case 2:
//                if (tvFragment == null) {
//                    tvFragment = new TvFragment();
//                    transaction.add(R.id.layFrame, tvFragment);
//                    list.add(tvFragment);
//                } else {
//                    transaction.show(tvFragment);
//                }
//                break;
//            case 3:
//                if (gameFragment == null) {
//                    gameFragment = new GameFragment();
//                    transaction.add(R.id.layFrame, gameFragment);
//                    list.add(gameFragment);
//                } else {
//                    transaction.show(gameFragment);
//                }
//                break;
        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    /**
     * @param transaction
     */
    public void hideFragment(FragmentTransaction transaction) {
        for (Fragment fragment : list) {
            if(fragment != null)
                transaction.hide(fragment);
        }
    }

    // 设置默认进来是tab 显示的页面
    private void setDefaultFragment(){
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (studentFragment == null) {
            studentFragment = new StudentFragment();
            transaction.add(R.id.content, studentFragment);
            list.add(studentFragment);
        } else {
            transaction.show(studentFragment);
        }
        transaction.commit();
    }

}
