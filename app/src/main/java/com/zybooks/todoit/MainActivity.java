package com.zybooks.todoit;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.time.LocalDate;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {



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
        LocalDate cal = LocalDate.of(2022,6,6);
        LocalDate lac = LocalDate.of(2022,5,17);
        LocalDate lic = LocalDate.of(2022,3,11);
        Task Task = new Task(0,"monkey",cal);
        Task Task1 = new Task(1,"zebra",lac);
        Task Task2 = new Task(2,"giraffe",lic);
        Task Task3 = new Task(3,"monkey",cal);
        Task Task4 = new Task(4,"zebra",lac);
        Task Task5 = new Task(5,"go and wash dishes and even evvveeeem longer",lic);

        TaskList.getInstance(this).addTask(Task);
        TaskList.getInstance(this).addTask(Task1);
        TaskList.getInstance(this).addTask(Task2);
        TaskList.getInstance(this).addTask(Task3);
        TaskList.getInstance(this).addTask(Task4);
        TaskList.getInstance(this).addTask(Task5);

        try {
            TaskList.getInstance(this).saveToFile(); //after adding the tasks to the list had to save them to file
        } catch (IOException e) {
            e.printStackTrace();
        }



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
            TaskList.getInstance(this).readFromFile();
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
            TaskList.getInstance(this).saveToFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void fab(View view) {
        System.out.println("BUTTON HAS BEEN CLICKED");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.add_task_fragment);
    }


}

