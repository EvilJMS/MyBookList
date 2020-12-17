package cat.itb.booklistv3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapterVH extends RecyclerView.Adapter<BookAdapterVH.BookVH>{

    List<Book> bookList;

    public BookAdapterVH(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapterVH.BookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new BookVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookVH holder, int position) {
        holder.bindData(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public class BookVH extends RecyclerView.ViewHolder{
        public TextView bookName;
        public TextView author;
        public TextView status;
        public RatingBar ratingBar;
        public TextView ratingTextView;

        public BookVH(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.text_view_name_values);

            author = itemView.findViewById(R.id.text_view_author_values);

            status = itemView.findViewById(R.id.text_view_status_values);

            ratingTextView = itemView.findViewById(R.id.text_view_rating);

            ratingBar = itemView.findViewById(R.id.ratingBar2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavDirections list = BookListFragmentDirections
                            .actionListToFragment(bookList.get(getAdapterPosition()));
                    Navigation.findNavController(v).navigate(list);
                }
            });
        }

        public void bindData(Book book){
            if (book!=null){
                bookName.setText(book.getName());
                author.setText(book.getAuthor());
                status.setText(book.getStatus());
                if (book.getStatus().equalsIgnoreCase("Readed")){
                    ratingBar.setRating(book.getRating());
                    ratingTextView.setVisibility(View.VISIBLE);
                    ratingBar.setVisibility(View.VISIBLE);
                } else {
                    ratingBar.setRating(0);
                    ratingTextView.setVisibility(View.INVISIBLE);
                    ratingBar.setVisibility(View.INVISIBLE);
                }

            }
        }

    }

    public void addBook(Book book){
        bookList.add(book);
        this.notifyDataSetChanged();
    }

}
