package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyDBHandler dbHandler = new MyDBHandler(this);

        Intent backEnd = getIntent();

        int id = backEnd.getIntExtra("userId", 0);

        String name = backEnd.getStringExtra("username");

        String description = backEnd.getStringExtra("userDesc");

        boolean followed = backEnd.getBooleanExtra("userFollowed", false);

        User viewUser = new User(id, description, name, followed);

        TextView tvName = findViewById(R.id.tvName);

        TextView tvDescription = findViewById(R.id.tvDescription);

        Button btnfollow = findViewById(R.id.btnFollow);

        Button btnMessage = findViewById(R.id.btnMessage);

        tvName.setText(name);

        tvDescription.setText(description);

        if (followed){
            btnfollow.setText("Unfollow");
        }
        else{
            btnfollow.setText("Follow");
        }

        btnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewUser.getFollowed()) {
                    btnfollow.setText("Follow");

                    viewUser.setFollowed(false);

                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();

                    dbHandler.updateUser(viewUser);
                }

                else if (!viewUser.getFollowed()){
                    btnfollow.setText("Unfollow");

                    viewUser.setFollowed(true);

                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();

                    dbHandler.updateUser(viewUser);
                }
            }
        });
    }
}
