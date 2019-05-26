package com.example.medicinealertapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.User.LogoutActivity;
import com.example.medicinealertapplication.User.SetTimeToEatActivity;
import com.example.medicinealertapplication.User.User;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

public class HomeActivity extends AppCompatActivity {
    ImageView viewAllMed;
    ImageView viewYourMed;
    ImageView viewTimeMed;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        viewAllMed = (ImageView) findViewById(R.id.viewAllMed);
        viewYourMed = (ImageView) findViewById(R.id.viewYourMed);
        viewTimeMed = (ImageView) findViewById(R.id.viewTimeMed);

        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        navigationView = findViewById(R.id.bottom_nav);

        viewAllMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), AllMedActivity.class);
//                Intent mainIntent = new Intent(HomeActivity.this, AllMedActivity.class);
                editIntent.putExtra("idUser", userID);
                editIntent.putExtra("nameUser", userName);
                editIntent.putExtra("passUser", userPass);
                editIntent.putExtra("mornUser", userMorning);
                editIntent.putExtra("afterUser", userAfter);
                editIntent.putExtra("evenUser", userEven);
                startActivity(editIntent);
            }
        });

        viewYourMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), YourMedicineActivity.class);
                editIntent.putExtra("idUser", userID);
                editIntent.putExtra("nameUser", userName);
                editIntent.putExtra("passUser", userPass);
                editIntent.putExtra("mornUser", userMorning);
                editIntent.putExtra("afterUser", userAfter);
                editIntent.putExtra("evenUser", userEven);
                startActivity(editIntent);
            }
        });

        viewTimeMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), TimeMedActivity.class);
                editIntent.putExtra("idUser", userID);
                editIntent.putExtra("nameUser", userName);
                editIntent.putExtra("passUser", userPass);
                editIntent.putExtra("mornUser", userMorning);
                editIntent.putExtra("afterUser", userAfter);
                editIntent.putExtra("evenUser", userEven);
                startActivity(editIntent);

            }
        });


        final HomeFragment homeFragment=new HomeFragment();
        final KnowFragment knowFragment = new KnowFragment();
        final AccountFragment accountFragment = new AccountFragment();
        final TimeFragment timeFragment = new TimeFragment();



//
//        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
//
////                int id = menuItem.getItemId();
////                Fragment selectedFragment = null;
//                switch (menuItem.getItemId()){
//
//                    case R.id.home:
//                        Intent HomePage = new Intent(HomeActivity.this, HomeActivity.class);
//                        HomePage.putExtra("idUser",userID);
//                        HomePage.putExtra("nameUser",userName);
//                        HomePage.putExtra("passUser",userPass);
//                        HomePage.putExtra("mornUser",userMorning);
//                        HomePage.putExtra("afterUser",userAfter);
//                        HomePage.putExtra("evenUser",userEven);
//                        startActivity(HomePage);
//                        break;
//                    case R.id.know:
//                        Intent HomePage2 = new Intent(HomeActivity.this, KnowledgeActivity.class);
//                        HomePage2.putExtra("idUser",userID);
//                        HomePage2.putExtra("nameUser",userName);
//                        HomePage2.putExtra("passUser",userPass);
//                        HomePage2.putExtra("mornUser",userMorning);
//                        HomePage2.putExtra("afterUser",userAfter);
//                        HomePage2.putExtra("evenUser",userEven);
//                        startActivity(HomePage2);
//                        break;
//                    case R.id.timer:
//                        Intent HomePage3 = new Intent(HomeActivity.this, TimeMedActivity.class);
//                        HomePage3.putExtra("idUser",userID);
//                        HomePage3.putExtra("nameUser",userName);
//                        HomePage3.putExtra("passUser",userPass);
//                        HomePage3.putExtra("mornUser",userMorning);
//                        HomePage3.putExtra("afterUser",userAfter);
//                        HomePage3.putExtra("evenUser",userEven);
//                        startActivity(HomePage3);
//                        break;
//                    case R.id.account:
//                        Intent HomePage4 = new Intent(HomeActivity.this, LogoutActivity.class);
//                        HomePage4.putExtra("idUser",userID);
//                        HomePage4.putExtra("nameUser",userName);
//                        HomePage4.putExtra("passUser",userPass);
//                        HomePage4.putExtra("mornUser",userMorning);
//                        HomePage4.putExtra("afterUser",userAfter);
//                        HomePage4.putExtra("evenUser",userEven);
//                        startActivity(HomePage4);
//                        break;
//                }
//
//            }
//        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(HomeActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser", userID);
                    HomePage.putExtra("nameUser", userName);
                    HomePage.putExtra("passUser", userPass);
                    HomePage.putExtra("mornUser", userMorning);
                    HomePage.putExtra("afterUser", userAfter);
                    HomePage.putExtra("evenUser", userEven);
                    startActivity(HomePage);
                    return true;
                }else if (id == R.id.know) {
                    Intent HomePage2 = new Intent(HomeActivity.this, KnowledgeActivity.class);
                    HomePage2.putExtra("idUser", userID);
                    HomePage2.putExtra("nameUser", userName);
                    HomePage2.putExtra("passUser", userPass);
                    HomePage2.putExtra("mornUser", userMorning);
                    HomePage2.putExtra("afterUser", userAfter);
                    HomePage2.putExtra("evenUser", userEven);
                    startActivity(HomePage2);
                    return true;
                }else if (id == R.id.timer) {
                    Intent HomePage3 = new Intent(HomeActivity.this, TimeMedActivity.class);
                    HomePage3.putExtra("idUser", userID);
                    HomePage3.putExtra("nameUser", userName);
                    HomePage3.putExtra("passUser", userPass);
                    HomePage3.putExtra("mornUser", userMorning);
                    HomePage3.putExtra("afterUser", userAfter);
                    HomePage3.putExtra("evenUser", userEven);
                    startActivity(HomePage3);
                    return true;
                }else if (id == R.id.account) {
                    Intent HomePage4 = new Intent(HomeActivity.this, LogoutActivity.class);
                    HomePage4.putExtra("idUser", userID);
                    HomePage4.putExtra("nameUser", userName);
                    HomePage4.putExtra("passUser", userPass);
                    HomePage4.putExtra("mornUser", userMorning);
                    HomePage4.putExtra("afterUser", userAfter);
                    HomePage4.putExtra("evenUser", userEven);
                    startActivity(HomePage4);
                    return true;
                }
                return false;
            }
        });
