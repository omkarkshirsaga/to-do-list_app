package com.to_do_list.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.to_do_list.R;
import com.to_do_list.adapters.TaskAdapter;
import com.to_do_list.app.data.pojo.Task;
import com.to_do_list.app.domain.TaskListViewModel;
import com.to_do_list.databinding.TaskListBinding;

import java.util.ArrayList;
import java.util.Objects;


public class TaskList extends Fragment {
    public TaskList() {
    }

    //hi
    private TaskListBinding binding;
    private TaskListViewModel viewModel;
    private TaskAdapter adapter;
    public final static String ID_TAG = "task_id";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new TaskAdapter();
        setHasOptionsMenu(true);
        binding = TaskListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(TaskListViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        viewModel.setContext(requireContext());

        adapter.setClickListener((upadteOrDelete, task) -> {
            if (Objects.equals(upadteOrDelete, TaskAdapter.DELETE_TAG)) {
                viewModel.deleteTask(task);
            } else if (Objects.equals(upadteOrDelete, TaskAdapter.UPDATE_TAG)) {
                UpdateTask updateTask = new UpdateTask();
                Bundle bundle = new Bundle();
                bundle.putParcelable(ID_TAG, task);
                updateTask.setArguments(bundle);
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, updateTask)
                        .commit();
            }
        });

        binding.addbtn.setOnClickListener(v -> requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, new AddTask())
                .commit());

        viewModel.getFilter().observe(getViewLifecycleOwner(), tasks -> adapter.setTasks((ArrayList<Task>) tasks));

        binding.seachView.setOnCloseListener(() -> {
            viewModel.getAllTask();
            return true;
        });

        binding.taskRecyclerView.setLayoutManager(manager);
        binding.taskRecyclerView.setAdapter(adapter);
        viewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            if ((!tasks.isEmpty())) {
                binding.taskRecyclerView.setVisibility(View.VISIBLE);
                binding.noTaskCard.setVisibility(View.INVISIBLE);
                adapter.setTasks((ArrayList<Task>) tasks);
            } else {
                binding.taskRecyclerView.setVisibility(View.INVISIBLE);
                binding.noTaskCard.setVisibility(View.VISIBLE);
            }
        });

        binding.seachView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.filterSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return binding.getRoot();
    }
}