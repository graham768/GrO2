package fr3380ot.com.gro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

/**
 * Created by Ben on 4/21/16.
 */

public class EditReward extends AppCompatActivity {

    EditText title;
    EditText price;
    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reward);

        //Define elements
        title = (EditText) findViewById(R.id.titleEditText);
        price = (EditText) findViewById(R.id.priceEditText);

        //Find the habit to edit
        Intent intent = getIntent();
        String plantId = intent.getStringExtra("plantId");
        HashMap<String, String> reward = dbTools.getRewardInfo(plantId);

        //Place the current habit info in the view
        if(reward.size() != 0){
            title.setText(reward.get("title"));
            price.setText(reward.get("price"));
        }

    }

    public void editReward(View view) {

        HashMap<String, String> queryValueMap = new HashMap<>();

        //Define elements
        title = (EditText) findViewById(R.id.titleEditText);
        price = (EditText) findViewById(R.id.priceEditText);

        Intent intent = getIntent();
        String plantId = intent.getStringExtra("plantId");

        queryValueMap.put("plantId", plantId);
        queryValueMap.put("title", title.getText().toString());
        queryValueMap.put("price", price.getText().toString());

        dbTools.updateReward(queryValueMap);

        this.callRewardActivity(view);
    }

    public void removeReward(View view) {

        Intent intent = getIntent();
        String plantId = intent.getStringExtra("plantId");

        dbTools.deleteReward(plantId);

        this.callRewardActivity(view);
    }

    public void callRewardActivity(View view) {
        Intent intent = new Intent(getApplication(), Rewards.class);
        startActivity(intent);
        this.finish();
    }
}
