package cloud.koma.kcui

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("kcui")
class ApplicationConfig {
    var clusters: Map<String, String> = emptyMap()
}