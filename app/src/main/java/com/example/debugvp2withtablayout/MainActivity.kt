package com.example.debugvp2withtablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tab)
        val vp2 = findViewById<ViewPager2>(R.id.vp2)

        vp2.adapter = object: FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 5
            }

            override fun createFragment(position: Int): Fragment {
                return TestFragment()
            }
        }

        TabLayoutMediator(tabLayout, vp2) { tab, position ->
            tab.text = "Tab: $position"
        }.attach()
    }

}

class TestFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_test, container, false)
        val loading = root.findViewById<TextView>(R.id.loading)
        val content = root.findViewById<TextView>(R.id.tv)
        Thread {
            Thread.sleep(2000)
            root.post {
                loading.visibility = View.GONE
                content.visibility = View.VISIBLE
            }
        }.start()
        return root
    }
}