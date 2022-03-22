package cloud.koma.kcui.model

data class ConnectorStatus (
    val name: String,
    val type: ConnectorType,
    val connector: StateInfo?,
    val tasks: List<StateInfo>?
)

/*
data class ConnectorStatus (
    val name: String?,
    val type: ConnectorType?,
    val connector: StateInfo?,
    val tasks: List<StateInfo>?
)
 */