package sg.edu.rp.c346.id22035660.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.rp.c346.id22035660.demodatabase.R;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayList<Task> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert;
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);

        ArrayAdapter<Task>adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(adapter);

        btnInsert = findViewById(R.id.btnInsert);
            btnInsert.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // Create the DBHelper object, passing in the
                    // activity's Context
                    DBHelper db = new DBHelper(MainActivity.this);

                    // Insert a task
                    db.insertTask("Submit RJ", "25 Apr 2021");

                }
            });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);



                //listview
                ArrayList<Task> data1 = db.getTasks();
                al.clear();
                al.addAll(data1);
                adapter.notifyDataSetChanged();

            }
        });

    }
}
