
package business;

import business.Log.ControleAuditoria;
import comum.*;
import comum.enums.TipoUsuario;
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

    public static void validaCampoVazio (TextField text) throws Exception
    {
        if (text.getText().isEmpty())
        {
            throw new Exception("Existem campo(s) vazio(s)");
        }
    }

    public static void validaCampoVazioData (DatePicker data) throws Exception
    {
        if (data.getValue() == null)
        {
            throw new Exception("Campo de data vazio");
        }
    }

    public static void validaCampoVazioBox (ComboBox cb) throws Exception
    {
        if (cb.getValue() == null)
        {
            throw new Exception("Existem campo(s) vazio(s)");
        }
    }

    public static Usuario validaLogin(String email, String senha) throws SQLException {

        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();

        Usuario encontrado = (Usuario) dao.localiza(email);
        if(encontrado == null || !senha.equals(encontrado.getSenha())){
            return null;
        }
        return encontrado;
    }

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

    public static Usuario localizaUsuario(String email) throws SQLException{

        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        Usuario encontrado = (Usuario) dao.localiza(email);
        return encontrado;

    }

    public static Usuario localizaUsuarioPorId(int idUser) throws SQLException{

        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        Usuario encontrado = (Usuario) dao.localizaPorId(idUser);
        return encontrado;

    }

    public static void enviaDadosUsuario(Usuario u) throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Insere(u);
        ControleAuditoria.getInstance().AddAuditoria("Usuario salvo: " + u.getEmail());
    }

    public static void alterarDadosUsuario(Usuario u)  throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Alter(u);
    }

    public static void enviaPost (Postagem p) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Insere(p);
    }

    public static Postagem obtemPost (int id) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return (Postagem) dao.localizaPorId(id);
    }

    public static ArrayList obtemListPosts () throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return dao.listaTodos();
    }

    public static ArrayList obtemListCom (int filtro) throws SQLException{
        DAO dao = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        return dao.listaFiltroInt(filtro);
    }

    public static ArrayList obtemListPostsPorUser (int id) throws SQLException {
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return dao.listaFiltroInt(id);
    }

    public static ArrayList obtemPostsFiltro (String filtro) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        return dao.listaFiltro(filtro);
    }

    public static ArrayList obtemUsuariosFiltro (String filtro) throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        return dao.listaFiltro(filtro);
    }

    public static void enviaPostCientifico (Postagem pc) throws SQLException {
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Insere(pc);
        ControleAuditoria.getInstance().AddAuditoria("Postagem salva: " + pc.getTitulo());
    }

    public static void enviaComentario (Comentario c) throws SQLException{
        DAO dao = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        dao.Insere(c);
        ControleAuditoria.getInstance().AddAuditoria("Comentario salvo: " + c.getConteudo() + " - no post " + c.getIdPost());
    }

    public static void enviaDenuncia (Denuncia d) throws SQLException{
        DAO dao = EntidadeDAO.DENUNCIA.getEntidadeDAO();
        dao.Insere(d);
    }


    public static Comentario obtemComentario(int id) throws SQLException{
        DAO dao = EntidadeDAO.COMENTARIO.getEntidadeDAO();
        return (Comentario) dao.localizaPorId(id);
    }

    public static void apagaUsuario(Usuario u) throws SQLException{
        DAO dao  = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Apaga(u);
    }

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

    public static boolean ehModeradorLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() == TipoUsuario.MODERADOR;
    }

    public static boolean ehComumLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() == TipoUsuario.COMUM;
    }

    public static boolean ehPesquisadorLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() == TipoUsuario.PESQUISADOR;
    }

    public static boolean ehLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() != null;
    }

    public static byte[] imgToBytes(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10241];
        for (int readNum; (readNum = fis.read(buf)) != -1; ) {
            bos.write(buf, 0, readNum);
        }
        return bos.toByteArray();
    }

    public static Image bytesToImg(byte[] img)  {
        return new Image(new ByteArrayInputStream(img));
    }

    public static ArrayList obtemUsuarioTipo3 () throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        return dao.listaFiltroInt(3);
    }

}

