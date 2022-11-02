package com.example.antinorms

import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity() {

    private val spinner: Spinner? = null
    private val paths = arrayOf("item 1", "item 2", "item 3")

    companion object {
        var token:String? = ""

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val firstFragment=Dasboard()
        val secondFragment = BlankFragment()
        val third = BlankFragment3()
        val fourth = Teams_Fragment()
        val fifth = group_fragment()

        setCurrentFragment(firstFragment)
        token = intent.getStringExtra("token")
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnItemSelectedListener {
            when(it.itemId){
                R.id.dashboard->setCurrentFragment(firstFragment)
                R.id.developer->setCurrentFragment(secondFragment)
                R.id.project->setCurrentFragment(third)
                R.id.teams->setCurrentFragment(fourth)
                R.id.groups->setCurrentFragment(fifth)

            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}
