package org.example.slaughterhouse_service.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.example.slaughterhouse_service.service.grpc.StationOneImpl;
import org.example.slaughterhouse_service.service.grpc.StationThreeImpl;
import org.example.slaughterhouse_service.service.grpc.StationTwoImpl;

//@SpringBootApplication
//@EntityScan("org.example.slaughterhouse_service.entities")
//@EnableJpaRepositories("org.example.slaughterhouse_service.service.repositories")
public class ServerMain //implements CommandLineRunner
{
//    private StationOneImpl stationOne;
//    private StationTwoImpl stationTwo;
//    private StationThreeImpl stationThree;
//
//    @Value("${spring.grpc.server.port:5678}")
//    private int port;
//
//    @Autowired
//    public ServerMain(StationOneImpl stationOne, StationTwoImpl stationTwo, StationThreeImpl stationThree)
//    {
//        this.stationOne = stationOne;
//        this.stationTwo = stationTwo;
//        this.stationThree = stationThree;
//    }
//
//
//    public static void main(String[] args)
//    {
//        SpringApplication.run(ServerMain.class, args);
//
//    }
//
//    @Override
//    public void run(String... args) throws Exception
//    {
//        Server server = ServerBuilder
//                .forPort(port)
//                .addService(stationOne)
//                .addService(stationTwo)
//                .addService(stationThree)
//                .build();
//        server.start();
//        System.out.println("Server started, listening on " + port);
//        server.awaitTermination();
//    }
}