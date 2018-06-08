package xyz.madki.trail.ui.todolist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_todo_list.*
import xyz.madki.trail.R
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class TodoListFragment : DaggerFragment() {
    @Inject lateinit var vm: TodoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TodoListAdapter {
            vm.incrementCount(it)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        vm.getTodos().subscribe { adapter.setTodos(it) }

        iv_add_todo.setOnClickListener {
            if (et_new_todo.text.toString().isNotEmpty()) {
                vm.addTodo(et_new_todo.text.toString())
            }
        }
    }
}
