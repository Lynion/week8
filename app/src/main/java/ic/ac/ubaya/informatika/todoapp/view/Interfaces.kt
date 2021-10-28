package ic.ac.ubaya.informatika.todoapp.view

import android.view.View
import android.widget.CompoundButton
import ic.ac.ubaya.informatika.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton,
                       isChecked:Boolean,
                       obj: Todo)
}

interface TodoEditClick{
    fun onTodoEditClick(v:View)
}

interface RadioClick {
    fun onRadioClick(v:View, obj:Todo)
}

interface TodoSaveChangesClick {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}



