package ic.ac.ubaya.informatika.todoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import ic.ac.ubaya.informatika.todoapp.model.Todo
import ic.ac.ubaya.informatika.todoapp.model.TodoDatabase
import ic.ac.ubaya.informatika.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel (application: Application)
    :AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())

            todoLD.value = db.todoDao().selectAllTodo()

        }
    }

    fun clearTask(id:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(id)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }


}