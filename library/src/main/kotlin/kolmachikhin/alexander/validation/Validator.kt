package kolmachikhin.alexander.validation

abstract class Validator<DATA, INCORRECT_REASON> {

    suspend fun validate(data: DATA) = data.incorrectReason()?.let {
        Incorrect(data, it)
    } ?: Correct(data)

    protected open suspend fun DATA.incorrectReason(): INCORRECT_REASON? = null

}