package fr3380ot.com.gro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.HashMap;

public class Rewards extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
