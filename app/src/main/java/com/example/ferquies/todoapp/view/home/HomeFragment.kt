package com.example.ferquies.todoapp.view.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ferquies.todoapp.R
import com.example.ferquies.todoapp.base.BaseFragment
import com.example.ferquies.todoapp.base.getViewModel
import com.example.ferquies.todoapp.domain.database.Todo
import com.example.ferquies.todoapp.domain.home.HomeNavigation
import com.example.ferquies.todoapp.domain.home.HomeViewState
import com.example.ferquies.todoapp.view.detail.DetailActivity
import com.example.ferquies.todoapp.view.home.adapter.TodoListAdapter
import kotlinx.android.synthetic.main.fragment_home.addTodoButton
import kotlinx.android.synthetic.main.fragment_home.noTodos
import kotlinx.android.synthetic.main.fragment_home.todoList
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
class HomeFragment : BaseFragment(), TodoListAdapter.Callback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var applicationContext: Context

    @Inject
    lateinit var adapter: TodoListAdapter

    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        viewModel = getViewModel(HomeFragmentViewModel::class.java, viewModelFactory)

        viewModel.getViewState().observe(this, Observer {
            render(it!!)
        })

        viewModel.navigationAction.observe(this, Observer {
            when (it) {
                is HomeNavigation.AddTodo -> navigateToAddTravel()
                is HomeNavigation.Detail -> navigateToTravelDetail(it.todoId)
            }
        })

        todoList.layoutManager = LinearLayoutManager(activity)
        todoList.adapter = adapter

        hookEvents()
    }

    private fun render(viewState: HomeViewState) {
        when (viewState.isEmptyList) {
            true -> {
                noTodos.visibility = View.VISIBLE
                todoList.visibility = View.GONE
            }
            false -> {
                noTodos.visibility = View.GONE
                todoList.visibility = View.VISIBLE
            }
        }

        adapter.todoList = viewState.todos
    }

    private fun hookEvents() {
        addTodoButton.setOnClickListener {
            viewModel.onAddClick()
        }
    }

    private fun navigateToAddTravel() {
        startActivity(DetailActivity.newIntent(applicationContext))
    }

    private fun navigateToTravelDetail(travelId: Int) {
        startActivity(DetailActivity.newIntent(applicationContext, travelId))
    }

    override fun onItemClick(todo: Todo) {
        viewModel.onTodoItemClick(todo.id)
    }

    override fun onItemLongClick(todo: Todo): Boolean {
        viewModel.onItemLongClick(todo)
        return true
    }

    companion object {
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}
