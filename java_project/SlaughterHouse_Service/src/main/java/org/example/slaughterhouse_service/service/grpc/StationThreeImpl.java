package org.example.slaughterhouse_service.service.grpc;

import org.example.slaughterhouse_service.entities.ProductEntity;
//import generated.*;
import io.grpc.stub.StreamObserver;
import org.example.slaughterhouse_service.generated.*;
import org.example.slaughterhouse_service.service.repositories.ProductRepository;
import org.example.slaughterhouse_service.service.repositories.TrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationThreeImpl extends StationThreeServiceGrpc.StationThreeServiceImplBase
{
    private TrayRepository trayRepository;
    private ProductRepository productRepository;

    @Autowired
    public StationThreeImpl(TrayRepository trayRepository, ProductRepository productRepository)
    {
        this.trayRepository = trayRepository;
        this.productRepository = productRepository;
    }

    public void packSingleProduct(PackSingleProductRequest request,
                                  StreamObserver<PackSingleProductResponse> responseObserver)
    {
        // find tray in DB
        var tray = trayRepository.findById(request.getTrayId()).orElse(null);

        ProductEntity product = new ProductEntity(tray);
        assert tray != null; // for null safety
        product.setTotalWeight(tray.getPart().getWeight());
        // save the product to the database
        productRepository.save(product);
        // make dto for response
        Product productDto = Product.newBuilder()
                .setId(product.getId())
                .setTotalWeight(product.getTotalWeight())
                .setTrayId1(product.getTray().getId())
                .setTrayId2(-1) // no second tray
                .build();
        PackSingleProductResponse response = PackSingleProductResponse.newBuilder()
                .setProduct(productDto).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void packHalfAnimal(PackHalfAnimalRequest request,
                               StreamObserver<PackHalfAnimalResponse> responseObserver)
    {
        // find trays in DB
        var trayEntity1 = trayRepository.findById(request.getTrayId1()).orElse(null);
        var trayEntity2 = trayRepository.findById(request.getTrayId2()).orElse(null);
        assert trayEntity1 != null && trayEntity2 != null; // for null safety
        if (trayEntity1.getPart().getId() != trayEntity2.getPart().getId() && trayEntity1.getAnimal().getId() == trayEntity2.getAnimal().getId())
        {
            ProductEntity product = new ProductEntity(trayEntity1, trayEntity2);

            product.setTotalWeight(trayEntity1.getPart().getWeight() + trayEntity2.getPart().getWeight());
            // save the product to the database
            productRepository.save(product);
            // make dto for response
            Product productDto = Product.newBuilder()
                    .setId(product.getId())
                    .setTotalWeight(product.getTotalWeight())
                    .setTrayId1(product.getTray().getId())
                    .setTrayId2(product.getTray2().getId())
                    .build();
            PackHalfAnimalResponse response = PackHalfAnimalResponse.newBuilder()
                    .setProduct(productDto).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        else
        {
            throw new IllegalArgumentException("Trays must contain parts from the same animal and different part types.");
        }

    }

}
