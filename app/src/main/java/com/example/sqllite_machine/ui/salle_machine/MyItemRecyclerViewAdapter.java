package com.example.sqllite_machine.ui.salle_machine;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sqllite_machine.bean.Machine;
import com.example.sqllite_machine.databinding.FragmentSalleMachineBinding;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Machine> mValues;

    public MyItemRecyclerViewAdapter(List<Machine> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentSalleMachineBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mark.setText(mValues.get(position).getMarque());
        holder.reference.setText(mValues.get(position).getRefernce());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mark;
        public final TextView reference;
        public Machine mItem;

        public ViewHolder(FragmentSalleMachineBinding binding) {
            super(binding.getRoot());
            mark = binding.marque;
            reference = binding.reference;
        }

        @Override
        public String toString() {
            return super.toString() ;
        }
    }
}