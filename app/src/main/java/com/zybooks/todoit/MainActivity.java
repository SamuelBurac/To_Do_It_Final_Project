package com.zybooks.todoit;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private TaskList mToDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mToDoList = new TaskList(this);
<!--Added necessary fragment navigation code to main, new file is going to be needed to setup the onclicklistener and make it navigate to the add task 
    view similar to the way the band app moves from the list to the detail fragment-->
      NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
              .findFragmentById(R.id.nav_host_fragment);

      if (navHostFragment != null) {
         NavController navController = navHostFragment.getNavController();
         AppBarConfiguration appBarConfig = new AppBarConfiguration.
                 Builder(navController.getGraph()).build();
         NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
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
            mToDoList.readFromFile();
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
            mToDoList.saveToFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clearButtonClick() {
        mToDoList.clear();
    }


    public void addButtonClick(View view) {
        System.out.println("Floating Action Button has been pressed!");

        //How to open Dialog box from here?
    }

   // @Override
    public void OnNewTaskClick(){

    }

}
