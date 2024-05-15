package com.to_do_list.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.to_do_list.R;
import com.to_do_list.app.data.pojo.Task;
import com.to_do_list.app.domain.UpdateTaskViewModel;
import com.to_do_list.databinding.UpdateTaskLayoutBinding;
import com.to_do_list.util.Util;

import java.util.ArrayList;
import java.util.Objects;


public class UpdateTask extends Fragment {

    public UpdateTask() {
    }

    private UpdateTaskLayoutBinding binding;
    private UpdateTaskViewModel viewModel;
    private Task task;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = UpdateTaskLayoutBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(UpdateTaskViewModel.class);
        viewModel.setContext(requireContext());

        if (!(getArguments() == null)) {
            Bundle args = getArguments();
            task = args.getParcelable(TaskList.ID_TAG);
            assert task != null;
            binding.upTitle.setText(task.getTitle());
            binding.upDdesc.setText(task.getDescription());
            binding.prioritySpinner.setSelection(Integer.parseInt(task.getPriority()) + 1);
            binding.statusSpinner.setSelection(task.getStatus().equalsIgnoreCase("pending") ? 0 : 1);
        }
        binding.prioritySpinner.setAdapter(getAdapter());
        binding.statusSpinner.setAdapter(getStatus());
        binding.updateButton.setOnClickListener(v -> {
            Task upadate = new Task();
            upadate.setId(task.getId());
            upadate.setTitle(Objects.requireNonNull(binding.upTitle.getText()).toString());
            upadate.setDescription(Objects.requireNonNull(binding.upDdesc.getText()).toString());
            upadate.setPriority(String.valueOf(binding.prioritySpinner.getSelectedItemPosition() + 1));
            upadate.setDate(this.task.getDate());
            upadate.setStatus(binding.statusSpinner.getSelectedItemPosition() == 0 ? "Pending" : "Completed");
            viewModel.updateTask(upadate);
            Util.openAlertDialogue(requireContext(), "Complete", "Task Updated Successfully", () -> requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new TaskList())
                    .commit());
        });

        return binding.getRoot();
    }

    private ArrayAdapter<String> getStatus() {
        ArrayList<String> list = new ArrayList<>();
        list.add("pending");
        list.add("complete");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_dropdown_item_1line, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        return adapter;
    }

    private ArrayAdapter<String> getAdapter() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_dropdown_item_1line, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        return adapter;
    }

}