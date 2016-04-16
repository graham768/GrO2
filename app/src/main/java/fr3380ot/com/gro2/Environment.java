package fr3380ot.com.gro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.HashMap;

public class Environment extends AppCompatActivity {

    ImageView env00, env01, env02, env10, env11, env12, env20, env21, env22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.environment);

        //Define elements

        env00 = (ImageView) findViewById(R.id.env00);
        env01 = (ImageView) findViewById(R.id.env01);
        env02 = (ImageView) findViewById(R.id.env02);
        env10 = (ImageView) findViewById(R.id.env10);
        env11 = (ImageView) findViewById(R.id.env11);
        env12 = (ImageView) findViewById(R.id.env12);
        env20 = (ImageView) findViewById(R.id.env20);
        env21 = (ImageView) findViewById(R.id.env21);
        env22 = (ImageView) findViewById(R.id.env22);

    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
