package papler.projetologin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    @JsonProperty("nomeCompleto")
    private String nomeCompleto;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("dtNascimento")
    private String dtNascimento;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("cidade")
    private String cidade;
    @JsonProperty("rua")
    private String rua;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("complemento")
    private String complemento;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("cep")
    private String cep;

}
