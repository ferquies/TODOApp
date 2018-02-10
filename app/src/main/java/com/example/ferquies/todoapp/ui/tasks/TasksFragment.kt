package com.example.ferquies.todoapp.ui.tasks

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ferquies.todoapp.R
import com.example.ferquies.todoapp.base.BaseFragment
import com.example.ferquies.todoapp.base.getViewModel
import com.example.ferquies.todoapp.domain.database.Todo
import com.example.ferquies.todoapp.domain.task.TasksNavigation
import com.example.ferquies.todoapp.domain.task.TasksViewState
import com.example.ferquies.todoapp.ui.detail.DetailActivity
import com.example.ferquies.todoapp.ui.tasks.adapter.TaskListAdapter
import kotlinx.android.synthetic.main.fragment_home.noTodos
import kotlinx.android.synthetic.main.fragment_home.todoList
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
class TasksFragment : BaseFragment(), TaskListAdapter.Callback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var applicationContext: Context

    @Inject
    lateinit var adapter: TaskListAdapter

    @Inject
    lateinit var touchHelper: ItemTouchHelper

    private lateinit var viewModel: TasksFragmentViewModel
    private var isUndo = false

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
        viewModel = getViewModel(TasksFragmentViewModel::class.java, viewModelFactory)

        viewModel.getViewState(obtainStatus()).observe(this, Observer {
            render(it!!)
        })

        viewModel.navigationAction.observe(this, Observer {
            when (it) {
                is TasksNavigation.Detail -> navigateToTaskDetail(it.taskId)
            }
        })

        todoList.layoutManager = LinearLayoutManager(activity)
        todoList.adapter = adapter
        touchHelper.attachToRecyclerView(todoList)
    }

    private fun render(viewState: TasksViewState) {
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

        adapter.setItems(viewState.tasks)
    }

    private fun navigateToTaskDetail(taskId: Int) {
        startActivity(DetailActivity.newIntent(applicationContext, taskId))
    }

    override fun onItemClick(todo: Todo) {
        viewModel.onTodoItemClick(todo.id)
    }

    override fun onItemDismiss(todo: Todo, position: Int): Boolean {
        val snackbar = Snackbar.make(baseActivity!!.getRootView(), "Task removed",
                Snackbar.LENGTH_LONG)
        snackbar.setAction("UNDO", {
            isUndo = true
            adapter.onItemRestored(todo, position)
        })
        snackbar.setActionTextColor(Color.YELLOW)
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                if (!isUndo) {
                    viewModel.onItemDelete(todo)
                }

                isUndo = false
            }
        })
        snackbar.show()

        return true
    }

    override fun onItemNextState(todo: Todo): Boolean {
        viewModel.onTaskNextState(todo)
        return true
    }

    override fun onItemMoved(tasks: List<Todo>) {
        viewModel.changePosition(tasks)
    }

    private fun obtainStatus() = arguments.getInt(STATUS_ARG)

    companion object {
        private const val STATUS_ARG = "status_arg"

        fun newInstance(status: Int): Fragment {
            val fragment = TasksFragment()
            val bundle = Bundle()
            bundle.putInt(STATUS_ARG, status)
            fragment.arguments = bundle
            return fragment
        }
    }
}
