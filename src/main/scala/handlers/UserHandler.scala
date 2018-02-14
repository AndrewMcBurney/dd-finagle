package datadog.sbt

import datadog.trace.api.Trace

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Future

class UserHandler(val name: String) extends Service[Request, Response] {
  @Trace
  def apply(req: Request): Future[Response] = {
    val r = req.response
    r.setStatusCode(200)
    r.setContentString("Username: " + name)
    Future.value(r)
  }
}
