package fr3380ot.com.gro2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

public class EditHabit extends AppCompatActivity {

    EditText title;
    Spinner diffSpinner;
    Spinner freqSpinner;
    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_habit);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        //Item instantiation
        title = (EditText) findViewById(R.id.titleEditText);
        diffSpinner = (Spinner) findViewById(R.id.diffSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                EditHabit.this, R.array.difficultyArray, android.R.layout.simple_spinner_dropdown_item);
        freqSpinner = (Spinner) findViewById(R.id.freqSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                EditHabit.this, R.array.frequencyArray, android.R.layout.simple_spinner_dropdown_item);

        //Find the habit to edit
        Intent intent = getIntent();
        String habitId = intent.getStringExtra("habitId");
        HashMap<String, String> habit = dbTools.getHabitInfo(habitId);

        //Place the current habit info in the view
        if(habit.size() != 0){
            title.setText(habit.get("title"));

            int spinPos = adapter1.getPosition(habit.get("difficulty"));
            diffSpinner.setSelection(spinPos);

            spinPos = adapter2.getPosition(habit.get("frequency"));
            freqSpinner.setSelection(spinPos);
        }

    }
}
