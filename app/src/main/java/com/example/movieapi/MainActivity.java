package com.example.movieapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.movieapi.adapter.pageAdapter;
import com.example.movieapi.nav.homeFragment;
import com.example.movieapi.nav.profileFragment;
import com.example.movieapi.nav.searchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private FirebaseUser firebaseUser;
    private TextView textName, btnLogout;

    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textName = findViewById(R.id.name);
        btnLogout = findViewById(R.id.btn_logout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

//
//

        if(firebaseUser!=null) {
            textName.setText(firebaseUser.getDisplayName());
        }else{
            textName.setText("Login Gagal");
        }

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
//        --
        ViewPager2 viewPager2=findViewById(R.id.viewPager2);
        BottomNavigationView bottomNavigationView=findViewById(R.id.navigation);
        fragmentArrayList.add(new homeFragment());
        fragmentArrayList.add(new searchFragment());
        fragmentArrayList.add(new profileFragment());
        pageAdapter pageAdapter=new pageAdapter(this, fragmentArrayList);
        viewPager2.setAdapter(pageAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.frHome);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.frSearch);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.frProfile);
                        break;
                }
                super.onPageSelected(position);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.frHome:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.frSearch:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.frProfile:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
//        //bottom navigation
//        loadFragment(new homeFragment());
//        BottomNavigationView bottomNavigationView=findViewById(R.id.navigation);
//        ViewPager viewPager=findViewById(R.id.page);
//        PagerAdapter adapter=new pageAdapter(getSupportFragmentManager(),
//                bottomNavigationView.getMaxItemCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new BottomNavigationView());
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.option,menu);
            return true;
        }

        public boolean onOptionItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.download){
            startActivity(new Intent(this, activity_download.class));
        } else if (item.getItemId() == R.id.setting){
            startActivity(new Intent(this, activity_setting.class));
        } else if (item.getItemId() == R.id.about){
            startActivity(new Intent(this, activity_about.class));
        }
        return true;
        }
//    private boolean loadFragment(Fragment fragment) {
//        if (fragment != null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.frHome,fragment).commit();
//            return true;
//        }
//        return false;
}

