package com.example.sqllite_machine.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.sqllite_machine.R;
import com.example.sqllite_machine.bean.Machine;
import com.example.sqllite_machine.bean.Salle;
import com.example.sqllite_machine.databinding.FragmentGalleryBinding;
import com.example.sqllite_machine.service.MachineService;
import com.example.sqllite_machine.service.SalleService;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {


    private FragmentGalleryBinding binding;
    private SalleService salleService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        salleService = new SalleService(getContext());

        final Button create = binding.btnCreate;
        final EditText marque = binding.marque;
        final EditText reference = binding.reference;
        final Spinner spinner=binding.Spinner ;

        ArrayAdapter<String> adapter;
        List<String> liste= new ArrayList<String>();
        for(Salle salle : salleService.findAll()) {
            liste.add(salle.getCode());
        }
        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, liste);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MachineService machineService = new MachineService(getContext());
                salleService = new SalleService(getContext());
                Salle salle = salleService.findByCode(spinner.getSelectedItem().toString());
                machineService.add(new Machine(marque.getText().toString(), reference.getText().toString(), salle ));
                Toast.makeText(getContext() ," Machine crée avec succès :) ", Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}