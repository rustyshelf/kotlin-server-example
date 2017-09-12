package au.com.shiftyjelly.labs.kotlinexample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class MainVerticle: AbstractVerticle() {

    override fun start(startFuture: Future<Void>?) {
        val router = Router.router(vertx)

        //setup endpoint
        router.get("/hello").handler({ this.handleHello(it) })

        //start our server
        vertx.createHttpServer().requestHandler( { router.accept(it) }).listen(5000)
    }

    private fun handleHello(routingContext: RoutingContext) {
        routingContext.response().setStatusCode(200).end("Well hello yourself!")
    }
}