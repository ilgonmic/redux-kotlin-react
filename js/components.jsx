import React, {useState} from 'react'
import {
    AddTodo as AddTodoReducer,
    SetVisibilityFilter,
    SHOW_ACTIVE,
    SHOW_ALL,
    SHOW_COMPLETED,
    store,
    ToggleTodo
} from 'kotlin-library'

Object.defineProperty(store, 'dispatch', {
    configurable: true,
    get: () => store._dispatch
})

Object.defineProperty(store, 'subscribe', {
    configurable: true,
    get: () => store._subscribe
})

Object.defineProperty(store, 'state', {
    configurable: true,
    get: store._getState
})

const Todo = ({onClick, completed, title}) => (
    <li
        onClick={onClick}
        style={{
            textDecoration: completed ? 'line-through' : 'none'
        }}
    >
        {title}
    </li>
)

const TodoList = ({todos, toggleTodo}) => (
    <ul>
        {todos.map(todo => {
            console.log("todo", {...todo})
            return (
                <Todo key={todo.id} completed={todo.completed} title={todo.title} onClick={() => toggleTodo(todo.id)}/>
            )
        })}
    </ul>
)

const FilterLink = ({store, children, filter}) => {
    const [filterState, setFilter] = useState(store.state.visibilityFilter)
    store.subscribe(() => {
        console.log("FROM VISIBILITY SUBSCRIBE")
        setFilter(store.state.visibilityFilter)
    })

    const active = filterState === filter
    const onClick = () => store.dispatch(new SetVisibilityFilter(filter))
    return (
        <Link active={active} children={children} onClick={onClick}/>
    )
}

const getVisibleTodos = (todos, filter) => {
    switch (filter) {
        case SHOW_ALL:
            return todos
        case SHOW_COMPLETED:
            return todos.filter(t => t.completed)
        case SHOW_ACTIVE:
            return todos.filter(t => !t.completed)
        default:
            throw new Error('Unknown filter: ' + filter)
    }
}

const VisibleTodoList = ({store}) => {
    const [todos, setTodos] = useState(store.state.todos)
    const [filterState, setFilter] = useState(store.state.visibilityFilter)
    console.log(store.state)
    store.subscribe(() => {
        console.log("FROM TODO SUBSCRIBE")
        setTodos(store.state.todos)
        setFilter(store.state.visibilityFilter)
    })
    const toggleTodo = (id) => store.dispatch(new ToggleTodo(id))
    console.log("filter", store.state.visibilityFilter)

    return (
        <TodoList todos={getVisibleTodos(todos, filterState ?? SHOW_ALL)} toggleTodo={toggleTodo}/>
    )
}

const Link = ({active, children, onClick}) => (
    <button
        onClick={onClick}
        disabled={active}
        style={{
            marginLeft: '4px'
        }}
    >
        {children}
    </button>
)

const Footer = ({store}) => {
    return (
        <div>
            <span>Show: </span>
            <FilterLink store={store} filter={SHOW_ALL}>All</FilterLink>
            <FilterLink store={store} filter={SHOW_ACTIVE}>Active</FilterLink>
            <FilterLink store={store} filter={SHOW_COMPLETED}>Completed</FilterLink>
        </div>
    )
}

const AddTodo = ({store}) => {
    let input

    const dispatch = store.dispatch

    return (
        <div>
            <form
                onSubmit={e => {
                    e.preventDefault()
                    if (!input.value.trim()) {
                        return
                    }
                    dispatch(new AddTodoReducer(input.value))
                    input.value = ''
                }}
            >
                <input ref={node => (input = node)}/>
                <button type="submit">Add Todo</button>
            </form>
        </div>
    )
}

export const App = () => (
    <div>
        <AddTodo store={store}/>
        <VisibleTodoList store={store}/>
        <Footer store={store}/>
    </div>
)