package business.Log;

import comum.Auditoria;
import dao.acesso.AuditoriaMSSQLDAO;

import java.sql.SQLException;

public class ThreadArmazenaAuditorias extends Thread {

    public static boolean ativo;

    @Override
    public void run(){
        ativo = true;
        AuditoriaMSSQLDAO dados = new AuditoriaMSSQLDAO<>();
        Auditoria a = new Auditoria();

        while (ativo){
           String msg = ControleAuditoria.getInstance().removeProxAuditoria();

            if(msg!=null){
                a.setDescricao(msg);
                a.setIdTipo(0);
                try {
                    dados.Insere(a);
                    Thread.sleep(1);
                } catch (SQLException | InterruptedException throwables) {
                    throwables.printStackTrace();
                }
            }


        }
    }

}
