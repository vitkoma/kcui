package cloud.koma.kcui

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("cloud.koma.kcui")
        .start()
}

