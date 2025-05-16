package edu.pjatk.bmi.ui.shoppingList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.pjatk.bmi.R;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private final List<String> items;
    private final boolean[] checkedItems;

    public ShoppingListAdapter(List<String> items) {
        this.items = items;
        this.checkedItems = new boolean[items.size()];
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checkbox);
            textView = view.findViewById(R.id.item_text);
        }
    }

    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {
        String item = items.get(position);
        holder.textView.setText(item);
        holder.checkBox.setChecked(checkedItems[position]);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedItems[position] = isChecked;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