//        navigationView.setSelectedItemId(R.id.home);

    }

//    private BottomNavigationView.OnNavigationItemSelectedListener a = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
////            Fragment selectedFragment = null;
//            switch (menuItem.getItemId()) {
//                case R.id.home:
////                    selectedFragment = new HomeFragment();
//
//                    Intent HomePage = new Intent(HomeActivity.this, HomeActivity.class);
//                    HomePage.putExtra("idUser", userID);
//                    HomePage.putExtra("nameUser", userName);
//                    HomePage.putExtra("passUser", userPass);
//                    HomePage.putExtra("mornUser", userMorning);
//                    HomePage.putExtra("afterUser", userAfter);
//                    HomePage.putExtra("evenUser", userEven);
//                    startActivity(HomePage);
//                    return true;
//                case R.id.know:
//                    Intent HomePage2 = new Intent(HomeActivity.this, KnowledgeActivity.class);
//                    HomePage2.putExtra("idUser", userID);
//                    HomePage2.putExtra("nameUser", userName);
//                    HomePage2.putExtra("passUser", userPass);
//                    HomePage2.putExtra("mornUser", userMorning);
//                    HomePage2.putExtra("afterUser", userAfter);
//                    HomePage2.putExtra("evenUser", userEven);
//                    startActivity(HomePage2);
//                    return true;
//                case R.id.timer:
//                    Intent HomePage3 = new Intent(HomeActivity.this, TimeMedActivity.class);
//                    HomePage3.putExtra("idUser", userID);
//                    HomePage3.putExtra("nameUser", userName);
//                    HomePage3.putExtra("passUser", userPass);
//                    HomePage3.putExtra("mornUser", userMorning);
//                    HomePage3.putExtra("afterUser", userAfter);
//                    HomePage3.putExtra("evenUser", userEven);
//                    startActivity(HomePage3);
//                    return true;
//                case R.id.account:
//                    Intent HomePage4 = new Intent(HomeActivity.this, LogoutActivity.class);
//                    HomePage4.putExtra("idUser", userID);
//                    HomePage4.putExtra("nameUser", userName);
//                    HomePage4.putExtra("passUser", userPass);
//                    HomePage4.putExtra("mornUser", userMorning);
//                    HomePage4.putExtra("afterUser", userAfter);
//                    HomePage4.putExtra("evenUser", userEven);
//                    startActivity(HomePage4);
//                    return true;
//            }
////            getSupportFragmentManager().beginTransaction().replace(R.id.account,
////                    selectedFragment).commit();
////
//            return false;
//        }
//    };
//    private void setFragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.home, fragment);
//        fragmentTransaction.commit();
//    }


}
