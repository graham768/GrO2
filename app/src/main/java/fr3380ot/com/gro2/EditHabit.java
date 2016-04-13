package fr3380ot.com.gro2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditHabit extends AppCompatActivity {

    EditText title;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_habit);

        //TODO: Finish EditHabit
    }
}
