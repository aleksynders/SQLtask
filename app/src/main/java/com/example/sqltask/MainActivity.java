package com.example.sqltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqltask.Adapter.AdapterDirectory;
import com.example.sqltask.Models.Directory;
import com.example.sqltask.network.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Connection connection;
    List<Directory> data;
    ListView listView;
    AdapterDirectory pAdapter;
    EditText search;

    TextView IDforDo;

    String errorMessage = "";

    ImageButton ImgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetDataFromSql();

        ImgButton = findViewById(R.id.imageButton2);
        ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


        search = findViewById(R.id.txtSearch);
        search.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                data = new ArrayList<Directory>();
                listView = findViewById(R.id.lvData);
                pAdapter = new AdapterDirectory(MainActivity.this, data);
                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connection = connectionHelper.connectionClass();
                    if (connection != null) {
                        String query = "SELECT ID,Title,Number,Mail\n" +
                                "FROM DirectoryList"
                                + " WHERE Title LIKE "  + "'%" + search.getText().toString()+ "%'";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);

                        while (resultSet.next()) {
                            Directory tempDirectory = new Directory
                                    (   resultSet.getInt("ID"),
                                            resultSet.getString("Title"),
                                            resultSet.getString("Number"),
                                            resultSet.getString("Mail")
                                    );
                            data.add(tempDirectory);
                        }
                        connection.close();
                    } else {
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                pAdapter.notifyDataSetInvalidated();
                listView.setAdapter(pAdapter);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    public void GetDataFromSql() {
        data = new ArrayList<Directory>();
        listView = findViewById(R.id.lvData);
        pAdapter = new AdapterDirectory(MainActivity.this, data);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "SELECT ID,Title,Number,Mail\n" +
                        "FROM DirectoryList";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Directory tempDirectory = new Directory
                            (   resultSet.getInt("ID"),
                                    resultSet.getString("Title"),
                                    resultSet.getString("Number"),
                                    resultSet.getString("Mail")
                            );
                    data.add(tempDirectory);
                }
                connection.close();
            } else {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);



    }

    public void deleteSQL(View v) {
        IDforDo = findViewById(R.id.txtIdForDeleteOrChange);
            try {
                ConnectionHelper ch = new ConnectionHelper();
                connection = ch.connectionClass();
                if (connection != null) {
                    String query = "DELETE FROM DirectoryList WHERE ID = " + IDforDo.getText();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    Toast.makeText(this, "Организация удалена!", Toast.LENGTH_LONG).show();
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                } else {
                    errorMessage = "Попробуйте еще раз...";
                }
            } catch (Exception ex) {
                errorMessage = "Check Connection!";
            }
    }
}