package org.ncapas.happypawsbackend.Domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PetResponse {

        private Integer id;
        private String name;
        private String species;
        private String breed;
        private String size;
        private String gender;
        private Integer age;
        private Boolean sterilized;
        private String status;
        private String photoUrl;
        private Instant entryDate;

}
