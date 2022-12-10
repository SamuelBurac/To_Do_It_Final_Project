package com.zybooks.todoit;

import static android.content.ContentValues.TAG;




import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    //this is used to read and write data to the internal storage for some reason it didn't work otherwise
    public TaskList secondTList = new TaskList(this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




      NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
              .findFragmentById(R.id.nav_host_fragment);

      if (navHostFragment != null) {
         NavController navController = navHostFragment.getNavController();
         AppBarConfiguration appBarConfig = new AppBarConfiguration.
                 Builder(navController.getGraph()).build();
         NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);

      }
        //transferring tasks from second list to the singleton
        TaskList.getInstance(this).addTasks(secondTList.getTasks());

    }

   @Override
   public boolean onSupportNavigateUp() {
      NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
      return navController.navigateUp() || super.onSupportNavigateUp();
   }



    @Override
    protected void onResume() {
        super.onResume();

        try {
            // Attempt to load a previously saved list
            secondTList.readFromFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            // Save list for later
            secondTList.addTasks(TaskList.getInstance(this).getTasks());
            secondTList.saveToFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

