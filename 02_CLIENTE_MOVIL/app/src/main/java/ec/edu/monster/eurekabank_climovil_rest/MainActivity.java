package ec.edu.monster.eurekabank_climovil_rest;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import ec.edu.monster.eurekabank_climovil_rest.view.DepositoFragment;
import ec.edu.monster.eurekabank_climovil_rest.view.HomeFragment;
import ec.edu.monster.eurekabank_climovil_rest.view.MovimientoFragment;
import ec.edu.monster.eurekabank_climovil_rest.view.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new MovimientoFragment(), "Movimientos");
        adapter.addFragment(new DepositoFragment(), "Depósitos");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
