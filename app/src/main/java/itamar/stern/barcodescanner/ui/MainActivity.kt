package itamar.stern.barcodescanner.ui

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import itamar.stern.barcodescanner.R
import itamar.stern.barcodescanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        val tabsNames = arrayOf(applicationContext.resources.getString(R.string.scan_fragment),applicationContext.resources.getString(
            R.string.history_fragment
        ))
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = tabsNames[position]
        }.attach()

    }
}