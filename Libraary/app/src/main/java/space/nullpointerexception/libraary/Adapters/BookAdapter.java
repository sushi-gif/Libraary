package space.nullpointerexception.libraary.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import space.nullpointerexception.libraary.Activities.BookViewerActivity;
import space.nullpointerexception.libraary.DataUtils.Book;
import space.nullpointerexception.libraary.DataUtils.Utility;
import space.nullpointerexception.libraary.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{

    public static int HORIZONTAL_DEFAULT = 0;
    public static int GRID_LAYOUT = 1;
    public static int GRID_LAYOUT_REMOVE = 2;
    public final ArrayList<Book> books;
    private final int flag;

    public BookAdapter(ArrayList<Book> books,int flag){
        this.books = books;
        this.flag = flag;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate((flag == HORIZONTAL_DEFAULT) ? R.layout.book_holder : R.layout.shop_book,parent,false);
        return new BookHolder(v);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {

        holder.bookCover.setImageBitmap(Utility.fromBase64ToBitmap(books.get(position).getBookCover()));
        String title = books.get(position).getTitle();

        title = (title.length() > 12) ? title.substring(0,12) + "... " : title;

        holder.title.setText(title);
        holder.price.setText("$" + books.get(position).getPrice());

        holder.bookCover.setOnClickListener(v -> {
            Intent viewBook = new Intent(v.getContext(), BookViewerActivity.class);
            viewBook.putExtra("book",books.get(position));
            viewBook.putExtra("hide_fab", flag == GRID_LAYOUT_REMOVE);
            v.getContext().startActivity(viewBook);
        });

        if(flag == GRID_LAYOUT_REMOVE) {
            holder.bookCover.setOnLongClickListener(v -> {
                books.remove(position);
                notifyDataSetChanged();
                return true;
            });
        }

    }

    protected static class BookHolder extends RecyclerView.ViewHolder{

        ImageView bookCover;
        TextView title;
        TextView price;

        public BookHolder(@NonNull View itemView) {
            super(itemView);

            bookCover = itemView.findViewById(R.id.book_cover);
            title = itemView.findViewById(R.id.book_title);
            price = itemView.findViewById(R.id.book_price);
        }
    }

}
