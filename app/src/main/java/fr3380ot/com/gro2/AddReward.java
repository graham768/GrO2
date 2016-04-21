package fr3380ot.com.gro2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

/**
 * Created by Ben on 4/21/16.
 */
public class AddReward extends Activity {

    EditText title;
    EditText price;
    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reward);

        title = (EditText) findViewById(R.id.titleEditText);
        price = (EditText) findViewById(R.id.priceEditText);



    }

    //TODO: If field isn't answered, throw error
    public void addReward(View view) {

        HashMap<String, String> queryValuesMap = new HashMap<>();

        queryValuesMap.put("title", title.getText().toString());
        queryValuesMap.put("price", price.getText().toString());

        dbTools.insertReward(queryValuesMap);

        this.callRewardActivity(view);

    }

    public void callRewardActivity(View view) {
        Intent intent = new Intent(getApplication(), Rewards.class);
        startActivity(intent);
        this.finish();
    }

}