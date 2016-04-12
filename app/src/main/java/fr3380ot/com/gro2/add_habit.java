package fr3380ot.com.gro2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

/**
 * Created by Ben on 4/9/16.
 */
public class add_habit extends Activity {

    EditText title;
    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        //title
        title = (EditText) findViewById(R.id.titleEditText);

        //diffSpinner Setup
        Spinner diffSpinner = (Spinner) findViewById(R.id.diffSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(add_habit.this, R.array.difficultyArray, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diffSpinner.setAdapter(adapter);

        Intent intent = getIntent();

        // Add habitId intent stuff here

//        HashMap<String, String>
    }

}