package fr3380ot.com.gro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBTools dbTools = new DBTools(this);
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddHabit.class);
                startActivity(intent);
                //TODO: Replace finish() with something appropriate.
                // This is probably incorrect and reloads the cache every time we recreate main.
                finish();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Pull habits from database and store in habitList
        ArrayList<HashMap<String, String>> habitList = dbTools.getAllHabits();
        //TODO: Did commenting this break anything?
//        dbTools.getAllHabits();

        //If there are habits fill the habit listView
        if(habitList.size() != 0) {
            ListView listView = (ListView) findViewById(android.R.id.list);

            CustomListAdapter adapter = new CustomListAdapter(
                    this, habitList, R.layout.habit_entry, new String[]{"habitId", "habitId", "title", "difficulty", "frequency"}, new int[]{
                    R.id.habitId1, R.id.habitId2, R.id.habitTitle, R.id.habitDifficulty, R.id.habitFrequency});

            listView.setAdapter(adapter);
        }

        HashMap<String, String> user = dbTools.getUserInfo();
        int progress1 = Integer.parseInt(user.get("waterLevel"));
        int progress2 = Integer.parseInt(user.get("oxygenLevel"));
        progressBar1.setProgress(progress1);
        progressBar2.setProgress(progress2);
    }

    public void habitCheckIn(String habitId) {

        HashMap<String, String> user = dbTools.getUserInfo();
        HashMap<String, String> habit = dbTools.getHabitInfo(habitId);
        String diff = habit.get("difficulty");
        int waterInt = 0;
        String water = "";
        Log.d("habitCheckIn", habitId);


        switch (diff) {
            case "Easy":
                waterInt = Integer.parseInt(user.get("waterLevel"));
                if (waterInt + 1 > 100){
                    waterInt = 100;
                    water = waterInt + "";
                }else{
                    water = waterInt + 1 + "";
                }
                progressBar1.setProgress(progressBar1.getProgress() + 1);
                Log.d("Easy", water);
                break;
            case "Medium":
                waterInt = Integer.parseInt(user.get("waterLevel"));
                if (waterInt + 4 > 100){
                    waterInt = 100;
                    water = waterInt + "";
                }else{
                    water = waterInt + 4 + "";
                }
                progressBar1.setProgress(progressBar1.getProgress() + 4);
                Log.d("Medium", water);
                break;
            case "Hard":
                waterInt = Integer.parseInt(user.get("waterLevel"));
                if (waterInt + 10 > 100){
                    waterInt = 100;
                    water = waterInt + "";
                }else{
                    water = waterInt + 10 + "";
                }
                progressBar1.setProgress(progressBar1.getProgress() + 10);
                Log.d("Hard", water);
                break;
            default:
                Log.d("Habit Check In", "Broken");
        }
        user.put("waterLevel", water);
        if (progressBar1.getProgress() > 100){
            progressBar1.setProgress(100);
        }
        dbTools.updateUser(user);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void onResume(){
        HashMap<String, String> user = dbTools.getUserInfo();
        int progress1 = Integer.parseInt(user.get("waterLevel"));
        int progress2 = Integer.parseInt(user.get("oxygenLevel"));
        progressBar1.setProgress(progress1);
        progressBar2.setProgress(progress2);
        Log.d("Resume", "Updated Main Activity");
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        switch(item.getItemId()){

            case R.id.nav_habits:
                // Handle the camera action
                intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_environment:
                intent = new Intent(getApplication(), Environment.class);
                startActivity(intent);
                break;
            case R.id.nav_rewards:
                intent = new Intent(getApplication(), Rewards.class);
                startActivity(intent);
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        return true;
    }
}
