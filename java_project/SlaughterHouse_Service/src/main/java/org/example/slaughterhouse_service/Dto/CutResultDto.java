package org.example.slaughterhouse_service.Dto;

import java.util.List;

public class CutResultDto
{
    private List<PartDto> parts;

    public CutResultDto(List<PartDto> parts)
    {
        this.parts = parts;
    }

    public List<PartDto> getParts()
    {
        return parts;
    }

    public void setParts(List<PartDto> parts)
    {
        this.parts = parts;
    }
}
