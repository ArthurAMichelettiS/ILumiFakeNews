package dao.Auditoria;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ControleAuditoria {

    //singleton
    private static ControleAuditoria instance;

    private ControleAuditoria(){
        auditoriasParaSalvar = new ConcurrentLinkedQueue<String>();
    }

    public static ControleAuditoria getInstance(){
        if(instance == null){
            instance = new ControleAuditoria();
        }
        return instance;
    }
    //

    private ThreadArmazenaAuditorias thread;

    private ConcurrentLinkedQueue<String> auditoriasParaSalvar;

    public void AddAuditoria(String msg){
        auditoriasParaSalvar.add(msg);
    }

    public String removeProxAuditoria(){
        String msg = auditoriasParaSalvar.poll();
        return msg;
    }

    public void iniciaThread(){
        if(thread == null){
            thread = new ThreadArmazenaAuditorias();
        }
        thread.start();
    }

    public void pararThead(){
        try{
            thread.join(2000);

        }
        catch (Exception erro){
            //log
        }
    }

}
