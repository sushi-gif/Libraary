package space.nullpointerexception.libraary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import space.nullpointerexception.libraary.DataUtils.BooksManager;
import space.nullpointerexception.libraary.InternetServices.ClientConnection;
import space.nullpointerexception.libraary.Fragments.HomeFragment;
import space.nullpointerexception.libraary.R;
import space.nullpointerexception.libraary.Fragments.ShopFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClientConnection clientConnection = new ClientConnection();
        clientConnection.start();

        while(!clientConnection.isReadable()){}

        BooksManager.getInstance().readLatestStatus(this);

        setFragment(new HomeFragment());
        initBottomBar();
    }

    private void initBottomBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_activity_bottom_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            if ((item.getItemId() == R.id.shop)) {
                setFragment(new ShopFragment());
            } else {
                setFragment(new HomeFragment());
            }

            return true;
        });

    }

    private void setFragment(Fragment newFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_wrapper,newFragment).commit();
    }

    @Override
    protected void onStop() {
        BooksManager.getInstance().saveStatus(this);
        super.onStop();
    }
}