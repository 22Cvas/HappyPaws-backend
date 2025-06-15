package org.ncapas.happypawsbackend.Domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PetResponse {

        private UUID id;
        private String name;
        private String species;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String breed;

        private String size;
        private String gender;
        private Integer age;
        private Boolean sterilized;
        private String status;
        private String photoUrl;
        private Instant entryDate;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private List<PetAttributeResponseDto> attributes;
}
