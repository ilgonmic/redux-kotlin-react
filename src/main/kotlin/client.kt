import org.reduxkotlin.createStore

@JsExport
val store = createStore({ state, action: Any -> rootReducer(state, action) }, State())

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

@JsExport
fun todos(
    state: Array<Todo> = emptyArray(),
    action: Any
): Array<Todo> = when (action) {
    is AddTodo -> {
        console.log("ADD TODO")
        state + Todo(action.id, action.text, false)
    }
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
    is SetVisibilityFilter -> {
        console.log("SET VISIBILITY FILTER")
        action.filter
    }
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

@JsExport
val SHOW_ALL = "SHOW_ALL"

@JsExport
val SHOW_ACTIVE = "SHOW_ACTIVE"

@JsExport
val SHOW_COMPLETED = "SHOW_COMPLETED"

@JsExport
class AddTodo(val text: String) {
    val id = nextTodoId++

    private companion object {
        var nextTodoId = 1
    }
}

@JsExport
class ToggleTodo(val id: Int)

@JsExport
class SetVisibilityFilter(val filter: String)