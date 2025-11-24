package org.example.slaughterhouse_service.service.grpc;

import com.example.slaughterhouseService.CutRequest;
import com.example.slaughterhouseService.CutResponse;
import com.example.slaughterhouseService.Part;
import com.example.slaughterhouseService.StationTwoServiceGrpc;
import org.example.slaughterhouse_service.entities.PartEntity;
import org.example.slaughterhouse_service.entities.TrayEntity;
import io.grpc.stub.StreamObserver;
import org.example.slaughterhouse_service.service.repositories.AnimalRepository;
import org.example.slaughterhouse_service.service.repositories.PartTypeRepository;
import org.example.slaughterhouse_service.service.repositories.PartsRepository;
import org.example.slaughterhouse_service.service.repositories.TrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationTwoImpl extends StationTwoServiceGrpc.StationTwoServiceImplBase
{
    private AnimalRepository animalRepository;
    private TrayRepository trayRepository;
    private PartsRepository partsRepository;
    private PartTypeRepository partTypeRepository;

    private double headWeightPercent, gutsWeightPercent, legWeightPercent, meatWeightPercent;

    @Autowired
    public StationTwoImpl(AnimalRepository animalRepository, TrayRepository trayRepository,
                          PartsRepository partsRepository, PartTypeRepository partTypeRepository)
    {
        this.animalRepository = animalRepository;
        this.trayRepository = trayRepository;
        this.partsRepository = partsRepository;
        this.partTypeRepository = partTypeRepository;
    }

    public void cut(CutRequest request, StreamObserver<CutResponse> responseObserver)
    {
        // find animal in DB
        var animal = animalRepository.findById(request.getAnimalId()).orElse(null);
        if (animal == null)
        {
            System.out.println("Animal with ID " + request.getAnimalId() + " not found.");
            // throw error to client with .onError()
            responseObserver.onError(new Throwable("Animal not found"));
        }
        // cut animal
        // unalive it
        else
        {
            animal.setAlive(false);
            // update it in DB
            animalRepository.save(animal);
        }
        // cut them into parts: for now everything is cut into 4 parts
        headWeightPercent = 0.10;
        gutsWeightPercent = 0.20;
        legWeightPercent = 0.30;
        meatWeightPercent = 1 - (headWeightPercent + gutsWeightPercent + legWeightPercent);
        assert animal != null;
        PartEntity head = new PartEntity(animal, animal.getWeight()*headWeightPercent, null
                , partTypeRepository.findById(1).orElseThrow());
        PartEntity guts = new PartEntity(animal, animal.getWeight()*gutsWeightPercent, null
                , partTypeRepository.findById(2).orElseThrow());
        PartEntity leg = new PartEntity(animal, animal.getWeight()*legWeightPercent, null
                , partTypeRepository.findById(3).orElseThrow());
        PartEntity meat = new PartEntity(animal, animal.getWeight()*meatWeightPercent, null
                , partTypeRepository.findById(4).orElseThrow());

        List<TrayEntity> trays = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            TrayEntity tray = new TrayEntity(animal.getWeight(), animal, null);
            trays.add(tray);
        }
        // save trays to DB first even it's null on part
        trayRepository.saveAll(trays);
        // put parts in trays
        head.setTray(trays.get(0));
        guts.setTray(trays.get(1));
        leg.setTray(trays.get(2));
        meat.setTray(trays.get(3));
        // save parts to DB
        partsRepository.save(head);
        partsRepository.save(guts);
        partsRepository.save(leg);
        partsRepository.save(meat);
        // set parts in trays
        trays.get(0).setPart(head);
        trays.get(1).setPart(guts);
        trays.get(2).setPart(leg);
        trays.get(3).setPart(meat);
        // update trays in DB
        trayRepository.saveAll(trays);


        // make dto for response
        // since we have repeated fields on cutResponse, we need to add parts one by one

        // create part response list
        List<Part> partResponseList = new ArrayList<>();
        for (TrayEntity tray : trays)
        {
            Part partResponse = Part.newBuilder()
                    .setId(tray.getPart().getId())
                    .setAnimalId(tray.getAnimal().getId())
                    .setWeight(tray.getPart().getWeight())
                    .setTrayId(tray.getId())
                    .setPartTypeId(tray.getPart().getId())
                    .build();
            partResponseList.add(partResponse);
        }
        CutResponse cutResponse = CutResponse.newBuilder().addAllParts(partResponseList).build();

        System.out.println("Cutting animal: " + request);
        responseObserver.onNext(cutResponse);
        responseObserver.onCompleted();
    }


}
