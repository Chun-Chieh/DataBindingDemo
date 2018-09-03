package com.chunchiehliang.networktest.ui.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chunchiehliang.networktest.EarthquakeLoader;
import com.chunchiehliang.networktest.R;
import com.chunchiehliang.networktest.databinding.ActivityMainBinding;
import com.chunchiehliang.networktest.model.Earthquake;
import com.chunchiehliang.networktest.ui.FilterBottomSheet;
import com.chunchiehliang.networktest.ui.adapter.EarthquakeAdapter;
import com.chunchiehliang.networktest.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

import static com.chunchiehliang.networktest.util.ViewUtils.convertDpToPixel;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {
    private List<Earthquake> earthquakeList = new ArrayList<>();
    private ActivityMainBinding binding;

    private EarthquakeAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /**
     * URL to query the USGS dataset for earthquake information
     */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/" +
            "query?format=geojson&eventtype=earthquake&orderby=time&minmag=3&limit=150";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (!new AppUtils(this).isConnected()) {
            binding.contentMain.configuration.setVisibility(View.GONE);
            binding.contentMain.noConnection.setVisibility(View.VISIBLE);

        } else {
            binding.contentMain.loadingIndicator.setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(0, null, this);
            Log.d(LOG_TAG, "initLoader");

            initRecyclerView();

            ClickHandler handler = new ClickHandler(this);
            binding.setHandler(handler);
        }
    }

    private void initRecyclerView() {
        mRecyclerView = binding.contentMain.recyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // the label elevation
        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (view.canScrollVertically(-1)) {
                    ViewCompat.setElevation(binding.contentMain.configuration, convertDpToPixel(4f, getApplicationContext()));
                } else {
                    ViewCompat.setElevation(binding.contentMain.configuration, 0f);
                }
            }
        });

        // the fab animation
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                FloatingActionButton fab = binding.fab;
//                if (dy > 0) {
//                    fab.hide();
//                } else {
//                    fab.show();
//                }
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
        mAdapter = new EarthquakeAdapter(this, earthquakeList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.d(LOG_TAG, "onCreateLoader");
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        Log.d(LOG_TAG, "onLoadFinished");
        // Hide loading indicator because the data has been loaded
        binding.contentMain.loadingIndicator.setVisibility(View.GONE);

        earthquakeList.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {

            earthquakeList.addAll(earthquakes);
            mAdapter.notifyDataSetChanged();
            binding.contentMain.configuration.setVisibility(View.VISIBLE);
            binding.contentMain.textViewLabelResults.setText(getResources().getQuantityString(R.plurals.numberOfResultsFound, earthquakeList.size(), earthquakeList.size()));
        } else {
            configureUiWhenEmpty();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(LOG_TAG, "onLoaderReset");
        earthquakeList.clear();
    }

    public class ClickHandler {
        Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {
            Snackbar.make(binding.getRoot(), "FAB Clicked", Snackbar.LENGTH_SHORT).show();
        }

        public void onFilterClicked(View view) {
            new FilterBottomSheet().show(getSupportFragmentManager(), "Dialog");
        }
    }

    private void configureUiWhenEmpty() {
        binding.contentMain.configuration.setVisibility(View.GONE);
        binding.contentMain.emptyView.setVisibility(View.VISIBLE);
    }
}