package papler.projetologin.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Usuario")

public class UsuarioEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Integer id;
        @Column(unique = true, nullable = false, name = "login")
        private String login;
        @Column(nullable = false, name = "password")
        private String password;
        @Column(nullable = false, name = "nomeCompleto")
        private String nomeCompleto;
        @Column(nullable = false, name = "email", unique = true)
        private String email;
        @Column(nullable = true, name = "telefone")
        private String telefone;
        @Column(unique = true, nullable = true, name = "cpf")
        private String cpf;
        @Column(nullable = true, name = "cidade")
        private String cidade;
        @Column(nullable = true, name = "rua")
        private String rua;
        @Column(nullable = true, name = "numero")
        private String numero;
        @Column(nullable = true, name = "complemento")
        private String complemento;
        @Column(nullable = true, name = "bairro")
        private String bairro;
        @Column(nullable = true, name = "estado")
        private String estado;
        @Column(nullable = true, name = "cep")
        private String cep;



}

