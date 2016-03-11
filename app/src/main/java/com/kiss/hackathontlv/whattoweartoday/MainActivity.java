package com.kiss.hackathontlv.whattoweartoday;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;
import com.kiss.hackathontlv.whattoweartoday.Data.FiveDaysForcast;
import com.kiss.hackathontlv.whattoweartoday.adapters.ViewPagerAdapter;
import com.kiss.hackathontlv.whattoweartoday.fragments.ForcastFragment;
import com.kiss.hackathontlv.whattoweartoday.fragments.MainFragment;
import com.kiss.hackathontlv.whattoweartoday.fragments.SettingsFragment;
import com.kiss.hackathontlv.whattoweartoday.internet.WeatherFromInternet;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, WeatherFromInternet.onWeatherOkListener {
    private static final String SETTINGS_TRANSACTION = "SETTINGS_TRANSACTION";
    FragmentManager fragmentManager;
    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    SettingsFragment settingsFragment;
    SettingsFragment settingFragment;
    MainFragment mainFragment;
    ForcastFragment forcastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forcastFragment = new ForcastFragment();

        settingFragment = new SettingsFragment();
        mainFragment = new MainFragment();
        handleToolbar();
        handleTabs();
        handleNavigationDrawer();
        fragmentManager = getSupportFragmentManager();

        //openMainFragment();


    }

    @Override
    protected void onResume() {
        super.onResume();
        CityDetails cityDetails = CityDetails.retrieveFromPrfences(this);
        if (cityDetails != null) {
            WeatherFromInternet.getWeatherFromInternet(this, cityDetails);
        }
    }

    private void handleTabs() {
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "Today");
        adapter.addFragment(forcastFragment, "Forecast");
        viewPager.setAdapter(adapter);
    }

    /*private void openMainFragment() {
        fragmentManager.beginTransaction().add(R.id.container, mainFragment).commit();
        toolbar.setNavigationIcon(mainFragment.getNavigationIcon());
    }*/

    public void isToolbarNavigtionOpenDrawer(Boolean isDrawer) {
        if (isDrawer) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(Gravity.LEFT);

                }
            });
        } else {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();

                }
            });

        }
    }

    private void handleNavigationDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void handleToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            openSettingActivity();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openSettingActivity() {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    public void setToolbarNavigationIcon(int icon) {
        toolbar.setNavigationIcon(icon);

    }
    @Override
    public void onWeatherOK(String weatherJSONAsString) {
        // Toast.makeText(getContext(), weatherJSONAsString, Toast.LENGTH_SHORT).show();
        try {
            JSONObject JSONWhole = new JSONObject(weatherJSONAsString);
            FiveDaysForcast.getInstance(JSONWhole);
            forcastFragment.notifyDataSetChanged();



        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onWeatherError(String Error) {


    }




}
