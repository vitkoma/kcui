package cloud.koma.kcui.model

import com.fasterxml.jackson.annotation.JsonAlias

data class StateInfo (
    val id: Int?,
    val state: String?,
    @JsonAlias("worker_id")
    val workerId: String?,
    val trace: String?
)