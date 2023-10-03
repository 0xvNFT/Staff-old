package com.jaysonstaff.staff.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaysonstaff.staff.R;
import com.jaysonstaff.staff.model.Workspace;

import java.util.List;

public class WorkspaceAdapter extends RecyclerView.Adapter<WorkspaceAdapter.WorkspaceViewHolder> {

    private List<Workspace> workspaceList;

    public WorkspaceAdapter(List<Workspace> workspaceList) {
        this.workspaceList = workspaceList;
    }

    @NonNull
    @Override
    public WorkspaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workspace, parent, false);
        return new WorkspaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkspaceViewHolder holder, int position) {
        Workspace workspace = workspaceList.get(position);
        holder.bind(workspace);
    }

    @Override
    public int getItemCount() {
        return workspaceList.size();
    }

    public class WorkspaceViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCompany;
        private TextView textViewAddress;
        private TextView textViewEmail;
        private TextView textViewPhone;
        private TextView textViewEmployees;

        public WorkspaceViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCompany = itemView.findViewById(R.id.textViewCompany);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewEmployees = itemView.findViewById(R.id.textViewEmployees);
        }

        public void bind(Workspace workspace) {
            textViewCompany.setText(workspace.getCompany());
            textViewAddress.setText(workspace.getAddress());
            textViewEmail.setText(workspace.getEmail());
            textViewPhone.setText(workspace.getPhone());
            textViewEmployees.setText(workspace.getEmployees());
        }
    }
}

