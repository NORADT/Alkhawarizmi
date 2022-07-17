package net.com.alkhawarizmi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.com.alkhawarizmi.database.SqlLiteStatements;
import net.com.alkhawarizmi.models.Reservation;

public class PaymentScreen extends AppCompatActivity {


    LinearLayout visaLayout;
    Button pay;
    TextView bill;
    EditText visaNo, visaCvv;

    SqlLiteStatements sqlLiteStatements;

    String total, userId, courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);

        sqlLiteStatements = new SqlLiteStatements(this);

        userId = getIntent().getExtras().getString("userId");
        courseId = getIntent().getExtras().getString("courseId");
        total = getIntent().getExtras().getString("total");

        visaLayout = findViewById(R.id.visaLayout);
        visaNo = findViewById(R.id.visaInput);
        visaCvv = findViewById(R.id.cvvInput);
        bill = findViewById(R.id.bill);
        bill.setText("إجمالي الفاتورة : " + total);

        pay = findViewById(R.id.payment);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
        if (TextUtils.isEmpty(visaNo.getText().toString().trim())) {
            Toast.makeText(PaymentScreen.this, "من فضلك ادخل رقم الفيزا", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(visaCvv.getText().toString().trim())) {
            Toast.makeText(PaymentScreen.this, "من فضلك ال ارقام ال Cvv في الفيزا", Toast.LENGTH_LONG).show();
            return;
        }

        Reservation reservation = new Reservation(userId, courseId);
        sqlLiteStatements.newReservation(reservation);

        Toast.makeText(PaymentScreen.this,
                "تم الدفع والحجز بنجاح", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(PaymentScreen.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
}
