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
 * Created by Ben on 4/9/16.
 */
public class AddHabit extends Activity {

    EditText title;
    Spinner diffSpinner;
    Spinner freqSpinner;
    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        title = (EditText) findViewById(R.id.titleEditText);

        //diffSpinner Setup
        diffSpinner = (Spinner) findViewById(R.id.diffSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                AddHabit.this, R.array.difficultyArray, android.R.layout.simple_spinner_dropdown_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diffSpinner.setAdapter(adapter1);

        //freqSpinner Setup
        freqSpinner = (Spinner) findViewById(R.id.freqSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                AddHabit.this, R.array.frequencyArray, android.R.layout.simple_spinner_dropdown_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        freqSpinner.setAdapter(adapter2);
    }

    //TODO: If field isn't answered, throw error
    public void addHabit(View view) {

        HashMap<String, String> queryValuesMap = new HashMap<>();

        queryValuesMap.put("title", title.getText().toString());
        queryValuesMap.put("difficulty", diffSpinner.getSelectedItem().toString());
        queryValuesMap.put("frequency", freqSpinner.getSelectedItem().toString());

        dbTools.insertHabit(queryValuesMap);

        this.callMainActivity(view);

    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}