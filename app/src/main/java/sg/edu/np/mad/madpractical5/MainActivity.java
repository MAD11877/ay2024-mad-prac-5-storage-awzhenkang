package sg.edu.np.mad.madpractical5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserAdapter userAdapter;
    private MyDBHandler dbHandler;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database helper
        dbHandler = new MyDBHandler(this);

        // Fetch the user list from the database
        userList = dbHandler.getUsers();

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the adapter and handle the Follow/Unfollow button click
        userAdapter = new UserAdapter(userList, user -> {
            // Update the user's follow status
            user.setFollowed(!user.getFollowed());
            dbHandler.updateUser(user);  // Update the user in the database
            userAdapter.notifyItemChanged(userList.indexOf(user));  // Notify the adapter of the change
        });
        recyclerView.setAdapter(userAdapter);
    }
}
