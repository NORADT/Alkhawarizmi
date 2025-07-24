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
import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.database.SqlLiteStatements;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private int CHAR = 4;

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;

    private TextView haveAcc;
    private Button register;

    SqlLiteStatements sqlLiteStatements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        haveAcc = findViewById(R.id.haveAcc);
        haveAcc.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register:
                inputsValidation();
                break;

            case R.id.haveAcc:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
        }

    }

    private void inputsValidation() {
        if (TextUtils.isEmpty(name.getText().toString().trim())) {
            name.setError("حقل مطلوب");
            return;
        }
        if (TextUtils.isEmpty(email.getText().toString().trim())) {
            email.setError("حقل مطلوب");
            return;
        }

        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            password.setError("حقل مطلوب");
            return;
        }

        if (password.getText().toString().length() < CHAR) {
            if (!TextUtils.isEmpty(password.getText().toString())) {
                password.setError("كلمه المرور لابد ان تتكون من 4 حروف او اكثر");
                return;
            }
        }

        if (TextUtils.isEmpty(confirmPassword.getText().toString())) {
            confirmPassword.setError("حقل مطلوب");
            return;
        }

        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("كلمه المرور لا تتطابق مع تاكيد كلمه المرور");
            return;
        }

        register();
    }

    private void register() {

        AppUser user = new AppUser(name.getText().toString().trim(),
                email.getText().toString().trim(),
                password.getText().toString().trim());

        SqlLiteStatements sqlLiteStatements = new SqlLiteStatements(RegisterActivity.this);

        if (sqlLiteStatements.userValidation(user.getEmail())) {
            Toast.makeText(RegisterActivity.this, "البريد الإلكتروني موجود بالفعل", Toast.LENGTH_LONG).show();
        } else {
            sqlLiteStatements.newUser(user);

            Toast.makeText(RegisterActivity.this,
                    "تم انشاء الحساب بنجاح", Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
    }
}
