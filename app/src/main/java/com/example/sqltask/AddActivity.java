package com.example.sqltask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqltask.network.ConnectionHelper;

import java.sql.Connection;
import java.sql.Statement;

public class AddActivity extends AppCompatActivity {

    Connection connection;
    String errorMessage = "";

    EditText ID;
    EditText Title;
    EditText Number;
    EditText Mail;
    EditText Image;
    ImageButton imgBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ID = null;
        Title = findViewById(R.id.titleET);
        Number = findViewById(R.id.numberET);
        Mail = findViewById(R.id.emailET);
        Image = null;

        imgBTN = findViewById(R.id.addBTN);

    }


    @SuppressLint("SuspiciousIndentation")
    public void inputSQL(View v) {
        TextView Title = findViewById(R.id.titleET);
        TextView Number = findViewById(R.id.numberET);
        TextView Mail = findViewById(R.id.emailET);


        if(Number.getText().length() != 11)
            Toast.makeText(this, "Введите корректный номер телефона!", Toast.LENGTH_LONG).show();
        else{
            try {
                ConnectionHelper ch = new ConnectionHelper();
                connection = ch.connectionClass();
                if (connection != null) {
                    String query = "Insert into DirectoryList(Title, Mail, Number)" +
                            " Values('" + Title.getText() + "', '" + Mail.getText() + "', '" + Number.getText() + "')";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    Toast.makeText(this, "Организация добавлена!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    errorMessage = "Попробуйте еще раз.";
                }
            } catch (Exception ex) {
                errorMessage = "Check Connection!";
            }
        }
    }
}