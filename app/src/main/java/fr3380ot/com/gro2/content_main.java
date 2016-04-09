package fr3380ot.com.gro2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class content_main extends ListActivity{
    DBTools dbTools = new DBTools(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ArrayList<HashMap<String, String>> habitList = dbTools.getAllHabits();
        dbTools.getAllHabits();

        if(habitList.size() != 0) {
            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    habitId = (TextView) view.findViewById(R.id.habitId);
                }
            });
        }
    }
}
