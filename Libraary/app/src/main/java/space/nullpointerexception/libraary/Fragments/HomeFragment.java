package space.nullpointerexception.libraary.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Objects;

import space.nullpointerexception.libraary.DataUtils.BooksManager;
import space.nullpointerexception.libraary.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Fragment def = new DefaultHomeLayout();
        setFragment(def);

        EditText ed = view.findViewById(R.id.search_bar);
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().equals("")) setFragment(def);
                else setFragment(new SearchHomeLayout(BooksManager.getInstance().search(s.toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void setFragment(Fragment newFragment){
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_layout_wrapper,newFragment).commit();
    }

}