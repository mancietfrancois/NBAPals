package fr.mnf.nbapalsapp.logic.vector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import fr.mnf.nbapalsapp.R;
import fr.mnf.nbapalsapp.logic.utils.NBATeamsVc;

public class VectorDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
        ListView listView = (ListView) findViewById(R.id.listview);
        VectorAdapter adapter = new VectorAdapter(
                new ArrayList<>(Arrays.asList(NBATeamsVc.values())),
                getApplicationContext());
        listView.setAdapter(adapter);
    }
}
