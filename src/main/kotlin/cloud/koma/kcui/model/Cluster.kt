package cloud.koma.kcui.model

data class Cluster (
    val name: String,
    val url: String,
    val running: Boolean?,
    val version: String?
)