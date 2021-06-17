package space.nullpointerexception.libraary.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import space.nullpointerexception.libraary.Adapters.BookAdapter;
import space.nullpointerexception.libraary.DataUtils.Book;
import space.nullpointerexception.libraary.DataUtils.Utility;
import space.nullpointerexception.libraary.R;

public class SearchHomeLayout extends Fragment {

    private ArrayList<Book> books = new ArrayList<>();

    public SearchHomeLayout() {
        // Required empty public constructor
    }

    public SearchHomeLayout(ArrayList<Book> books){
        this.books = books;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_home_layout, container, false);

        RecyclerView rv = view.findViewById(R.id.searched_books_wrapper);
        rv.setLayoutManager(new GridLayoutManager(getContext(), Utility.calculateNoOfColumns(requireContext(),140)));
        rv.setAdapter(new BookAdapter(books,BookAdapter.GRID_LAYOUT));

        return view;
    }
}