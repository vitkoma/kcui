package cloud.koma.kcui

import cloud.koma.kcui.model.Cluster
import cloud.koma.kcui.model.ConnectorStatus
import cloud.koma.kcui.service.ClusterService
import cloud.koma.kcui.service.ConnectorService
import io.micronaut.http.annotation.*
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Controller("/api")
class ApiController(
    private val clusterService: ClusterService,
    private val connectorService: ConnectorService
) {

    @Get("/clusters")
    fun clusters(): Publisher<List<Cluster>> {
        return clusterService.fetchClusters()
    }

    @Get("/clusters/{cluster}/connectors")
    fun connectors(@PathVariable cluster: String): Publisher<List<ConnectorStatus>> {
        return connectorService.fetchConnectors(cluster)
    }

    @Get("/clusters/{cluster}/connectors/{connector}")
    fun connector(@PathVariable cluster: String, @PathVariable connector: String): Publisher<ConnectorStatus> {
        return Mono.from(connectorService.fetchConnector(cluster, connector))
    }

    @Post("/clusters/{cluster}/actions/restart-connectors")
    fun restartConnectors(@PathVariable cluster: String, @Body connectorNames: List<String>): Publisher<List<ConnectorStatus>> {
        return connectorService.restartConnectors(cluster, connectorNames)
    }

    @Put("/clusters/{cluster}/actions/pause-connectors")
    fun pauseConnectors(@PathVariable cluster: String, @Body connectorNames: List<String>): Publisher<List<ConnectorStatus>> {
        return connectorService.pauseConnectors(cluster, connectorNames)
    }

    @Put("/clusters/{cluster}/actions/resume-connectors")
    fun resumeConnectors(@PathVariable cluster: String, @Body connectorNames: List<String>): Publisher<List<ConnectorStatus>> {
        return connectorService.resumeConnectors(cluster, connectorNames)
    }

}