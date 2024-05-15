package com.to_do_list.app.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.to_do_list.R;
import com.to_do_list.app.domain.AddTaskViewModel;
import com.to_do_list.databinding.AddTaskBinding;
import com.to_do_list.util.Util;

import java.util.Objects;


public class AddTask extends Fragment {

    public AddTask() {
    }

    private AddTaskBinding binding;
    private AddTaskViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = AddTaskBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);
        viewModel.setContext(requireContext());
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setPriority(String.valueOf(position + 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        viewModel.getError().observe(getViewLifecycleOwner(), error -> Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());
        viewModel.getSuccess().observe(getViewLifecycleOwner(), success -> {
            Toast.makeText(requireContext(), success, Toast.LENGTH_SHORT).show();
        });
        binding.submit.setOnClickListener(v -> {
            if (!Objects.requireNonNull(binding.title.getText()).toString().isEmpty()) {
                if (!Objects.requireNonNull(binding.description.getText()).toString().isEmpty()) {
                    viewModel.onClick(binding.title.getText().toString(), binding.description.getText().toString());
                    binding.title.setText("");
                    binding.description.setText("");
                    Util.openAlertDialogue(requireContext(), "Complete", "Task Added Successfully", () ->
                            requireActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container, new TaskList())
                                    .commit());
                } else {
                    Toast.makeText(requireContext(), "Please Enter Description", Toast.LENGTH_SHORT).show();
                    binding.description.setError("Please Enter Description");
                }
            } else {
                Toast.makeText(requireContext(), "Please Enter Title", Toast.LENGTH_SHORT).show();
                binding.title.setError("Please Enter Title");
            }

        });
        return binding.getRoot();
    }
}