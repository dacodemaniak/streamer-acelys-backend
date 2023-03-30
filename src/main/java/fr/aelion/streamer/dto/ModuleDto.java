package fr.aelion.streamer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ModuleDto {
    private int id;
    private String name;
    private String objective;

    private Set<MediaDto> medias;

    private String totalTime;
}
