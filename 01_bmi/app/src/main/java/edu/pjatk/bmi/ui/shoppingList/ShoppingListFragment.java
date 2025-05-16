package edu.pjatk.bmi.ui.shoppingList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import edu.pjatk.bmi.R;

public class ShoppingListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ShoppingListAdapter adapter;

    private final List<String> shoppingItems = Arrays.asList(
            "2 eggs", "1 glass of milk", "100g flour"
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ShoppingListAdapter(shoppingItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
