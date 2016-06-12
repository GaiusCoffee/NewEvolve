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

public class LibraryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.libraryRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        List<CardData> libraryData = new ArrayList<CardData>();
        libraryData.add(new CardData(R.drawable.book_quickstart,
                "Quickstart", "<-- Click here to learn how to play Lorekeeper APPRPG!"));
        libraryData.add(new CardData(R.drawable.book_lkm,
                "Lorekeeper's Manual", "Worldbuilding & Adventurer Wrangling"));
        libraryData.add(new CardData(R.drawable.book_agb,
                "Adventurer's Guidebook", "Sword, Sorcery and Socializing 101"));
        //libraryData.add(new CardData(R.drawable.book_forge,
        //        "Forge", "Lorem Ipsum."));

        recyclerView.setAdapter(new CardAdapter(libraryData));
        return rootView;
    }
}