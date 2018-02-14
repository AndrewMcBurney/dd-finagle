package datadog.sbt

import datadog.trace.api.Trace

import com.twitter.finagle.Service
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.http.{Request, Response}

object HttpRouter {
  @Trace
  def byRequest[REQUEST](routes: PartialFunction[Request, Service[REQUEST, Response]]) =
    new RoutingService(
      new PartialFunction[Request, Service[REQUEST, Response]] {
        def apply(request: Request)       = routes(request)
        def isDefinedAt(request: Request) = routes.isDefinedAt(request)
      })
}
