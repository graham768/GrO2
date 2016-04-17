package fr3380ot.com.gro2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Environment extends AppCompatActivity {

    ImageView env00, env01, env02, env10, env11, env12, env20, env21, env22;
    DBTools dbTools = new DBTools(this);
    final Handler handler = new Handler();
    Timer timer = new Timer();


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

    TimerTask sunflower  = new TimerTask(){
        @Override
        public void run(){
            handler.post(new Runnable(){
                @SuppressWarnings("unchecked")
                public void run(){
                    try{
                        addOxygen(2);
                        Log.d("Oxygen", "Added");
                    }catch(Exception e){
                        Log.d("Stop sucking", "cmon");
                    }
                }
            });
        }
    };

    TimerTask camellia  = new TimerTask(){
        @Override
        public void run(){
            handler.post(new Runnable(){
                @SuppressWarnings("unchecked")
                public void run(){
                    try{
                        addOxygen(4);
                        Log.d("Oxygen", "Added");
                    }catch(Exception e){
                        Log.d("Stop sucking", "cmon");
                    }
                }
            });
        }
    };

    TimerTask chrisanthemum  = new TimerTask(){
        @Override
        public void run(){
            handler.post(new Runnable(){
                @SuppressWarnings("unchecked")
                public void run(){
                    try{
                        addOxygen(8);
                        Log.d("Oxygen", "Added");
                    }catch(Exception e){
                        Log.d("Stop sucking", "cmon");
                    }
                }
            });
        }
    };

    private void addOxygen(int amount){
        HashMap<String, String> user = dbTools.getUserInfo();
        int oxygen = Integer.getInteger(user.get("oxygenLevel"));
        oxygen+=amount;
        user.put("oxygenLevel",Integer.toString(oxygen));
        dbTools.updateUser(user);
    }
}
