package cloud.koma.kcui

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("koma.kcui")
class ApplicationConfig {
    var clusters: Map<String, String> = emptyMap()
}