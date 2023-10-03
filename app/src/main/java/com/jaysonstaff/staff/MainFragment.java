package com.jaysonstaff.staff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.jaysonstaff.staff.model.Workspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {
    private List<String> workspaces;
    private ArrayAdapter<String> workspaceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listViewWorkspaces = view.findViewById(R.id.listViewWorkspaces);
        workspaces = new ArrayList<>();

        workspaceAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, workspaces);
        listViewWorkspaces.setAdapter(workspaceAdapter);

        Bundle args = getArguments();
        if (args != null) {
            String company = args.getString("company");
            String address = args.getString("address");
            String email = args.getString("email");
            String phone = args.getString("phone");
            String employees = args.getString("employees");

            // Add the new workspace data to the list
            String workspaceInfo = "Company: " + company + "\n" +
                    "Address: " + address + "\n" +
                    "Email: " + email + "\n" +
                    "Phone: " + phone + "\n" +
                    "Employees: " + employees + "\n";
            workspaces.add(workspaceInfo);
            workspaceAdapter.notifyDataSetChanged();
        }
        loadWorkspacesFromSharedPreferences();
        return view;
    }

    private void loadWorkspacesFromSharedPreferences() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("workspaces", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();

        // Create a temporary list to hold the updated workspaces
        List<String> updatedWorkspaces = new ArrayList<>(workspaces);

        // Iterate through the saved workspaces and add them to the temporary list
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String workspaceJson = entry.getValue().toString();

            // Convert the JSON back to a Workspace object
            Gson gson = new Gson();
            Workspace workspace = gson.fromJson(workspaceJson, Workspace.class);

            // Add the workspace to the temporary list
            String workspaceInfo = "Company: " + workspace.getCompany() + "\n" +
                    "Address: " + workspace.getAddress() + "\n" +
                    "Email: " + workspace.getEmail() + "\n" +
                    "Phone: " + workspace.getPhone() + "\n" +
                    "Employees: " + workspace.getEmployees() + "\n";
            updatedWorkspaces.add(workspaceInfo);
        }

        // Update the original list with the new workspaces
        workspaces.clear();
        workspaces.addAll(updatedWorkspaces);

        // Notify the adapter that the data has changed
        workspaceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_WORKSPACE && resultCode == RESULT_OK) {
            String company = data.getStringExtra("company");
            String address = data.getStringExtra("address");
            String email = data.getStringExtra("email");
            String phone = data.getStringExtra("phone");
            String employees = data.getStringExtra("employees");

            // Add the new workspace data to the list
            String workspaceInfo = "Company: " + company + "\n" +
                    "Address: " + address + "\n" +
                    "Email: " + email + "\n" +
                    "Phone: " + phone + "\n" +
                    "Employees: " + employees + "\n";
            workspaces.add(workspaceInfo);
            workspaceAdapter.notifyDataSetChanged();

            // Save the new workspace to SharedPreferences
            Workspace workspace = new Workspace(company, address, email, phone, employees);
            saveWorkspaceToSharedPreferences(workspace);
        }
    }



}
