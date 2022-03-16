package papler.projetologin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Usuario")

public class UsuarioEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name= "ID")
        private Integer id;
        @Column( name = "nomeCompleto")
        private String nomeCompleto;
        @Column(name = "telefone")
        private String telefone;
        @Column(name = "dtNascimento")
        private String dtNascimento;
        @Column(name = "cpf")
        private String cpf;
        @Column(name = "cidade")
        private String cidade;
        @Column(name = "rua")
        private String rua;
        @Column(name = "numero")
        private String numero;
        @Column(name = "complemento")
        private String complemento;
        @Column(name = "bairro")
        private String bairro;
        @Column(name = "estado")
        private String estado;
        @Column(name = "cep")
        private String cep;



}

