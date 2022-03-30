# KCUI

## UI for Kafka Connect clusters

The application provides UI for managing connectors in Apache Kafka Connect clusters.

### Configuration

URLs for manages clusters are set using a configuration properties `kcui.clusters.<cluster-name>`
where `<cluster-name>` is an arbitrary name of a cluster.
Value of the property is a URL of the cluster's REST API.

Example:
```yaml
kcui:
    clusters:
        local: http://localhost:8083
        staging: http://my-staging-cluster:8083

```

KCUI is based on the Micronaut framework, see https://docs.micronaut.io/latest/guide/#config for ways 
how to set the configuration properties.

### Installation

See https://docs.micronaut.io/latest/guide/#deployingApp for ways how to deploy the application.

## TODO

- Fix "Delete connector"
- Add tests
- Error handling
- Add "Create connector"
- Add "Update connector configuration"
- Add configuration diff
- Add connection to brokers
- Show consumer lag
- Add "Reset processing" (sending tombstone to the offsets topic)