package com.example.ferquies.todoapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.View
import com.example.ferquies.todoapp.R
import com.example.ferquies.todoapp.base.BaseActivity
import com.example.ferquies.todoapp.base.inTransaction
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.generic_activity.root
import kotlinx.android.synthetic.main.generic_activity.toolbar
import javax.inject.Inject

class DetailActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.generic_activity)

        if (savedInstanceState?.get(TODO_DETAIL_ARG) != null) {
            intent.putExtra(TODO_DETAIL_ARG, savedInstanceState.get(TODO_DETAIL_ARG) as Int)
        }

        initializeView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(TODO_DETAIL_ARG, obtainTodoId())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        intent.putExtra(TODO_DETAIL_ARG, savedInstanceState?.get(TODO_DETAIL_ARG) as Int)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    private fun initializeView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.inTransaction {
            replace(R.id.container, DetailFragment.newInstance(obtainTodoId()))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getRootView(): View = root

    private fun obtainTodoId() = intent.extras.getInt(TODO_DETAIL_ARG)

    companion object {
        private const val TODO_DETAIL_ARG = "todo_id"

        fun newIntent(context: Context, taskId: Int = -1): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(TODO_DETAIL_ARG, taskId)
            return intent
        }
    }
}
