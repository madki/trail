package xyz.madki.trail.ui.todolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_todo.view.*
import xyz.madki.trail.R
import xyz.madki.trail.models.Todo

class TodoListAdapter(
        private var todos: List<Todo> = ArrayList(),
        private val onClick: (todo: Todo) -> Unit
        ): RecyclerView.Adapter<TodoListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListHolder {
        return TodoListHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoListHolder, position: Int) {
        holder.bind(todos[position]) {
            onClick(todos[position])
        }
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }
}

class TodoListHolder(private val v: View): RecyclerView.ViewHolder(v) {
    fun bind(todo: Todo, listener: () -> Unit) {
        with (v) {
            tv_id.text = todo.id.toString()
            tv_title.text = todo.title
            tv_count.text = todo.count.toString()
            setOnClickListener {
                listener()
            }
        }
    }
}