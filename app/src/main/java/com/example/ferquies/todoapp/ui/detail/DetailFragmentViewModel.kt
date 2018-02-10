package com.example.ferquies.todoapp.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.ferquies.todoapp.base.ActionLiveData
import com.example.ferquies.todoapp.data.Repository
import com.example.ferquies.todoapp.domain.database.Todo
import com.example.ferquies.todoapp.domain.detail.DetailNavigation
import com.example.ferquies.todoapp.domain.detail.DetailViewState
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
class DetailFragmentViewModel @Inject constructor(private val repository: Repository) :
        ViewModel() {

    private val viewState: MutableLiveData<DetailViewState> = MutableLiveData()
    var todoId: Int = -1
    val navigationAction = ActionLiveData<DetailNavigation>()

    init {
        viewState.value = DetailViewState()
    }

    fun getViewState(): LiveData<DetailViewState> = Transformations.switchMap(
            repository.getTask(todoId), this::updateViewState)

    fun saveTodo(title: String, detail: String) {
        repository.addTask(Todo(title = title, detail = detail))
        navigationAction.sendAction(DetailNavigation.Back())
    }

    fun updateTodo(todo: Todo) {
        repository.updateTask(todo)
        navigationAction.sendAction(DetailNavigation.Back())
    }

    private fun getCurrentViewState() = viewState.value!!

    private fun updateViewState(todo: Todo?): LiveData<DetailViewState> {
        val isEditing: Boolean = (todo != null)
        viewState.value = getCurrentViewState().copy(isEditing = isEditing,
                todo = todo ?: Todo())
        return viewState
    }
}