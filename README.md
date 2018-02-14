# Finagle DataDog

_An example application showcasing how to setup a [Finagle RPC](https://twitter.github.io/finagle/)-backed HTTP server complete with [DataDog distributed tracing instrumentation](https://docs.datadoghq.com/tracing/)._

### Development Setup

1)

```bash
DD_API_KEY=<YOUR_DD_API_KEY> docker-compose up -d
```

2)

```bash
 sbt run
```
