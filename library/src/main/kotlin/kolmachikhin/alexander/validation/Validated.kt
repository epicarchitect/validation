package kolmachikhin.alexander.validation

sealed class Validated<out DATA, out INCORRECT_REASON> {
    abstract val data: DATA
}

data class Correct<out DATA> internal constructor(
    override val data: DATA
) : Validated<DATA, Nothing>()

data class Incorrect<out DATA, out REASON> internal constructor(
    override val data: DATA,
    val reason: REASON
) : Validated<DATA, REASON>()