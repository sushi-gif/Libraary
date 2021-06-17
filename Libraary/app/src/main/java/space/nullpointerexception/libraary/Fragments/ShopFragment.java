package space.nullpointerexception.libraary.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import space.nullpointerexception.libraary.Adapters.BookAdapter;
import space.nullpointerexception.libraary.DataUtils.BooksManager;
import space.nullpointerexception.libraary.R;
import space.nullpointerexception.libraary.DataUtils.Utility;

public class ShopFragment extends Fragment {

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop, container, false);

        RecyclerView shopBooksWrapper = v.findViewById(R.id.shop_books_wrapper);

        shopBooksWrapper.setLayoutManager(new GridLayoutManager(getContext(), Utility.calculateNoOfColumns(requireContext(),140)));

        shopBooksWrapper.setAdapter(new BookAdapter(BooksManager.getInstance().getAddedBooks(),BookAdapter.GRID_LAYOUT_REMOVE));

        return v;

    }
}