@JsExport
fun todos(
    state: Array<Todo> = emptyArray(),
    action: Any
): Array<Todo> = when (action) {

    is AddTodo -> state + Todo(action.id, action.text, false)
    is ToggleTodo -> state.map {
        if (it.id == action.id) {
            it.copy(completed = !it.completed)
        } else {
            it
        }
    }.toTypedArray()
    else -> state
}

@JsExport
fun visibilityFilter(
    state: String = SHOW_ALL,
    action: Any
): String = when (action) {
    is SetVisibilityFilter -> action.filter
    else -> state
}

@JsExport
fun rootReducer(
    state: State,
    action: Any
) = State(
    todos(state.todos, action),
    visibilityFilter(state.visibilityFilter, action)
)