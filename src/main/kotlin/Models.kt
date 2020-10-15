@JsExport
data class State(
    val todos: Array<Todo> = emptyArray(),
    val visibilityFilter: String = SHOW_ALL
)

@JsExport
data class Todo(
    val id: Int,
    val title: String,
    val completed: Boolean
)