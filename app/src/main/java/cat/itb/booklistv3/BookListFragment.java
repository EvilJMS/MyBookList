package cat.itb.booklistv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BookListFragment extends Fragment {
    RecyclerView recyclerView;
    BookAdapterVH adapterVH;
    BookViewModel viewModel;
    FloatingActionButton addButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewModel==null){
            viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_list_fragment,container,false);
        recyclerView = v.findViewById(R.id.recyclerView);
        addButton = v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections list = BookListFragmentDirections
                        .actionAddNewBook(null);
                Navigation.findNavController(v).navigate(list);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterVH = new BookAdapterVH(viewModel.bookLists);
        recyclerView.setAdapter(adapterVH);
        return v;
    }
}