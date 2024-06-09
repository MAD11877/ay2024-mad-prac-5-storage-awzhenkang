package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> userList;
    Context context;

    public UserAdapter(List<User> userList,Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View itemView = layoutInflater.inflate(R.layout.custom_activity_list, parent, false);

        return new UserViewHolder(itemView, userList);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.description.setText(user.getDescription());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public ImageView smallImage;

        public TextView name, description;

        public UserViewHolder(@NonNull View view, List<User> userList) {
            super(view);
            smallImage = view.findViewById(R.id.ivSmallImage);
            name = view.findViewById(R.id.tvName);
            description = view.findViewById(R.id.tvDescription);

            smallImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    User clickUser = userList.get(position);

                    Intent viewAccount = new Intent(smallImage.getContext(), AccountActivity.class);

                    int userId = clickUser.getId();

                    String username = clickUser.getName();

                    String description = clickUser.getDescription();

                    boolean followed = clickUser.getFollowed();

                    Log.i("followed", String.valueOf(followed));

                    viewAccount.putExtra("userId", userId);

                    viewAccount.putExtra("userName", username);

                    viewAccount.putExtra("userDesc", description);

                    viewAccount.putExtra("userFollowed", followed);

                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());

                    builder.setTitle("Profile");
                    builder.setMessage(username);
                    builder.setCancelable(false);

                    builder.setPositiveButton("View", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(itemView.getContext(), viewAccount, null);
                                }
                            });

                    builder.setNegativeButton("Close", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }
    }
}
