import org.reduxkotlin.*

@JsExport
val store = createStore(::rootReducer, State())
    .let { origin ->
        object : Store<State> {
            override val getState: GetState<State>
                get() = origin.getState
            override var dispatch: Dispatcher
                get() = origin.dispatch
                set(value) {
                    origin.dispatch = value
                }
            override val subscribe: (StoreSubscriber) -> StoreSubscription
                get() = origin.subscribe
            override val replaceReducer: (Reducer<State>) -> Unit
                get() = origin.replaceReducer

        }
    }

// It is necessary for reexport Store type
@JsExport
interface Store<State> {
    val getState: GetState<State>
    var dispatch: Dispatcher
    val subscribe: (StoreSubscriber) -> StoreSubscription
    val replaceReducer: (Reducer<State>) -> Unit
    val state: State
        get() = getState()
}