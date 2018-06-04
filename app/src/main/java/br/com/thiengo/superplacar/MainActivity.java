package br.com.thiengo.superplacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.superplacar.domain.Match;
import br.com.thiengo.superplacar.extras.FifaRequest;
import br.com.thiengo.superplacar.extras.Worker;


public class MainActivity extends AppCompatActivity {

    private MatchesAdapter mMatchesAdapter;
    private ArrayList<Match> mMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mMatches = savedInstanceState.getParcelableArrayList(Match.MATCHES_KEY);
        } else {
            mMatches = new ArrayList<>();
        }
        initViews();
        retrieveMatchesStream();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Match.MATCHES_KEY, mMatches);
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        RecyclerView recyclerView = findViewById(R.id.rv_matches);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);

        mMatchesAdapter = new MatchesAdapter(this, mMatches);
        recyclerView.setAdapter(mMatchesAdapter);
    }

    private void retrieveMatchesStream() {
        new Worker(this).start();
    }

    public void update(List<Match> j) {
        mMatches.clear();
        mMatches.addAll(j);
        mMatchesAdapter.notifyDataSetChanged();
    }
}