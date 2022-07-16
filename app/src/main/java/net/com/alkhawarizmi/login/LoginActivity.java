package net.com.alkhawarizmi.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.com.alkhawarizmi.models.AppUser;
import net.com.alkhawarizmi.HomeActivity;
import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.database.CacheHandler;
import net.com.alkhawarizmi.database.SqlLiteStatements;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText email;
    private EditText password;

    private TextView newAcc;
    private Button login;

    SqlLiteStatements sqlLiteStatements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        newAcc = findViewById(R.id.newAcc);
        newAcc.setOnClickListener(this);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login:
                inputsValidation();
                break;

            case R.id.newAcc:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }

    }

    private void inputsValidation() {
        if (TextUtils.isEmpty(email.getText().toString().trim())) {
            email.setError("حقل مطلوب");
            return;
        }

        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            password.setError("حقل مطلوب");
            return;
        }

        login();
    }

    private void login() {
        sqlLiteStatements = new SqlLiteStatements(this);
        String emailT = email.getText().toString().trim();
        String passwordT = password.getText().toString().trim();
        AppUser user = null;

        user = sqlLiteStatements.userLogin(emailT, passwordT);

        if (user == null) {
            Toast.makeText(LoginActivity.this,
                    "فشل تسجيل الدخول", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this,
                    "تم تسجيل الدخول بنجاح", Toast.LENGTH_LONG).show();

            CacheHandler cacheHandler = new CacheHandler(LoginActivity.this);
            cacheHandler.saveCurrentUser(user);
            cacheHandler.setLoggedIn(true);

            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }
}
