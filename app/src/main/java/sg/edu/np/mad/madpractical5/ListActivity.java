package sg.edu.np.mad.madpractical5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Set edge-to-edge using WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create a list of 20 random users
        ArrayList<User> userList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int id = i + 1;  // Incremental ID
            String name = "Name " + random.nextInt(999999999);
            String description = "Description " + random.nextInt(999999999);
            boolean followed = random.nextBoolean();

            User user = new User(id, name, description, followed);
            userList.add(user);
        }

        // Initialize RecyclerView
        userAdapter = new UserAdapter(userList, user -> {
            // Handle follow/unfollow button click
            user.setFollowed(!user.getFollowed());
            userAdapter.notifyItemChanged(userList.indexOf(user));  // Notify the adapter of the change
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);
    }
}
