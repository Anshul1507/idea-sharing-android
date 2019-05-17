package tech.dsckiet.ideasharing;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private FrameLayout mContainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    replaceFragment(new DashboardFragement());
                    break;
                case R.id.navigation_info:
                    replaceFragment(new InfoFragment());
                    break;
                    default: return false;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = findViewById(R.id.fragment_container);
        mContainer.setVisibility(View.VISIBLE);
//        defaultFragment();
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        replaceFragment(new DashboardFragement());
//        getSupportFragmentManager()
//                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                    @Override
//                    public void onBackStackChanged() {
//                        MainActivity.this.updateBottomNavigationTitle(MainActivity.this.getSupportFragmentManager().findFragmentById(R.id.fragment_container));
//                    }
//                });
    }
    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getSimpleName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            // fragment not in back stack, create it
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, backStateName);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }
//    private void defaultFragment() {
//        replaceFragment(new DashboardFragement());
//        navigation.getMenu().getItem(0).setChecked(true);
//    }
    public void updateBottomNavigationTitle(Fragment f) {
        String className = f.getClass().getSimpleName();

        if (className.equals(DashboardFragement.class.getSimpleName()))
            navigation.getMenu().getItem(0).setChecked(true);

    }
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public void onBackPressed() {
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//        Log.e("qwer", count + "");
//        if (count == 1) {
//            this.finishAffinity();
//        } else {
//            super.onBackPressed();
//        }
//    }

}
