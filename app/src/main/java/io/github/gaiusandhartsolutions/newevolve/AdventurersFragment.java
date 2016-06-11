package io.github.gaiusandhartsolutions.newevolve;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdventurersFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_adventurers, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.adventurersRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        /* Test Data */
        List<CardData> testData = new ArrayList<CardData>();
        testData.add(new CardData(R.drawable.adventurer_monkey,
                "Test Adventurer 1", "Hello, World!"));
        testData.add(new CardData(R.drawable.adventurer_pig,
                "Test Adventurer 2", "Hi, World!"));
        testData.add(new CardData(R.drawable.adventurer_elephant,
                "Test Adventurer 3", "Lorem Ipsum."));

        recyclerView.setAdapter(new CardAdapter(testData));
        return rootView;
    }

}
