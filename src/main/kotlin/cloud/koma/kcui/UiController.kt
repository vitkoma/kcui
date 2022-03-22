package cloud.koma.kcui

import io.micronaut.context.env.Environment
import io.micronaut.http.annotation.*
import io.micronaut.http.server.types.files.StreamedFile
import java.net.URL
import java.util.*


@Controller
class UiController(
    private val environment: Environment
) {

    @Get("/{path:[^.]*}")
    fun forward(path: String?): Optional<StreamedFile>? {
        return environment.getResource("classpath:public/index.html")
            .map { url: URL? -> StreamedFile(url) }
    }

}