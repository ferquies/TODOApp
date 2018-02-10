package com.example.ferquies.todoapp.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.example.ferquies.todoapp.R
import com.example.ferquies.todoapp.R.id
import com.example.ferquies.todoapp.R.string
import com.example.ferquies.todoapp.base.BaseActivity
import com.example.ferquies.todoapp.base.inTransaction
import com.example.ferquies.todoapp.ui.detail.DetailActivity
import com.example.ferquies.todoapp.ui.tasks.TasksFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.addTodoButton
import kotlinx.android.synthetic.main.activity_home.coordinatorLayout
import kotlinx.android.synthetic.main.activity_home.drawerLayout
import kotlinx.android.synthetic.main.activity_home.navigation
import kotlinx.android.synthetic.main.generic_activity.toolbar
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
class HomeActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initializeView()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initializeView() {
        initializeToolbar()
        hookEvents()
        hookMenuEvents()
        supportFragmentManager.inTransaction {
            replace(id.container,
                    TasksFragment.newInstance(0))
        }
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, string.navigation_drawer_open,
                string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun hookEvents() {
        addTodoButton.setOnClickListener {
            navigateToAddTask()
        }
    }

    private fun hookMenuEvents() {
        navigation.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.closeDrawers()

            when (it.itemId) {
                R.id.home -> navigateToHome()
                R.id.about -> navigateToAbout()
                else -> false
            }
        }
    }

    private fun navigateToHome(): Boolean {
        return true
    }

    private fun navigateToAbout(): Boolean {
        return true
    }

    private fun navigateToAddTask() {
        startActivity(DetailActivity.newIntent(applicationContext))
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun getRootView(): View = coordinatorLayout
}
