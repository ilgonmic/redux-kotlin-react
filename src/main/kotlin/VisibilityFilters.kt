object VisibilityFilters {
    const val SHOW_ALL = "SHOW_ALL"
    const val SHOW_ACTIVE = "SHOW_ACTIVE"
    const val SHOW_COMPLETED = "SHOW_COMPLETED"
}

@JsExport
val SHOW_ALL
    get() = VisibilityFilters.SHOW_ALL

@JsExport
val SHOW_ACTIVE
    get() = VisibilityFilters.SHOW_ACTIVE

@JsExport
val SHOW_COMPLETED
    get() = VisibilityFilters.SHOW_COMPLETED