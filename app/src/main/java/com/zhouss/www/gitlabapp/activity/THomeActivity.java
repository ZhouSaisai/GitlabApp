package com.zhouss.www.gitlabapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhouss.www.gitlabapp.R;
import com.zhouss.www.gitlabapp.fragment.ClassFragment;
import com.zhouss.www.gitlabapp.fragment.MyFragment;
import com.zhouss.www.gitlabapp.fragment.TaskFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2017/6/14.
 */

public class THomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{
    private BottomNavigationBar mBottomNavigationBar;
    private ClassFragment classFragment;
    private TaskFragment taskFragment;
    private MyFragment myFragment;
    private FragmentManager fm;

    //组件区
    private TextView title_bar;
    private Button add_button;
    private Button back_button;

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

        title_bar = (TextView) findViewById(R.id.title_bar);
        add_button = (Button) findViewById(R.id.add_button);
        back_button = (Button) findViewById(R.id.back_button);
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
                if (classFragment == null) {
                    classFragment = new ClassFragment();
                    transaction.add(R.id.content, classFragment);
                    list.add(classFragment);
                } else {
                    transaction.show(classFragment);
                }
                title_bar.setText("我的班级");
                add_button.setVisibility(View.VISIBLE);
                back_button.setVisibility(View.INVISIBLE);
                break;
            case 1:
                if (taskFragment == null) {
                    taskFragment = new TaskFragment();
                    transaction.add(R.id.content, taskFragment);
                    list.add(taskFragment);
                } else {
                    transaction.show(taskFragment);
                }
                title_bar.setText("发布的任务");
                add_button.setVisibility(View.VISIBLE);
                back_button.setVisibility(View.INVISIBLE);
                break;
            case 2:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                    list.add(myFragment);
                } else {
                    transaction.show(myFragment);
                }
                title_bar.setText("个人空间");
                add_button.setVisibility(View.INVISIBLE);
                back_button.setVisibility(View.INVISIBLE);
                break;
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
        if (classFragment == null) {
            classFragment = new ClassFragment();
            transaction.add(R.id.content, classFragment);
            list.add(classFragment);
        } else {
            transaction.show(classFragment);
        }
        transaction.commit();
    }


    public List<Fragment> getList() {
        return list;
    }

}
