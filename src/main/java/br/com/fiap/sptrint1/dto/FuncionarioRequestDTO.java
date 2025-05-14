package br.com.fiap.sptrint1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class FuncionarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(
            regexp = "\\d{11}",
            message = "O CPF deve conter exatamente 11 dígitos numéricos (somente números)"
    )
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "O RG é obrigatório")
    @Pattern(
            regexp = "\\d{7,14}",
            message = "O RG deve conter entre 7 e 14 dígitos numéricos (somente números)"
    )
    private String rg;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
            regexp = "\\(?\\d{2}\\)?\\s?9\\d{4}-\\d{4}",
            message = "O telefone deve estar no formato (XX) 9XXXX-XXXX"
    )
    private String telefone;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
