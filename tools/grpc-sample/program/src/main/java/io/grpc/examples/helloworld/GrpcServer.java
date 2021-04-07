package io.grpc.examples.helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
  public static void main(String[] args) {
    try {

      int port = Integer.parseInt(args[0]);
      final Server server =
          ServerBuilder.forPort(port).addService(new HelloServiceImpl()).build().start();
      System.out.println("Server started, listening on " + port);
      server.awaitTermination();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
