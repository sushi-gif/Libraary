package space.nullpointerexception.libraary.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import space.nullpointerexception.libraary.Activities.ShowAll;
import space.nullpointerexception.libraary.Adapters.BookAdapter;
import space.nullpointerexception.libraary.DataUtils.BooksManager;
import space.nullpointerexception.libraary.R;

public class DefaultHomeLayout extends Fragment {

    public DefaultHomeLayout() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_default_home_layout, container, false);

        RecyclerView recBooksWrapper = view.findViewById(R.id.rec_books_wrapper);
        RecyclerView ratBooksWrapper = view.findViewById(R.id.rat_books_wrapper);

        recBooksWrapper.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        ratBooksWrapper.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        BooksManager bm = BooksManager.getInstance();

        recBooksWrapper.setAdapter(new BookAdapter(bm.getRecBooks(),BookAdapter.HORIZONTAL_DEFAULT));
        ratBooksWrapper.setAdapter(new BookAdapter(bm.getNewBooks(),BookAdapter.HORIZONTAL_DEFAULT));

        view.findViewById(R.id.show_all).setOnClickListener(v -> v.getContext().startActivity(new Intent(getContext(), ShowAll.class)));

        return view;
    }
}