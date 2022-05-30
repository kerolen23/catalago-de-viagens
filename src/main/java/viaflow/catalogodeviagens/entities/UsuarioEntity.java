package viaflow.catalogodeviagens.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Usuario")

public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "password")
    private String password;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "nomeCompleto")
    private String nomeCompleto;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "dtNascimento")
    private String dtNascimento;
    @Column(name = "cpf", unique = true)
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
    @Column(name = "imageUrl")
    private String imageUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Seja bem vindo(a) ");
        sb.append(getNomeCompleto());
        sb.append("\nSeu email " + getEmail() + " foi cadastrado com sucesso!\n");
        return sb.toString();
    }
}

