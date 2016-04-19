package fr3380ot.com.gro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class Rewards extends AppCompatActivity{

    DBTools dbTools = new DBTools(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);

        ArrayList<HashMap<String, String>> plantList = dbTools.getAllPlants();

        //TODO: Populate reward list
        // should plant and reward db be consolidated with field
        //isPlant or isReward in order to better populate this listView?
        if(plantList.size() != 0) {
            ListView listView = (ListView) findViewById(android.R.id.list);

            CustomListAdapter adapter = new CustomListAdapter(
                    this, plantList, R.layout.habit_entry, new String[]{"habitId", "habitId", "title", "difficulty", "frequency"}, new int[]{
                    R.id.habitId1, R.id.habitId2, R.id.habitTitle, R.id.habitDifficulty, R.id.habitFrequency});

            listView.setAdapter(adapter);
        }
    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void purchaseSunflower(View v){
        HashMap<String, String> user = dbTools.getUserInfo();
        int oxygen = Integer.parseInt(user.get("oxygenLevel"));
        int result = -1;
        if (oxygen >=  25){
            oxygen-=25;
            HashMap<String, String> plant = dbTools.getPlantInfo("1");
            result = dbTools.insertEnvironment(plant);
            user.put("oxygenLevel", Integer.toString(oxygen));
            dbTools.updateUser(user);
        }
        switch(result){
            case -1:
                Log.d("Purchase Sunflower", "Failure");
                break;
            case 0:
                Log.d("Purchase Sunflower", "Success");
                break;
            default:
                Log.d("Purchase Sunflower", "Failure");
                break;
        }
    }

    public void purchaseCamellia(View v){
        HashMap<String, String> user = dbTools.getUserInfo();
        int oxygen = Integer.parseInt(user.get("oxygenLevel"));
        int result = -1;
        if (oxygen >=  50){
            oxygen-=50;
            HashMap<String, String> plant = dbTools.getPlantInfo("2");
            result = dbTools.insertEnvironment(plant);
            user.put("oxygenLevel", Integer.toString(oxygen));
            dbTools.updateUser(user);
        }
        switch(result){
            case -1:
                Log.d("Purchase Camellia", "Failure");
                break;
            case 0:
                Log.d("Purchase Camellia", "Success");
                break;
            default:
                Log.d("Purchase Camellia", "Failure");
                break;
        }

    }

    public void purchaseChrysanthemum(View v){
        HashMap<String, String> user = dbTools.getUserInfo();
        int oxygen = Integer.parseInt(user.get("oxygenLevel"));
        int result = -1;
        if (oxygen >=  75){
            oxygen-=75;
            HashMap<String, String> plant = dbTools.getPlantInfo("3");
            result = dbTools.insertEnvironment(plant);
            user.put("oxygenLevel", Integer.toString(oxygen));
            dbTools.updateUser(user);
        }
        switch(result){
            case -1:
                Log.d("Purchase Chrysanthemum", "Failure");
                break;
            case 0:
                Log.d("Purchase Chrysanthemum", "Success");
                break;
            default:
                Log.d("Purchase Chrysanthemum", "Failure");
                break;
        }
    }
}
