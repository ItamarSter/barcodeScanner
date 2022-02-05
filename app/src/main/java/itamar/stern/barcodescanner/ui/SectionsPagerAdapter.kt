package itamar.stern.barcodescanner.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fa: FragmentActivity) :
    FragmentStateAdapter(fa) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ScanFragment()
            }
            1 -> {
                HistoryFragment()
            }
            else -> throw IllegalArgumentException("No such fragment")
        }
    }
}