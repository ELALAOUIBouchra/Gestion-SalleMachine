package com.example.sqllite_machine.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.sqllite_machine.bean.Static;
import com.example.sqllite_machine.databinding.FragmentSlideshowBinding;
import com.example.sqllite_machine.service.MachineService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;


import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    List<Static> machines;
    MachineService ms;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ms = new MachineService(getContext());
        machines = ms.Machinesbysalle();
        BarChart chart = (BarChart) binding.chart;
        List<BarEntry> entries = new ArrayList<BarEntry>();
        int count = 0;
        for (Static data : machines) {
            // turn your data into Entry objects
            entries.add(new BarEntry(count++, data.getNb()));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Label"); // add entries to dataset
        BarData lineData = new BarData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}