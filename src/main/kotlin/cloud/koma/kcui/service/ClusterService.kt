package cloud.koma.kcui.service

import cloud.koma.kcui.ApplicationConfig
import cloud.koma.kcui.model.Cluster
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class ClusterService (private val config: ApplicationConfig, private val clusterClient: ClusterClient) {

    fun fetchClusters(): Publisher<List<Cluster>> {
        val results = config.clusters
            .map { fetchCluster(it.key, it.value) }
        return  Flux.fromIterable(results).flatMap { it }.collectList()
    }

    private fun fetchCluster(name: String, url: String): Publisher<Cluster> {
        return Mono.from(clusterClient.fetchCluster(url))
                .map { Cluster(name, url, true, it.version) }
                .onErrorReturn(Cluster(name, url, false, null))
    }

}