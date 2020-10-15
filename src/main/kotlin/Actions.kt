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