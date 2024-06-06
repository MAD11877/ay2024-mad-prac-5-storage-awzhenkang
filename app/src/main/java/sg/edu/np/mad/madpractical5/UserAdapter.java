package sg.edu.np.mad.madpractical5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnFollowClickListener listener;

    public UserAdapter(List<User> userList, OnFollowClickListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.description.setText(user.getDescription());
        holder.followButton.setText(user.getFollowed() ? "Unfollow" : "Follow");

        holder.followButton.setOnClickListener(v -> listener.onFollowClick(user));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface OnFollowClickListener {
        void onFollowClick(User user);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView name, description;
        public Button followButton, messageButton;

        public UserViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            description = view.findViewById(R.id.tvDescription);
            followButton = view.findViewById(R.id.btnFollow);
            messageButton = view.findViewById(R.id.btnMessage);
        }
    }
}
