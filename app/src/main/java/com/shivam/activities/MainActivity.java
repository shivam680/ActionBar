package com.shivam.activities;

import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.shivam.R;
import com.shivam.fragments.FirstTabFragment;


public class MainActivity extends ActionBarActivity implements  ActionBar.TabListener{

    private  ActionBar mActionBar = null;
    private TextView mTitle = null;
    private Fragment mFragement = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (Build.VERSION_CODES.HONEYCOMB > Build.VERSION.SDK_INT) {

            setContentView(R.layout.activity_main);
        }
        else {

            setLayout();
        }

        initUi();

    }


    private void setLayout() {

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    private void initUi() {

        mTitle = ( TextView ) findViewById(R.id.txt_title);

        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for(int i=0 ; i<3 ;i++) {

            ActionBar.Tab tab = mActionBar.newTab().setText("Tab "+(i+1)).setTabListener(this);
            mActionBar.addTab(tab);
        }

        mActionBar.setSelectedNavigationItem(0);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        /*if( title != null) {
            if (tab.getPosition() == 0) {

                    title.setText("First Tab");

            } else if (tab.getPosition() == 1) {

                    title.setText("Second Tab");

            } else {

                    title.setText("Third Tab");

            }
        }*/

        if(tab.getPosition()  == 0 ) {

            if (mFragement != null) {
                mFragement = Fragment.instantiate(MainActivity.this, FirstTabFragment.class.getName(), new Bundle());
                fragmentTransaction.add(mFragement, "first");
            }
            else {
                fragmentTransaction.attach(mFragement);
            }
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem menuItem )  {

        switch (menuItem.getItemId()) {
            case R.id.action_message:
                Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_SHORT).show();
            return  true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

}