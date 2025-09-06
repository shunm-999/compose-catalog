package com.shunm.android.domain.shared

sealed interface Result<T, E>

data class Ok<T, E>(val value: T) : Result<T, E>

data class Err<T, E>(val error: E) : Result<T, E>

fun <T, E> Result<T, E>.isOk(): Boolean = this is Ok

fun <T, E> Result<T, E>.isOkAnd(block: (T) -> Boolean): Boolean {
    return if (this is Ok) {
        block(this.value)
    } else {
        false
    }
}

fun <T, E> Result<T, E>.isErr(): Boolean = this is Err

fun <T, E> Result<T, E>.isErrAnd(block: (E) -> Boolean): Boolean {
    return if (this is Err) {
        block(this.error)
    } else {
        false
    }
}

fun <T, E> Result<T, E>.ok(): Option<T> {
    return if (this is Ok) {
        Some(this.value)
    } else {
        None
    }
}

fun <T, E> Result<T, E>.err(): Option<E> {
    return if (this is Err) {
        Some(this.error)
    } else {
        None
    }
}

fun <T, E, U> Result<T, E>.map(block: (T) -> U): Result<U, E> {
    return when (this) {
        is Ok -> Ok(block(this.value))
        is Err -> Err(this.error)
    }
}

fun <T, E, U> Result<T, E>.mapOr(
    default: U,
    block: (T) -> U,
): U {
    return when (this) {
        is Ok -> block(this.value)
        is Err -> default
    }
}

fun <T, E, U> Result<T, E>.mapOrElse(
    default: (E) -> U,
    block: (T) -> U,
): U {
    return when (this) {
        is Ok -> block(this.value)
        is Err -> default(this.error)
    }
}

fun <T, E, U> Result<T, E>.mapErr(block: (E) -> U): Result<T, U> {
    return when (this) {
        is Ok -> Ok(this.value)
        is Err -> Err(block(this.error))
    }
}

fun <T, E> Result<T, E>.inspect(block: (T) -> Unit): Result<T, E> {
    return when (this) {
        is Ok -> {
            block(this.value)
            this
        }
        is Err -> this
    }
}

fun <T, E> Result<T, E>.inspectErr(block: (E) -> Unit): Result<T, E> {
    return when (this) {
        is Ok -> this
        is Err -> {
            block(this.error)
            this
        }
    }
}

fun <T, E> Result<T, E>.expect(msg: String): T {
    return when (this) {
        is Ok -> this.value
        is Err -> throw Exception(msg)
    }
}

fun <T, E> Result<T, E>.unwrap(): T {
    return when (this) {
        is Ok -> this.value
        is Err -> throw Exception("called `Result.unwrap()` on an `Err` value: ${this.error}")
    }
}

fun <T, E> Result<T, E>.expectErr(msg: String): E {
    return when (this) {
        is Ok -> throw Exception(msg)
        is Err -> this.error
    }
}

fun <T, E> Result<T, E>.unwrapErr(): E {
    return when (this) {
        is Ok -> throw Exception("called `Result.unwrap_err()` on an `Ok` value: ${this.value}")
        is Err -> this.error
    }
}

fun <T, E, U> Result<T, E>.and(res: Result<U, E>): Result<U, E> {
    return when (this) {
        is Ok ->
            when (res) {
                is Ok -> Ok(res.value)
                is Err -> Err(res.error)
            }
        is Err -> Err(this.error)
    }
}

fun <T, E, U> Result<T, E>.andThen(block: (T) -> Result<U, E>): Result<U, E> {
    return when (this) {
        is Ok -> block(this.value)
        is Err -> Err(this.error)
    }
}

fun <T, E, U> Result<T, E>.or(res: Result<T, U>): Result<T, U> {
    return when (this) {
        is Ok -> Ok(this.value)
        is Err ->
            when (res) {
                is Ok -> Ok(res.value)
                is Err -> Err(res.error)
            }
    }
}

fun <T, E, U> Result<T, E>.orElse(block: (E) -> Result<T, U>): Result<T, U> {
    return when (this) {
        is Ok -> Ok(this.value)
        is Err -> block(this.error)
    }
}

fun <T, E> Result<T, E>.unwrapOr(default: T): T {
    return when (this) {
        is Ok -> this.value
        is Err -> default
    }
}

fun <T, E> Result<T, E>.unwrapOrElse(block: (E) -> T): T {
    return when (this) {
        is Ok -> this.value
        is Err -> block(this.error)
    }
}

fun <T, E> Result<Result<T, E>, E>.flatten(): Result<T, E> {
    return when (this) {
        is Ok -> this.value
        is Err -> Err(this.error)
    }
}
