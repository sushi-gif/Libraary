package space.nullpointerexception.libraary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import space.nullpointerexception.libraary.Adapters.BookAdapter;
import space.nullpointerexception.libraary.DataUtils.BooksManager;
import space.nullpointerexception.libraary.R;
import space.nullpointerexception.libraary.DataUtils.Utility;

public class ShowAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        RecyclerView rv = findViewById(R.id.show_all_rv);

        rv.setLayoutManager(new GridLayoutManager(this, Utility.calculateNoOfColumns(this,140)));

        BooksManager bm = BooksManager.getInstance();

        rv.setAdapter(new BookAdapter(bm.getBooks(),BookAdapter.GRID_LAYOUT));
        
    }
}