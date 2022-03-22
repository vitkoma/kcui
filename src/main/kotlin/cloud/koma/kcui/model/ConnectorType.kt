package cloud.koma.kcui.model

import com.fasterxml.jackson.annotation.JsonValue

enum class ConnectorType(private val code: String) {
    SOURCE("source"),
    SINK("sink");

    @JsonValue
    fun getType(): String = code
}