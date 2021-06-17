package space.nullpointerexception.libraary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Arrays;

import space.nullpointerexception.libraary.DataUtils.Book;
import space.nullpointerexception.libraary.DataUtils.BooksManager;
import space.nullpointerexception.libraary.DataUtils.Utility;
import space.nullpointerexception.libraary.R;

public class BookViewerActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_viewer_activity);

        Book book = (Book) getIntent().getSerializableExtra("book");
        Bitmap image = Utility.fromBase64ToBitmap(book.getBookCover());

        ConstraintLayout bCover = findViewById(R.id.book_cover);

        try{
            bCover.setBackground(new BitmapDrawable(getResources(),image));
        }catch (Exception e){
            bCover.setBackground((getDrawable(R.drawable.notfound)));
        }

        String authors = "by " + Arrays.toString(book.getAuthors()).replace("[","").replace("]","");
        String genres = Arrays.toString(book.getGenres()).replace("[","").replace("]","");

        ((TextView) findViewById(R.id.title)).setText(book.getTitle());
        ((TextView) findViewById(R.id.author)).setText(authors + " | " + book.getStudio());
        ((TextView) findViewById(R.id.age)).setText(book.getAudienceAge());
        ((RatingBar) findViewById(R.id.ratingbar)).setRating(book.getRating());
        ((TextView) findViewById(R.id.summary_text)).setText(book.getSummary());
        ((TextView) findViewById(R.id.book_price)).setText("$ " + book.getPrice());
        ((TextView) findViewById(R.id.language)).setText(book.getLanguage() + "," + book.getCountry());
        ((TextView) findViewById(R.id.isbn)).setText(book.getIsbn());
        ((TextView) findViewById(R.id.ean)).setText(book.getEan());
        ((TextView) findViewById(R.id.genres)).setText(genres);
        ((TextView) findViewById(R.id.weight)).setText(book.getWeight() + "gr, " + book.getPages() + " pag." );
        ((TextView) findViewById(R.id.date)).setText(book.getReleaseDate() + ", code " + book.getDewhy());


        try{
            if ((getIntent().getBooleanExtra("hide_fab", false))) {
                findViewById(R.id.addBook).setVisibility(View.GONE);
            } else {
                findViewById(R.id.addBook).setOnClickListener(v -> BooksManager.getInstance().addBookToShoppingCart(book));
            }
        }catch (Exception ignored){
            findViewById(R.id.addBook).setOnClickListener(v -> BooksManager.getInstance().addBookToShoppingCart(book));
         }

    }

}