import org.http4k.contract.contract
import org.http4k.contract.meta
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.core.*
import org.http4k.format.Jackson
import org.http4k.format.Jackson.auto
import org.http4k.server.Jetty
import org.http4k.server.asServer
import kotlin.random.Random

fun main() {
    val responseBody = Jackson.autoBody<ResponseData>().toLens()
    val responseExample = ResponseData(listOf(Thing("1")))

    val contractRoutes = contract {
        renderer = OpenApi3(ApiInfo("Example", "v1"), Jackson)
        descriptionPath = "/docs/swagger.json"

        routes += "/" meta {
            produces += ContentType("application/vnd.api+json")
            returning(Status.OK, responseBody to responseExample)
        } bindContract Method.GET to {
            val id = Random.nextInt(1, 20000).toString()
            Body.auto<ResponseData>().toLens().inject(
                ResponseData(listOf(Thing(id))),
                Response(Status.OK)
            )
        }
    }

    contractRoutes.asServer(Jetty(8080)).start()
}

data class ResponseData(val data: List<Thing>)
data class Thing(val id: String, val type: String = "things")
