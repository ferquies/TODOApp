package com.example.ferquies.todoapp.view.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.ferquies.todoapp.base.ActionLiveData
import com.example.ferquies.todoapp.data.Repository
import com.example.ferquies.todoapp.domain.database.Todo
import com.example.ferquies.todoapp.domain.home.HomeNavigation
import com.example.ferquies.todoapp.domain.home.HomeViewState
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
class HomeFragmentViewModel @Inject constructor(private val repository: Repository) :
        ViewModel() {

    private val viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val navigationAction = ActionLiveData<HomeNavigation>()

    init {
        viewState.value = HomeViewState()
    }

    fun onAddClick() {
        navigationAction.sendAction(HomeNavigation.AddTodo())
    }

    fun onTodoItemClick(todoId: Int) {
        navigationAction.sendAction(HomeNavigation.Detail(todoId))
    }

    fun onItemDelete(todo: Todo) {
        repository.deleteTask(todo)
    }

    fun getViewState(): LiveData<HomeViewState> = Transformations.switchMap(
            repository.getTasks(0), this::updateViewState)

    private fun getCurrentViewState() = viewState.value!!

    private fun updateViewState(tasksList: List<Todo>?): LiveData<HomeViewState> {
        if (getCurrentViewState().todos != tasksList) {
            val isEmptyList = tasksList?.isEmpty() ?: true
            viewState.value = getCurrentViewState().copy(isEmptyList = isEmptyList,
                    todos = tasksList ?: ArrayList())
        }

        return viewState
    }

    fun onTaskNextState(todo: Todo) {
        repository.updateTask(todo.copy(status = 1))
    }

    fun changePosition(tasks: List<Todo>) {
        repository.changeOrder(tasks)
    }
}