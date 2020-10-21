package business.Log;

import comum.Auditoria;
import dao.acesso.AuditoriaMSSQLDAO;

import java.sql.SQLException;

public class ThreadArmazenaAuditorias extends Thread {

    public static boolean ativo;

    @Override
    public void run(){
        ativo = true;
        while (ativo){
           String msg = ControleAuditoria.getInstance().removeProxAuditoria();


            AuditoriaMSSQLDAO dados = new AuditoriaMSSQLDAO<>();
            Auditoria a = new Auditoria();
            a.setDescricao(msg);
            a.setIdTipo(0);
            try {
                dados.Insere(a);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
