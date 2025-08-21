package com.s23010486.easyjob;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<JobPost> myJobPosts = new ArrayList<>();
    private JobPostAdapter jobPostAdapter;
    private CardView postCreationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Initialize views
        TextView tvUserName = findViewById(R.id.tvUserName);
        CardView cardHirePeople = findViewById(R.id.HirePeople);
        CardView cardFindJobs = findViewById(R.id.cardFindJobs);
        FloatingActionButton fabChatBot = findViewById(R.id.fabChatBot);
        View chatOverlay = findViewById(R.id.chatOverlay);
        Button btnViewProfile = findViewById(R.id.btnViewProfile);
        Button btnCreatePost = findViewById(R.id.btnCreatePost);
        postCreationDialog = findViewById(R.id.postCreationDialog);

        // Initialize RecyclerView for job posts
        RecyclerView rvMyPosts = findViewById(R.id.rvMyPosts);
        rvMyPosts.setLayoutManager(new LinearLayoutManager(this));
        jobPostAdapter = new JobPostAdapter(myJobPosts, this);
        rvMyPosts.setAdapter(jobPostAdapter);

        // Set user name
        tvUserName.setText("John Doe");

        // Set click listeners
        cardHirePeople.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, HirePeopleActivity.class));
        });

        cardFindJobs.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, FindJobActivity.class));
        });

        btnViewProfile.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        });

        // FAB chat button
        fabChatBot.setOnClickListener(v -> {
            chatOverlay.setVisibility(View.VISIBLE);
        });

        // Bottom navigation click handlers
        findViewById(R.id.navHome).setOnClickListener(v -> {
            // Already in home
        });

        findViewById(R.id.navPost).setOnClickListener(v -> {
            showPostCreationDialog();
        });

        findViewById(R.id.navJob).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, FindJobActivity.class));
        });

        findViewById(R.id.navSettings).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, SettingActivity.class));
        });

        // Create post button
        btnCreatePost.setOnClickListener(v -> {
            showPostCreationDialog();
        });

        // Post creation dialog buttons
        Button btnCancelPost = findViewById(R.id.btnCancelPost);
        Button btnSubmitPost = findViewById(R.id.btnSubmitPost);
        EditText etPostTitle = findViewById(R.id.etPostTitle);
        EditText etPostDescription = findViewById(R.id.etPostDescription);
        EditText etPostSalary = findViewById(R.id.etPostSalary);

        btnCancelPost.setOnClickListener(v -> {
            hidePostCreationDialog();
        });

        btnSubmitPost.setOnClickListener(v -> {
            String title = etPostTitle.getText().toString().trim();
            String description = etPostDescription.getText().toString().trim();
            String salary = etPostSalary.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create new job post
            JobPost newPost = new JobPost(title, description, salary);
            myJobPosts.add(0, newPost); // Add to beginning of list
            jobPostAdapter.notifyItemInserted(0);

            // Clear fields and hide dialog
            etPostTitle.setText("");
            etPostDescription.setText("");
            etPostSalary.setText("");
            hidePostCreationDialog();

            Toast.makeText(this, "Job post created successfully", Toast.LENGTH_SHORT).show();
        });

        // Chat overlay close button


        chatOverlay.setOnClickListener(v -> chatOverlay.setVisibility(View.GONE));
    }

    private void showPostCreationDialog() {
        postCreationDialog.setVisibility(View.VISIBLE);
    }

    private void hidePostCreationDialog() {
        postCreationDialog.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (postCreationDialog.getVisibility() == View.VISIBLE) {
            hidePostCreationDialog();
            return;
        }

        View chatOverlay = findViewById(R.id.chatOverlay);
        if (chatOverlay.getVisibility() == View.VISIBLE) {
            chatOverlay.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    // JobPost model class
    public static class JobPost {
        private String title;
        private String description;
        private String salary;

        public JobPost(String title, String description, String salary) {
            this.title = title;
            this.description = description;
            this.salary = salary;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getSalary() { return salary; }
    }

    // JobPostAdapter class
    public static class JobPostAdapter extends RecyclerView.Adapter<JobPostAdapter.ViewHolder> {
        private List<JobPost> jobPosts;
        private HomeActivity context;

        public JobPostAdapter(List<JobPost> jobPosts, HomeActivity context) {
            this.jobPosts = jobPosts;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_job_post, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            JobPost post = jobPosts.get(position);
            holder.tvTitle.setText(post.getTitle());
            holder.tvDescription.setText(post.getDescription());
            holder.tvSalary.setText(post.getSalary());

            holder.btnDelete.setOnClickListener(v -> {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    jobPosts.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                    Toast.makeText(context, "Post deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return jobPosts.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDescription, tvSalary;
            Button btnDelete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvPostTitle);
                tvDescription = itemView.findViewById(R.id.tvPostDescription);
                tvSalary = itemView.findViewById(R.id.tvPostSalary);
                btnDelete = itemView.findViewById(R.id.btnDeletePost);
            }
        }
    }
}