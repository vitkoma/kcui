package cloud.koma.kcui.service

import cloud.koma.kcui.model.ConnectorStatus
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import org.reactivestreams.Publisher

@Client("/")
interface ConnectorClient {

    @Get("{+clusterUrl}/connectors")
    fun fetchConnectors(@PathVariable clusterUrl: String): Publisher<List<String>>

    @Get("{+clusterUrl}/connectors/{connectorName}")
    @SingleResult
    fun fetchConnector(@PathVariable clusterUrl: String, @PathVariable connectorName: String): Publisher<ConnectorStatus>

    @Get("{+clusterUrl}/connectors/{connectorName}/status")
    fun fetchConnectorStatus(@PathVariable clusterUrl: String, @PathVariable connectorName: String): Publisher<ConnectorStatus>

    @Get("{+clusterUrl}/connectors/{connectorName}/config")
    fun fetchConnectorConfig(@PathVariable clusterUrl: String, @PathVariable connectorName: String): Publisher<Map<String, String>>

    @Post("{+clusterUrl}/connectors/{connectorName}/restart")
    fun restartConnector(@PathVariable clusterUrl: String, @PathVariable connectorName: String): Publisher<HttpResponse<String>>

    @Put("{+clusterUrl}/connectors/{connectorName}/pause")
    fun pauseConnector(@PathVariable clusterUrl: String, @PathVariable connectorName: String): Publisher<HttpResponse<String>>

    @Put("{+clusterUrl}/connectors/{connectorName}/resume")
    fun resumeConnector(@PathVariable clusterUrl: String, @PathVariable connectorName: String): Publisher<HttpResponse<String>>

    @Delete("{+clusterUrl}/connectors/{connectorName}")
    fun deleteConnector(@PathVariable clusterUrl: String, @PathVariable connectorName: String): Publisher<HttpResponse<String>>
}