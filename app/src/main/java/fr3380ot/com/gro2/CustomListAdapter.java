package fr3380ot.com.gro2;

import android.content.Context;
import android.content.Intent;
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
    Intent intent;
    TextView habitId;

    public CustomListAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        v.findViewById(R.id.tableRow)
                .setOnClickListener(this);

        v.findViewById(R.id.plus)
                .setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tableRow:
                habitId = (TextView) v.findViewById(R.id.habitId);
                String habitIdValue = habitId.getText().toString();

                intent = new Intent(context.getApplicationContext(), EditHabit.class);

                intent.putExtra("habitId", habitIdValue);

                context.startActivity(intent);
                break;
            case R.id.plus:
                Log.d("Test", "plus clicked");
            default:
                break;
        }
    }

}

