import Task

val listTasks = mutableListOf<Task>()

fun main() {
    var done = false

    while(!done){
        displayMenu()
        val userInput = getInput()
//using when here because of module description
        when (userInput) {
            "1" -> createTask()
            "2" -> removeTask()
            "3" -> viewTasks()
            "4" -> completeTask()
            "5" -> {
                display("Exiting application. Goodbye!")
                done = true
            }

            else -> display("Invalid option. Please choose between 1 and 4.")
        }
    }
}
//Create a console based task manager that allows users to add, remove, and list tasks.
//This language doesn't need semicolons?
fun displayMenu(){
    val optionMenu= "////////////////////////////////////////////////////////////////\n" +
                    "Welcome to the task management application! Please Choose a task:\n" +
                    "1. Add Task\n2. Remove Task\n3. View Tasks\n4. Complete Task\n5. Quit\n" +
                    "////////////////////////////////////////////////////////////////"
    display(optionMenu)
}

//displays text
fun display(toDisplay:String){
    println(toDisplay)
}

fun getInput():String{
    val answer = readln()
    return answer
}

fun createTask(){
    display("What is the Task?")
    val name = getInput()
    display("When does this task need to be done by?")
    val doneBy = getInput()
    val newTask = Task(name, doneBy, false)
    listTasks.add(newTask)
}

fun removeTask() {
    if (listTasks.isEmpty()) {
        display("There are no tasks to remove.\n")
        return
    }

    viewTasks()
    display("Enter the number of the task you want to remove:")
    val input = getInput()
    val index = input.toIntOrNull()

    if (index != null && index in 1..listTasks.size) {
        val removedTask = listTasks.removeAt(index - 1)
        display("Removed task: ${removedTask.name}\n")
    } else {
        display("Invalid task number.\n")
    }
}

fun viewTasks() {
    if (listTasks.isEmpty()) {
        display("task list is currently empty.\n")
        return
    }

    display("Your Tasks:")
    for ((index, task) in listTasks.withIndex()) {
        val status = if (task.isDone) "[X]" else "[ ]"
        display("${index + 1}. $status ${task.name} (Due: ${task.doneBy})")
    }
    display("")
}
fun completeTask(){
    if (listTasks.isEmpty()) {
        display("There are no tasks to complete.\n")
        return
    }

    viewTasks()
    display("Enter the number of the task you want to complete:")
    val input = getInput()
    val index = input.toIntOrNull()

    if (index != null && index in 1..listTasks.size) {
        listTasks[(index - 1)].makeDone()
        val completedTask = listTasks[index - 1]
        display("Completed task: ${completedTask.name}\n")
    } else {
        display("Invalid task number.\n")
    }
}