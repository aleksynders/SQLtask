package com.example.sqltask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetDataFromSql();
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
}