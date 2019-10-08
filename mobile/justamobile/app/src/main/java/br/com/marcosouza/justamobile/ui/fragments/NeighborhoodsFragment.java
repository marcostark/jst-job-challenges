package br.com.marcosouza.justamobile.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.Neighborhoods;
import br.com.marcosouza.justamobile.model.NeighborhoodsResponse;
import br.com.marcosouza.justamobile.ui.adapter.NeighbordhoodsAdapter;
import br.com.marcosouza.justamobile.ui.viewmodels.NeighborhoodsViewModel;
import br.com.marcosouza.justamobile.util.Utils;

public class NeighborhoodsFragment extends Fragment implements SearchView.OnQueryTextListener {

    private NeighborhoodsViewModel neighborhoodsViewModel;
    private ArrayList<Neighborhoods> neighborhoods = new ArrayList <> ();
    private NeighbordhoodsAdapter neighbordhoodsAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        neighborhoodsViewModel =
                ViewModelProviders.of(this).get(NeighborhoodsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_neighborhoods, container, false);
        recyclerView = root.findViewById(R.id.rv_collection);
        neighborhoodsViewModel.init();

        neighborhoodsViewModel.getNeighbordhoodsRepository().observe(this, new Observer<NeighborhoodsResponse>() {
            @Override
            public void onChanged(NeighborhoodsResponse neighborhoodsResponse) {
                if(neighborhoodsResponse == null){
                    return;
                }
                if(neighborhoodsResponse.getError() == null){
                    List<Neighborhoods> newsArticles = neighborhoodsResponse.getResults();
                    neighborhoods.addAll(newsArticles);
                    neighbordhoodsAdapter.notifyDataSetChanged();
                } else {
                    Throwable e = neighborhoodsResponse.getError();
                    Utils.messageConnectFailed(getActivity(), e);
                }

            }
        });

        loadRecyclerView();
        return root;
    }
    private void loadRecyclerView() {
        if (neighbordhoodsAdapter == null) {
            neighbordhoodsAdapter = new NeighbordhoodsAdapter(getActivity(), neighborhoods);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(neighbordhoodsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            neighbordhoodsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String query = newText.toLowerCase();
        List<Neighborhoods> filterNeighborhoods = new ArrayList<>();

        for(Neighborhoods neighborhood : neighborhoods){
            final String name = neighborhood.getName().toLowerCase();
            if (name.contains(query)) {
                filterNeighborhoods .add(neighborhood);
            }
        }
        neighbordhoodsAdapter.filteredList(filterNeighborhoods );
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_neighbordhoods, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

}