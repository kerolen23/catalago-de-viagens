package papler.projetologin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmailDto implements Serializable {

        private static final long serialVersionUID = 1L;


        @JsonProperty("login")
        private String email;


}
