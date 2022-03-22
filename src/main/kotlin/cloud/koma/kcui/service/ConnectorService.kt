package cloud.koma.kcui.service

import cloud.koma.kcui.ApplicationConfig
import cloud.koma.kcui.model.ConnectorStatus
import io.micronaut.http.HttpResponse
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Singleton
class ConnectorService(
    private val config: ApplicationConfig,
    private val connectorClient: ConnectorClient
) {

    fun fetchConnectors(clusterName: String): Publisher<List<ConnectorStatus>> {
        config.clusters[clusterName]?.let { url ->
            val connectorNames = connectorClient.fetchConnectors(url)
            return  Mono.from(connectorNames)
                .flatMap { list -> Flux.merge(list.map { conn -> fetchConnectorStatus(url, conn) }).collectList() }
        }
        return Mono.empty()
    }

    fun fetchConnector(clusterName: String, connectorName: String): Publisher<ConnectorStatus> {
        config.clusters[clusterName]?.let { url ->
            return connectorClient.fetchConnector(url, connectorName)
        }
        return Mono.empty()
    }

    private fun fetchConnectorStatus(clusterUrl: String, connectorName: String): Publisher<ConnectorStatus> {
        return connectorClient.fetchConnectorStatus(clusterUrl, connectorName)
    }

    private fun invokeConnectorAction(clusterName: String, connectorNames: List<String>,
                                      action: (clusterUrl: String, connector: String) -> Publisher<HttpResponse<String>>): Publisher<List<ConnectorStatus>> {
        config.clusters[clusterName]?.let { url ->
            return Flux.concat(connectorNames.map { conn ->
                Mono.from(action(url, conn))
            }).flatMap { fetchConnectors(url) }
        }
        return Mono.empty()
    }

    private fun invokeConnectorAction2(clusterName: String, connectorNames: List<String>,
                                      action: (clusterUrl: String, connector: String) -> Publisher<HttpResponse<String>>): Publisher<List<ConnectorStatus>> {
        config.clusters[clusterName]?.let { url ->
            return Flux.concat(connectorNames.map { conn ->
                Mono.from(action(url, conn)).flatMap { Mono.from(fetchConnectorStatus(url, conn)) }
            }).collectList()
        }
        return Mono.empty()
    }

    fun restartConnectors(clusterName: String, connectorNames: List<String>): Publisher<List<ConnectorStatus>> {
        return invokeConnectorAction(clusterName, connectorNames, connectorClient::restartConnector)
    }

    fun pauseConnectors(clusterName: String, connectorNames: List<String>): Publisher<List<ConnectorStatus>> {
        return invokeConnectorAction2(clusterName, connectorNames, connectorClient::pauseConnector)
    }

    fun resumeConnectors(clusterName: String, connectorNames: List<String>): Publisher<List<ConnectorStatus>> {
        return invokeConnectorAction2(clusterName, connectorNames, connectorClient::resumeConnector)
    }

}