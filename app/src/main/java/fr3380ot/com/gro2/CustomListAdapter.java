package fr3380ot.com.gro2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 4/17/16.
 */
public class CustomListAdapter extends SimpleAdapter implements View.OnClickListener {

    Context context;

    public CustomListAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.content_main, null);
        }
        else {
            //TODO: change habit_entry elements into a grouping
            //Change TextView into that grouping so user can click anywhere in a row to edit habit
            TextView tv = (TextView) v.findViewById(R.id.habitTitle);
            tv.setOnClickListener(this);

            ImageView iv = (ImageView) v.findViewById(R.id.plus);
            iv.setOnClickListener(this);


        }
        return super.getView(position, convertView, parent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.habitTitle:
                Log.d("Test", "title clicked");
                break;
            case R.id.plus:
                Log.d("Test", "plus clicked");
            default:
                break;
        }
    }

}

