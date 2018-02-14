package datadog.sbt

import datadog.trace.api.DDTags

import io.opentracing.Scope
import io.opentracing.Tracer
import io.opentracing.util.GlobalTracer

import com.twitter.finagle.Http
import com.twitter.finagle.http.HttpMuxer
import com.twitter.finagle.http.path._
import com.twitter.finagle.http.Method
import com.twitter.util.{Await, Future}

object Server extends App {
  val routingService =
    HttpRouter.byRequest { request =>
      (request.method, Path(request.path)) match {
        case Method.Get -> Root                  => new IndexHandler()
        case Method.Get -> Root / "user" / name  => new UserHandler(name)
      }
    }

  HttpMuxer.addRichHandler("/", routingService)

  val tracer: Tracer = GlobalTracer.get
  var scope: Scope = tracer.buildSpan("boot-server").startActive(true)

  try {
    println("Booting up the server...")
    scope.span.setTag(DDTags.SERVICE_NAME, "finagle-dd-demo")
    val server = Http.serve("0.0.0.0:8000", HttpMuxer)
    Await.ready(server)
  } finally {
    scope.close
  }
}
