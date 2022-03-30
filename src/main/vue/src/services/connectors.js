import http from './http'

const connectorService = {
    listConnectors: (clusterName, onFulfilled) => {
        http
            .get("/clusters/" + clusterName + "/connectors")
            .then(onFulfilled);
    },
    pauseConnectors: (clusterName, connectorNames, onFulfilled) => {
        http
            .put("/clusters/" + clusterName + "/actions/pause-connectors", connectorNames)
            .then(onFulfilled);
    },
    resumeConnectors: (clusterName, connectorNames, onFulfilled) => {
        http
            .put("/clusters/" + clusterName + "/actions/resume-connectors", connectorNames)
            .then(onFulfilled);
    },
    restartConnectors: (clusterName, connectorNames, onFulfilled) => {
        http
            .put("/clusters/" + clusterName + "/actions/restart-connectors", connectorNames)
            .then(onFulfilled);
    },
    deleteConnectors: (clusterName, connectorNames, onFulfilled) => {
        http
            .put("/clusters/" + clusterName + "/actions/delete-connectors", connectorNames)
            .then(onFulfilled);
    },
    fetchConfig: (clusterName, connectorName, onFulfilled) => {
        http
            .get("/clusters/" + clusterName + "/connectors/" + connectorName + "/config")
            .then(onFulfilled);
    },
}

export default connectorService;