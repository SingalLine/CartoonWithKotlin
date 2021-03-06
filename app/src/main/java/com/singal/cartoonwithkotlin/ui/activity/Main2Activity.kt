package com.singal.cartoonwithkotlin.ui.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.singal.cartoonwithkotlin.R
import com.singal.cartoonwithkotlin.ui.adapter.ContentPagerAdapter
import com.singal.cartoonwithkotlin.ui.fragment.BookFragment
import com.singal.cartoonwithkotlin.ui.fragment.HomeFragment
import com.singal.cartoonwithkotlin.ui.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.ArrayList

class Main2Activity : AppCompatActivity() {

    companion object {
        val GITHUB_URL = "https://github.com"
    }

    val nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { jump2MyGithub() }

        val fragments = ArrayList<Fragment>()


        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(NewsFragment())


        val nameList = nameResList.map(this::getString)

        viewPager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        viewPager.offscreenPageLimit = 2

        tabLayout.setupWithViewPager(viewPager)
    }

    private fun jump2MyGithub() {
        val uri = Uri.parse(GITHUB_URL);
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_about) {
            val intent = Intent(this, AboutActivity().javaClass)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
