package fr3380ot.com.gro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class Rewards extends MainActivity implements NavigationView.OnNavigationItemSelectedListener {

    DBTools dbTools = new DBTools(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ArrayList<HashMap<String, String>> plantList = dbTools.getAllPlants();
        ArrayList<HashMap<String, String>> rewardList = dbTools.getAllRewards();

        //Create a list containing both table elements to adapt them to the rewards ListView
        ArrayList<HashMap<String, String>> totalList = new ArrayList<>();
        totalList.addAll(plantList);
        totalList.addAll(rewardList);

        // should plant and reward db be consolidated with field
        //isPlant or isReward in order to better populate this listView?
        if(totalList.size() != 0) {
            ListView listView = (ListView) findViewById(R.id.rewardListView);

            //TODO: Debug binding pictureId.
            CustomListAdapter adapter = new CustomListAdapter(
                    this, totalList, R.layout.reward_entry, new String[]{"plantId", "plantId", "title", "price"}, new int[]{
                    R.id.plantId1, R.id.plantId2, R.id.rewardTitle, R.id.price});

            listView.setAdapter(adapter);
        }
    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    //TODO: implement single purchase method to be run onClick R.id.tableRowPurchase
//    public void purchaseSunflower(View v){
//        HashMap<String, String> user = dbTools.getUserInfo();
//        int oxygen = Integer.parseInt(user.get("oxygenLevel"));
//        int result = -1;
//        if (oxygen >=  25){
//            oxygen-=25;
//            HashMap<String, String> plant = dbTools.getPlantInfo("1");
//            result = dbTools.insertTile(plant);
//            user.put("oxygenLevel", Integer.toString(oxygen));
//            dbTools.updateUser(user);
//        }
//        switch(result){
//            case -1:
//                Log.d("Purchase Sunflower", "Failure");
//                break;
//            case 0:
//                Log.d("Purchase Sunflower", "Success");
//                break;
//            default:
//                Log.d("Purchase Sunflower", "Failure");
//                break;
//        }
//    }
//
//    public void purchaseCamellia(View v){
//        HashMap<String, String> user = dbTools.getUserInfo();
//        int oxygen = Integer.parseInt(user.get("oxygenLevel"));
//        int result = -1;
//        if (oxygen >=  50){
//            oxygen-=50;
//            HashMap<String, String> plant = dbTools.getPlantInfo("2");
//            result = dbTools.insertTile(plant);
//            user.put("oxygenLevel", Integer.toString(oxygen));
//            dbTools.updateUser(user);
//        }
//        switch(result){
//            case -1:
//                Log.d("Purchase Camellia", "Failure");
//                break;
//            case 0:
//                Log.d("Purchase Camellia", "Success");
//                break;
//            default:
//                Log.d("Purchase Camellia", "Failure");
//                break;
//        }
//
//    }
//
//    public void purchaseChrysanthemum(View v){
//        HashMap<String, String> user = dbTools.getUserInfo();
//        int oxygen = Integer.parseInt(user.get("oxygenLevel"));
//        int result = -1;
//        if (oxygen >=  75){
//            oxygen-=75;
//            HashMap<String, String> plant = dbTools.getPlantInfo("3");
//            result = dbTools.insertTile(plant);
//            user.put("oxygenLevel", Integer.toString(oxygen));
//            dbTools.updateUser(user);
//        }
//        switch(result){
//            case -1:
//                Log.d("Purchase Chrysanthemum", "Failure");
//                break;
//            case 0:
//                Log.d("Purchase Chrysanthemum", "Success");
//                break;
//            default:
//                Log.d("Purchase Chrysanthemum", "Failure");
//                break;
//        }
//    }
}
