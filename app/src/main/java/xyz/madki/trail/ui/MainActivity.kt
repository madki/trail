package xyz.madki.trail.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import xyz.madki.trail.R
import xyz.madki.trail.ui.newslist.NewsListFragment


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragmentPopped = supportFragmentManager
                .popBackStackImmediate("NEWS_LIST_FRAGMENT", 0)
        if (!fragmentPopped && supportFragmentManager.findFragmentByTag("NEWS_LIST_FRAGMENT") == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment, NewsListFragment())
                    .addToBackStack("NEWS_LIST_FRAGMENT")
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
