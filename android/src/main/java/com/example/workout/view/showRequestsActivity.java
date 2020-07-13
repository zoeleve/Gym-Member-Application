package com.example.workout.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.R;
import com.example.workout.RequestListAdapter;

import static com.example.workout.SignUp.Requests;

/**
 * In this activity a trainer can see all the pending requests
 * during that time ,so he can process those requests in the near future.
 */

public class showRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_requests);
        ListView list = (ListView) findViewById(R.id.theList);

        RequestListAdapter adapter = new RequestListAdapter(this , R.layout.adapter_view_layout ,Requests);
        list.setAdapter(adapter);
    }
}
