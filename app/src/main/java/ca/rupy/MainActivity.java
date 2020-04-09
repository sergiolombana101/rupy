package ca.rupy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Hides title bar
        setContentView(R.layout.activity_main);

        /*Sets the toolbar created as the action bar the app is going to use*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view); // Reference to the navigation view
        /* ---------------------------------
            When an item from the navigation view is selected then the next method will be called
           ---------------------------------*/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ic_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new HomeFragment()).commit();
                        break;
                    case R.id.ic_calendar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new CalendarFragment()).commit();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START); // Close the nav bar after item is clicked
                return true;
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        /* ------------------------------------------
            Open the home fragment when the app first loads
           ------------------------------------------*/
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.ic_home);

    }

    @Override
    public void onBackPressed() {
        /* --------------------------------------------------------
           If user presses the back button and the the menu is being displayed
           then we want to close the menu and return to the main activity
           -------------------------------------------------------- */
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else { // If Menu is not open then we stick to the default
            super.onBackPressed();
        }

    }
}
