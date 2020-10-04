package com.next.fs.demo;

import com.next.fs.demo.webservice.WebServiceVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  /*@Override
  public void start(Promise<Void> startPromise) {
    vertx.createHttpServer()
            .requestHandler(request -> request.response().end("Hello Vert.x!"))
            .listen(port);
    log.info("===> MainVerticle iniciado");
  }*/

  @Override
  public void start(Promise<Void> startPromise) {
    vertx.deployVerticle(new WebServiceVerticle());
    log.info("===> MainVerticle iniciado");
    startPromise.complete();
  }



}
