package viaflow.catalogodeviagens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("Password")
    public String Password;
    @JsonProperty("login")
    private String email;
    @JsonProperty("nomeCompleto")
    private String nomeCompleto;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("dtNascimento")
    private String dtNascimento;
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
