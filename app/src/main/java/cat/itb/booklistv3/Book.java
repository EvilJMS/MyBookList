package cat.itb.booklistv3;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    String name;
    String author;
    String status;
    int rating;
    public Book(String name, String author, String status, int rating) {
        this.name = name;
        this.author = author;
        this.status = status;
        this.rating = rating;
    }

    protected Book(Parcel in){
        name=in.readString();
        author=in.readString();
        status=in.readString();
        rating=in.readInt();
    }


    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(status);
        dest.writeInt(rating);
    }
}
