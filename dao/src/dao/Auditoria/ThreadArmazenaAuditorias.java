package dao.Auditoria;

public class ThreadArmazenaAuditorias extends Thread {

    public static boolean ativo;

    @Override
    public void run(){
        ativo = true;
        while (ativo){
           String msg = ControleAuditoria.getInstance().removeProxAuditoria();

           //conecta e salva

        }
    }

}
