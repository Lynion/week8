package ic.ac.ubaya.informatika.todoapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ic.ac.ubaya.informatika.todoapp.R
import ic.ac.ubaya.informatika.todoapp.databinding.TodoItemLayoutBinding
import ic.ac.ubaya.informatika.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>,val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),TodoCheckedChangeListener,TodoEditClick  {
    class TodoViewHolder(var view:TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater,R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onCheckChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked) {
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)

    }
}