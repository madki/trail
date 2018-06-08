package xyz.madki.trail.ui.todolist

import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import xyz.madki.trail.db.TodoDao
import xyz.madki.trail.models.Todo
import javax.inject.Inject

class TodoListViewModel @Inject constructor(private val todoDao: TodoDao) : ViewModel() {

    fun getTodos(): Flowable<List<Todo>> {
        return this.todoDao.load()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun addTodo(title: String) {
        Observable.fromCallable {
            this.todoDao.createTodoIfNotExists(Todo(title))
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { Timber.d("Inserted : " + it.toString()) }
    }

    fun incrementCount(todo: Todo) {
        Observable.fromCallable {
            this.todoDao.insert(todo.incrementCount())
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { Timber.d("Inserted : " + it.toString()) }
    }
}