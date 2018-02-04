package com.example.ferquies.todoapp.view.tasks

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.ferquies.todoapp.base.ActionLiveData
import com.example.ferquies.todoapp.data.Repository
import com.example.ferquies.todoapp.domain.database.Todo
import com.example.ferquies.todoapp.domain.home.TasksNavigation
import com.example.ferquies.todoapp.domain.home.TasksViewState
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/20/18
 */
class TasksFragmentViewModel @Inject constructor(private val repository: Repository) :
        ViewModel() {

    private val viewState: MutableLiveData<TasksViewState> = MutableLiveData()
    val navigationAction = ActionLiveData<TasksNavigation>()

    init {
        viewState.value = TasksViewState()
    }

    fun onAddClick() {
        navigationAction.sendAction(TasksNavigation.AddTask())
    }

    fun onTodoItemClick(todoId: Int) {
        navigationAction.sendAction(TasksNavigation.Detail(todoId))
    }

    fun onItemDelete(todo: Todo) {
        repository.deleteTask(todo)
    }

    fun getViewState(status: Int): LiveData<TasksViewState> = Transformations.switchMap(
            repository.getTasks(status), this::updateViewState)

    private fun getCurrentViewState() = viewState.value!!

    private fun updateViewState(tasksList: List<Todo>?): LiveData<TasksViewState> {
        if (getCurrentViewState().tasks != tasksList) {
            val isEmptyList = tasksList?.isEmpty() ?: true
            viewState.value = getCurrentViewState().copy(isEmptyList = isEmptyList,
                    tasks = tasksList ?: ArrayList())
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