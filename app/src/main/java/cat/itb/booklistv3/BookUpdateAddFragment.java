package cat.itb.booklistv3;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class BookUpdateAddFragment extends Fragment {
    RecyclerView recyclerView;
    BookAdapterVH adapterVH;
    BookViewModel viewModel;
    TextView title;
    EditText titleEditText;
    TextView author;
    EditText authorEditText;
    TextView statusTextView;
    Button confirmButton;
    String status;
    RadioGroup radioGroup;
    RadioButton planToRead;
    RadioButton reading;
    RadioButton read;
    Book book;
    Book bookNew;
    TextView ratingBarTextView;
    RatingBar ratingBar;
    boolean newBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_book_update_add_fragment,container,false);
        title = v.findViewById(R.id.nameTextView);
        titleEditText = v.findViewById(R.id.editTextName);
        author = v.findViewById(R.id.authorTextView);
        authorEditText = v.findViewById(R.id.editTextAuthor);
        statusTextView = v.findViewById(R.id.statusTextView);
        confirmButton = v.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUpdateBook();
                if (newBook){
                    Navigation.findNavController(v).navigate(R.id.action_update_to_list);
                }
            }
        });
        radioGroup = v.findViewById(R.id.radioGroup);
        planToRead = v.findViewById(R.id.planToRead);
        reading = v.findViewById(R.id.reading);
        read = v.findViewById(R.id.readed);
        ratingBarTextView = v.findViewById(R.id.ratingTextView);
        ratingBar = v.findViewById(R.id.ratingBarEdit);
        recyclerView = v.findViewById(R.id.recyclerView);
        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.planToRead:
                        status="Plan to Read";
                        ratingBarTextView.setVisibility(View.INVISIBLE);
                        ratingBar.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.readed:
                        status="Readed";
                        ratingBarTextView.setVisibility(View.VISIBLE);
                        ratingBar.setVisibility(View.VISIBLE);
                        break;
                    case R.id.reading:
                        status="Reading";
                        ratingBarTextView.setVisibility(View.INVISIBLE);
                        ratingBar.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
        if (getArguments()!=null){
            book = getArguments().getParcelable("book");
        }
        if (book!=null){
            authorEditText.setText(book.getAuthor());
            titleEditText.setText(book.getName());
            String currentStatus = book.getStatus();
            updateStatus(currentStatus);
            ratingBar.setRating(book.getRating());
            newBook=false;
        }
        else {
            newBook=true;
        }
        return v;
    }

    private void updateStatus(String currentStatus){
        switch (currentStatus){
            case "Plan to Read":
                radioGroup.check(R.id.planToRead);
                break;
            case "Readed":
                radioGroup.check(R.id.readed);
                break;
            case  "Reading":
                radioGroup.check(R.id.reading);
                break;
        }
    }

    private void addUpdateBook(){
        if (titleEditText.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Insert a Title: ", Toast.LENGTH_SHORT).show();
        } else {
            if (authorEditText.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Insert a Author: ", Toast.LENGTH_SHORT).show();
            } else {
                if (newBook){
                    bookNew = new Book(titleEditText.getText().toString(), authorEditText.getText().toString(), status, (int) ratingBar.getRating());
                    MainActivity.bookViewModel.addBook(bookNew);

                } else {
                    book.setName(titleEditText.getText().toString());
                    book.setAuthor(authorEditText.getText().toString());
                    book.setStatus(status);
                    book.setRating((int) ratingBar.getRating());
                }
            }
        }
    }


}