@JsExport
class AddTodo(val text: String) {
    val id = nextTodoId++

    // problem with export in 1.4.10, but ok in 1.4.20
//    private companion object {
//        var nextTodoId = 1
//    }
}

private var nextTodoId = 1

@JsExport
class ToggleTodo(val id: Int)

@JsExport
class SetVisibilityFilter(val filter: String)