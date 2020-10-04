package com.next.fs.demo.webservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServiceVerticle extends AbstractVerticle {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final int PORT = 8080;

    /*@Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            var response = request.response();
            response.putHeader("Content-Type", "text/plain");

            response.end("Usuarios");
        });

        server.listen(PORT);
        log.info("===> WebServiceVerticle iniciado por el puerto:{}", PORT);
    }*/

    /*@Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route("/users").handler(context -> {
            HttpServerResponse response = context.response();
            response.putHeader("content-type", "text/plain");
            response.end("Usuarios");
        });

        server.requestHandler(router).listen(PORT);

        log.info("WebServiceVerticle iniciado por el puerto: {}", PORT);
        startPromise.complete();
    }*/

    /*@Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        Route route = router.route("/users");
        route.handler(context -> {
            HttpServerResponse response = context.response();
            response.setChunked(true);
            response.write("route1\n");
            context.vertx().setTimer(3000, tid -> context.next());
        });

        route.handler(context -> {
            HttpServerResponse response = context.response();
            response.write("route2\n");
            context.vertx().setTimer(3000, tid -> context.next());
        });

        route.handler(context -> {
            HttpServerResponse response = context.response();
            response.write("route3\n");
            response.end();
        });

        server.requestHandler(router).listen(PORT);
        log.info("WebServiceVerticle iniciado por el puerto: {}", PORT);
        startPromise.complete();
    }*/

    /*@Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        Route usersRoute = router.route("/users");
        usersRoute.handler(context -> {
            HttpServerResponse response = context.response();
            response.putHeader("content-type", "text/plain");
            response.end("/users");
        });

        Route productsRoute = router.route("/products");
        productsRoute.handler(context -> {
            HttpServerResponse response = context.response();
            response.putHeader("content-type", "text/plain");
            response.end("/products");
        });

        Route paymentsRoute = router.route("/payments");
        paymentsRoute.handler(context -> {
            HttpServerResponse response = context.response();
            response.putHeader("content-type", "text/plain");
            response.end("/payments");
        });

        server.requestHandler(router).listen(PORT);
        log.info("WebServiceVerticle iniciado por el puerto: {}", PORT);
        startPromise.complete();
    }*/

    /*@Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/users/:userid/products/:productId").handler(context -> {
            final String userId = context.request().getParam("userid");
            final String productId = context.request().getParam("productId");
            HttpServerResponse response = context.response();
            response.putHeader("content-type", "application/json");
            response.end("{\"userId\":\"" + userId + "\", \"productId\":\"" + productId + "\"}");
        });

        server.requestHandler(router).listen(PORT);
        log.info("WebServiceVerticle iniciado por el puerto: {}", PORT);
        startPromise.complete();
    }*/

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/users/:userid/products/:productId")
                .handler(context -> {
                    Integer userId;
                    try {
                        userId = Integer.parseInt(context.request().getParam("userid"));
                        final String productId = context.request().getParam("productId");
                        HttpServerResponse response = context.response();
                        response.putHeader("content-type", "application/json");
                        response.end("{\"userId\":\"" + userId + "\", \"productId\":\"" + productId + "\"}");
                    } catch (NumberFormatException e){
                        context.fail(405, e);
                    }
                });

        server.requestHandler(router).listen(PORT);
        log.info("WebServiceVerticle iniciado por el puerto: {}", PORT);
        startPromise.complete();
    }
}
