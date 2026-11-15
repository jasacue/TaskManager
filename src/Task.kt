class Task (val name:String, val doneBy:String, var isDone:Boolean) {
    fun makeDone(){
        isDone = true
    }
}