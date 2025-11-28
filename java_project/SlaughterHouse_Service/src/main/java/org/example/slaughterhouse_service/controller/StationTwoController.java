package org.example.slaughterhouse_service.controller;

import com.example.slaughterhouseService.Part;
import org.example.slaughterhouse_service.Dto.CutResultDto;
import org.example.slaughterhouse_service.Dto.PartDto;
import org.example.slaughterhouse_service.service.grpc.StationTwoImplClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/station-two")
public class StationTwoController
{
    private final StationTwoImplClient client;

    public StationTwoController(StationTwoImplClient client)
    {
        this.client = client;
    }

    @PostMapping("/{animalId}")
    public CutResultDto cut(@PathVariable int animalId)
    {
        List<Part> grpcParts = client.cut(animalId);

        List<PartDto> dtoList = grpcParts.stream()
                .map(p -> new PartDto(
                        p.getId(),
                        p.getAnimalId(),
                        p.getWeight(),
                        p.getTrayId(),
                        p.getPartTypeId()))
                .toList();

        return new CutResultDto(dtoList);
    }
}
