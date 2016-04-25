package com.ligartolabs.everpobre.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ligartolabs.everpobre.fragments.NotebooksFragment;
import com.ligartolabs.everpobre.R;

public class NotebookListActivity extends AppCompatActivity {

    public static final String TAG = NotebookListActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_list);

        Log.d(TAG, "Hello from Activity");

        FragmentManager fm = getSupportFragmentManager();
        if (fm != null) {
            NotebooksFragment notebooksFragment = new NotebooksFragment();

            fm.beginTransaction()
                    .add(R.id.activity_notebook_fragment_container , notebooksFragment)
                    .commit();
        }
    }
}
