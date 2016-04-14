package fr3380ot.com.gro2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ContentMain extends Activity {

    Intent intent;
    TextView habitId;
    DBTools dbTools = new DBTools(this);

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        //Pull habits from database and store in habitList
//        ArrayList<HashMap<String, String>> habitList = dbTools.getAllHabits();
//        dbTools.getAllHabits();

        //If there are habits created
//        if(habitList.size() != 0) {
//            ListView listView = (ListView) findViewById(android.R.id.list);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                // When an item is clicked, get the TextView with the matching Id
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    //the item clicked
//                    habitId = (TextView) view.findViewById(R.id.habitId);
//                    String habitIdValue = habitId.getText().toString();
//
//                    intent = new Intent(getApplication(), EditHabit.class);
//
//                    intent.putExtra("habitId", habitIdValue);
//
//                    startActivity(intent);
//                }
//            });
//
//            ListAdapter adapter = new SimpleAdapter(
//                    this, habitList, R.layout.habit_entry, new String[] {"habitId","Title"}, new int[] {
//                    R.id.habitId, R.id.habitTitle});
//
//            listView.setAdapter(adapter);

//        ListView listView = (ListView) findViewById(android.R.id.list);
//
//        ArrayList<HashMap<String, String>> test = new ArrayList<>();
//        HashMap<String, String> hash = new HashMap<>();
//        for(int j=0; j < 3; j++) {
//            for (int i = 1; i < 5; i++) {
//                hash.put("Value", String.valueOf(i));
//            }
//            test.add(hash);
//            hash.clear();
//        }
//
//        ArrayAdapter<HashMap<String, String>> adapter = new ArrayAdapter<HashMap<String, String>>(
//                this, android.R.layout.simple_list_item_1, test);
//        listView.setAdapter(adapter);
    }
}
