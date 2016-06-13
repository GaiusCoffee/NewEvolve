package io.github.gaiusandhartsolutions.newevolve;

import android.content.Intent;
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
                getString(R.string.book_qst), getString(R.string.book_qst_subtitle),
                OnBookClick(getString(R.string.book_qst))));
        libraryData.add(new CardData(R.drawable.book_lkm,
                getString(R.string.book_lkm), getString(R.string.book_lkm_subtitle),
                OnBookClick(getString(R.string.book_lkm))));
        libraryData.add(new CardData(R.drawable.book_agb,
                getString(R.string.book_agb), getString(R.string.book_agb_subtitle),
                OnBookClick(getString(R.string.book_agb))));
        //libraryData.add(new CardData(R.drawable.book_forge,
        //        "Forge", "Lorem Ipsum."));

        recyclerView.setAdapter(new CardAdapter(libraryData));
        return rootView;
    }

    public View.OnClickListener OnBookClick(final String tag){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookActivity.class);
                intent.putExtra(Engine.TAG_BOOK, tag);
                startActivity(intent);
                getActivity().finish();
            }
        };
    }
}