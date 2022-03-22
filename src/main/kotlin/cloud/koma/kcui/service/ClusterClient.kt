package cloud.koma.kcui.service

import cloud.koma.kcui.model.ClusterInfo
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import org.reactivestreams.Publisher

@Client("/")
interface ClusterClient {

    @Get("{+clusterUrl}")
    fun fetchCluster(@PathVariable clusterUrl: String): Publisher<ClusterInfo>

}