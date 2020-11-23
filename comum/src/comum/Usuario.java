package comum;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;


public class Usuario extends Entidade {

    private String Nome;

    private String senha;

    private String email;

    private LocalDate nascimento;

    private String pais;

    private String genero;

    private int idTipoDeUsuario;

    private byte[] Imagem;

    public byte[] comprovantePesquisadorByte;

    public byte[] docFotoByte;

    private String bio;

    public byte[] getComprovantePesquisadorByte() {
        return comprovantePesquisadorByte;
    }

    public void setComprovantePesquisadorByte(byte[] comprovantePesquisadorByte) {
        this.comprovantePesquisadorByte = comprovantePesquisadorByte;
    }

    public byte[] getDocFotoByte() {
        return docFotoByte;
    }

    public void setDocFotoByte(byte[] docFotoByte) {
        this.docFotoByte = docFotoByte;
    }

    public byte[] getImagem() {
        return Imagem;
    }

    public void setImagem(byte[] imagem) {
        Imagem = imagem;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdTipoDeUsuario() {
        return idTipoDeUsuario;
    }

    public void setIdTipoDeUsuario(int idTipoDeUsuario) {
        this.idTipoDeUsuario = idTipoDeUsuario;
    }
}
