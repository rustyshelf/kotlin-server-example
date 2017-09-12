package au.com.shiftyjelly.labs.kotlinexample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class MainVerticle: AbstractVerticle() {

    private val HTTP_OK = 200
    private val PORT_NUMBER = 5000

    override fun start(startFuture: Future<Void>?) {
        val router = Router.router(vertx)

        //setup endpoints
        router.get("/hello").handler({ this.handleHello(it) })
        router.get("/jsonHello").handler({ this.handleHelloJson(it) })

        //start our server
        vertx.createHttpServer().requestHandler( { router.accept(it) }).listen(PORT_NUMBER)
    }

    private fun handleHello(routingContext: RoutingContext) {
        routingContext.response().setStatusCode(HTTP_OK).end("Well hello yourself!")
    }

    private fun handleHelloJson(routingContext: RoutingContext) {
        val response = HelloResponse()
        response.message = "Welcome stranger"
        response.code = 150

        val jsonObject = JsonObject(Json.encode(response))

        routingContext.response().setChunked(true).write(jsonObject.encodePrettily()).putHeader("Content-Type", "application/json").setStatusCode(HTTP_OK).end()
    }
}