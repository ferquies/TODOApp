package com.example.ferquies.todoapp.ui.detail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ferquies.todoapp.R
import com.example.ferquies.todoapp.base.BaseFragment
import com.example.ferquies.todoapp.base.ViewModelFactory
import com.example.ferquies.todoapp.base.getViewModel
import com.example.ferquies.todoapp.domain.detail.DetailNavigation
import com.example.ferquies.todoapp.domain.detail.DetailViewState
import kotlinx.android.synthetic.main.fragment_detail.detailEditText
import kotlinx.android.synthetic.main.fragment_detail.saveButton
import kotlinx.android.synthetic.main.fragment_detail.title
import kotlinx.android.synthetic.main.fragment_detail.titleEditText
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        viewModel = getViewModel(DetailFragmentViewModel::class.java, viewModelFactory)
        viewModel.todoId = obtainTaskId()

        viewModel.getViewState().observe(this, Observer {
            render(it!!)
        })

        viewModel.navigationAction.observe(this, Observer {
            when (it) {
                is DetailNavigation.Back -> closeActivity()
            }
        })
    }

    private fun render(viewState: DetailViewState) {
        when (viewState.isEditing) {
            true -> {
                title.text = getString(R.string.edit_todo)
                saveButton.setOnClickListener {
                    val task = viewState.todo.copy(title = titleEditText.text.toString(),
                            detail = detailEditText.text.toString())
                    viewModel.updateTodo(task)
                }
            }
            false -> {
                title.text = getString(R.string.new_todo)
                saveButton.setOnClickListener {
                    viewModel.saveTodo(titleEditText.text.toString(), detailEditText.text.toString())
                }
            }
        }

        titleEditText.setText(viewState.todo.title)
        detailEditText.setText(viewState.todo.detail)
    }

    private fun obtainTaskId(): Int = arguments.getInt(TODO_DETAIL_ARG)

    companion object {
        private const val TODO_DETAIL_ARG = "todo_id"

        fun newInstance(taskId: Int = -1): Fragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putInt(TODO_DETAIL_ARG, taskId)
            fragment.arguments = bundle
            return fragment
        }
    }
}