
package business;

import business.Log.ControleAuditoria;
import comum.*;
import comum.enums.TipoUsuario;
import dao.acesso.SeguidoresMSSQLDAO;
import dao.basis.DAO;
import dao.enums.EntidadeDAO;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import org.apache.commons.validator.GenericValidator;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class  Acesso {

    /***
     * analiza o text e se for vazio joga uma exceção
     * @param text
     * @throws Exception
     */
    public static void validaCampoVazio (TextField text) throws Exception
    {
        if (text.getText().isEmpty())
        {
            throw new Exception("Existem campo(s) vazio(s)");
        }
    }

    /***
     * analiza o campo de data e se for vazio joga uma exceção
     * @param data
     * @throws Exception
     */
    public static void validaCampoVazioData (DatePicker data) throws Exception
    {
        if (data.getValue() == null)
        {
            throw new Exception("Campo de data vazio");
        }
    }

    /***
     * analiza um combobox e se não houver uma opçõa selecionada joga uma exceção
     * @param cb
     * @throws Exception
     */
    public static void validaCampoVazioBox (ComboBox cb) throws Exception
    {
        if (cb.getValue() == null)
        {
            throw new Exception("Existem campo(s) vazio(s)");
        }
    }

    /***
     * Avalia se um email e senha correspondem a um usuario a ser logado
     * @param email
     * @param senha
     * @return o usuario logado, null se inválido
     * @throws SQLException
     */
    public static Usuario validaLogin(String email, String senha) throws SQLException {

        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();

        Usuario encontrado = (Usuario) dao.localiza(email);
        if(encontrado == null || !senha.equals(encontrado.getSenha())){
            return null;
        }
        return encontrado;
    }

    /***
     * valida se sa senhas estão preenchidas e são iguais, joga exceções caso inválido
     * @param senha
     * @param senhaconf
     * @throws Exception
     */
    public static void validaNovaSenha (String senha, String senhaconf) throws Exception
    {
        if (senha == null || senha.trim().isEmpty()) {
            throw new Exception("Preencha os campos de senha");
        }
        else if (senhaconf == null || senhaconf.trim().isEmpty())
        {
            throw new Exception("Preencha os campos de senha");
        }
        else if (!senha.equals(senhaconf))
        {
            throw new Exception("Senha Confirmada Inválida");
        }

    }

    public static void validaDataNasc (LocalDate data) throws Exception {
        LocalDate antes;
        antes = LocalDate.now().plusYears(-13);

        if (!data.isBefore(antes)){
            throw new Exception("Data de Nascimento inválida");
        }
    }

    /***
     * valida se é um email válido em formatação e inexistente, caso contrario joga exceções
     * @param email
     * @throws Exception
     */
    public static void validaEmail (String email) throws Exception{
        Usuario finder = localizaUsuario(email);
        if (!GenericValidator.isEmail(email))
        {
            throw new Exception("E-mail inválido");
        }
        else if (finder == null)
        {
            return;
        }
        else if(finder.getEmail().equals(email))
        {
            throw new Exception("Usuário já cadastrado");
        }
    }

    /***
     * localiza um usuário pelo email
     * @param email
     * @return o usuario encontrado ou nulo caso não encontrado
     * @throws SQLException
     */
    public static Usuario localizaUsuario(String email) throws SQLException{

        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        Usuario encontrado = (Usuario) dao.localiza(email);
        return encontrado;

    }

    /***
     * localiza um usuário pelo id dele
     * @param idUser
     * @return o usuario encontrado ou nulo caso não encontrado
     * @throws SQLException
     */
    public static Usuario localizaUsuarioPorId(int idUser) throws SQLException{

        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        Usuario encontrado = (Usuario) dao.localizaPorId(idUser);
        return encontrado;

    }

    /***
     * salva um usuário no banco
     * @param u
     * @throws SQLException
     */
    public static void enviaDadosUsuario(Usuario u) throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Insere(u);
        ControleAuditoria.getInstance().AddAuditoria("Usuario salvo: " + u.getEmail());
    }

    /***
     * altera um usuário no banco
     * @param u
     * @throws SQLException
     */
    public static void alterarDadosUsuario(Usuario u)  throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Alter(u);
    }

    /***
     * salva uma postagem no banco
     * @param p
     * @throws SQLException
     */
    public static void enviaPost (Postagem p) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Insere(p);
    }

    /***
     * encontra uma postagem pelo id
     * @param id
     * @return a postagem correspondente
     * @throws SQLException
     */
    public static Postagem obtemPost (int id) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return (Postagem) dao.localizaPorId(id);
    }

    /***
     * obtem as postagems
     * @return lista de todas as postagens
     * @throws SQLException
     */
    public static ArrayList obtemListPosts () throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return dao.listaTodos();
    }

    /**
     * lista os comentarios de uma postagem de id especifico
     * @param filtro
     * @return lista de comentários
     * @throws SQLException
     */
    public static ArrayList obtemListCom (int filtro) throws SQLException{
        DAO dao = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        return dao.listaFiltroInt(filtro);
    }

    /**
     * lista de postagens de um usuário especifico
     * @param id
     * @return lista de postagens
     * @throws SQLException
     */
    public static ArrayList obtemListPostsPorUser (int id) throws SQLException {
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return dao.listaFiltroInt(id);
    }

    /**
     * lista de postagens que contém no titulo ou conteudo o texto de filtro
     * @param filtro
     * @return lista de postagens
     * @throws SQLException
     */
    public static ArrayList obtemPostsFiltro (String filtro) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return dao.listaFiltro(filtro);
    }

    /**
     * lista de usuários que contem no seu nome o texto de filtro
     * @param filtro
     * @return lista de usuários
     * @throws SQLException
     */
    public static ArrayList obtemUsuariosFiltro (String filtro) throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        return dao.listaFiltro(filtro);
    }

    /**
     * salva uma postagem cientifica
     * @param pc
     * @throws SQLException
     */
    public static void enviaPostCientifico (Postagem pc) throws SQLException {
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Insere(pc);
        ControleAuditoria.getInstance().AddAuditoria("Postagem salva: " + pc.getTitulo());
    }

    /**
     * salva um comentário
     * @param c
     * @throws SQLException
     */
    public static void enviaComentario (Comentario c) throws SQLException{
        DAO dao = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        dao.Insere(c);
        ControleAuditoria.getInstance().AddAuditoria("Comentario salvo: " + c.getConteudo() + " - no post " + c.getIdPost());
    }

    /**
     * salva uma denuncia
     * @param d
     * @throws SQLException
     */
    public static void enviaDenuncia (Denuncia d) throws SQLException{
        DAO dao = EntidadeDAO.DENUNCIA.getEntidadeDAO();
        dao.Insere(d);
    }

    /**
     * obtem um comentário pelo seu id
     * @param id
     * @return comentário correspondente
     * @throws SQLException
     */
    public static Comentario obtemComentario(int id) throws SQLException{
        DAO dao = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        return (Comentario) dao.localizaPorId(id);
    }

    /**
     * apaga usuário do banco
     * @param u
     * @throws SQLException
     */
    public static void apagaUsuario(Usuario u) throws SQLException{
        DAO dao  = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Apaga(u);
    }

    /**
     * salva alterações em uma postagem
     * @param p
     * @throws SQLException
     */
    public static void alteraPostagem(Postagem p) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Alter(p);
    }


    public static void apagaPostagem(Postagem p) throws SQLException{
        DAO dao  = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Apaga(p);
    }

    public static void alteraComentario(Comentario c) throws SQLException{
        DAO dao = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        dao.Alter(c);
    }
    public static void apagaComentario(Comentario c) throws SQLException{
        DAO dao  = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        dao.Apaga(c);
    }

    /**
     * obtem se o usuário logado é um moderador
     * @return verdadeiro se o tipo de user for moderador, falso caso não
     */
    public static boolean ehModeradorLogado(){
        return DadosDaSecao.getInstance().getTipoUsuario() == TipoUsuario.MODERADOR;
    }

    /**
     * obtem se o usuário logado é unicamente comum
     * @return verdadeiro se o tipo de user for comum, falso caso não
     */
    public static boolean ehComumLogado(){
        return DadosDaSecao.getInstance().getTipoUsuario() == TipoUsuario.COMUM;
    }

    /**
     * obtem se o usuário logado é pesquisador
     * @return verdadeiro se o tipo de user for pesquisador, falso caso não
     */
    public static boolean ehPesquisadorLogado(){
        return DadosDaSecao.getInstance().getTipoUsuario() == TipoUsuario.PESQUISADOR;
    }

    /**
     * obtem se o usuário está logado
     * @return verdadeiro se está logado, falso caso não
     */
    public static boolean ehLogado(){
        return DadosDaSecao.getInstance().getTipoUsuario() != null;
    }

    /***
     * torna um arquivo em uma sequencia de bytes
     * @param file
     * @return vetor de bytes
     * @throws IOException
     */
    public static byte[] imgToBytes(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10241];
        for (int readNum; (readNum = fis.read(buf)) != -1; ) {
            bos.write(buf, 0, readNum);
        }
        return bos.toByteArray();
    }

    /**
     * transforma bytes para imagem de javafx
     * @param img
     * @return Image do javafx
     */
    public static Image bytesToImg(byte[] img)  {
        if(img == null){
            return  new Image(new ByteArrayInputStream(new byte[0]));
        }
        return new Image(new ByteArrayInputStream(img));
    }

    /**
     * Obtem uma lista de usuários pesquisadores em avaliação
     * @return lista de usários
     * @throws SQLException
     */
    public static ArrayList obtemUsuarioTipo3 () throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        return dao.listaFiltroInt(3);
    }


    public static ArrayList obtemDenuncias() throws SQLException{
        DAO dao = EntidadeDAO.DENUNCIA.getEntidadeDAO();
        return dao.listaTodos();
    }

    /**
     * obtem lista de outros usuários que o usuário de id especificado está seguindo
     * @param userId
     * @return
     * @throws SQLException
     */
    public static ArrayList obtemSeguindo (int userId) throws SQLException{
        DAO dao = EntidadeDAO.SEGUIDORES.getEntidadeDAO();
        return dao.listaFiltroInt(userId);
    }

    /**
     * obtem se o usuário logado está seguindo um usuário de id especifico
     * @param userId
     * @return
     * @throws SQLException
     */
    public static Boolean logadoEstaSeguindo(int userId) throws SQLException{
        SeguidoresMSSQLDAO dao = new SeguidoresMSSQLDAO();
        if(!ehLogado())
            return false;
        return dao.LocalizaSeguidor(userId, DadosDaSecao.getInstance().getUsuarioLogado().getId());
    }

    /***
     * adiciona um usuario aos que o usuário logado está seguindo
     * @param idPerfil
     * @throws SQLException
     */
    public static void insereSeguindo(int idPerfil) throws SQLException{
        DAO dao = EntidadeDAO.SEGUIDORES.getEntidadeDAO();
        Seguidores s = new Seguidores();
        s.setIdUser(DadosDaSecao.getInstance().getUsuarioLogado().getId());
        s.setIdUserCon(idPerfil);
        dao.Insere(s);
    }
    public static void apagaSeguindo(int idPerfil) throws SQLException{
        DAO dao = EntidadeDAO.SEGUIDORES.getEntidadeDAO();
        Seguidores s = new Seguidores();
        s.setIdUser(DadosDaSecao.getInstance().getUsuarioLogado().getId());
        s.setIdUserCon(idPerfil);
        dao.Apaga(s);
    }

    public static boolean ehUserPesquisador(int id) throws SQLException {
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        Usuario u = (Usuario)dao.localizaPorId(id);
        return u.getIdTipoDeUsuario() == 1;
    }

}

