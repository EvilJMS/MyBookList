package cat.itb.booklistv3;



import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class BookViewModel extends ViewModel {
    List<Book> bookLists = new ArrayList<>();
    private BookAdapterVH adapterVH;

    public BookViewModel() {
        Book b1 = new Book("Re: Zero Vol 1","Tappei Nagatsuki","Readed",4);
        Book b2 = new Book("Re: Zero Vol 2","Tappei Nagatsuki","Readed",5);
        bookLists.add(b1);
        bookLists.add(b2);
    }

    public List<Book> getBookList() {
        return bookLists;
    }

    public void setBookList(List<Book> bookList) {
        this.bookLists = bookList;
    }

    public void addBook(Book book){
        adapterVH = new BookAdapterVH(bookLists);
        adapterVH.addBook(book);
    }
}

