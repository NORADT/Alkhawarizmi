package net.com.alkhawarizmi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.com.alkhawarizmi.database.CacheHandler;
import net.com.alkhawarizmi.database.SqlLiteStatements;
import net.com.alkhawarizmi.login.LoginActivity;
import net.com.alkhawarizmi.models.AppUser;

public class ProfileActivity extends AppCompatActivity{

    private static final String TAG = "ProfileActivity";

    private EditText id;
    private EditText name;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prflle);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        CacheHandler cacheHandler = new CacheHandler(this);
        AppUser user = cacheHandler.loadCurrentUser();
        name.setText(user.getName());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        id.setText(String.valueOf(user.getId()));
    }
}
