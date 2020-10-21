package business.Log;

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
        //msg
        return auditoriasParaSalvar.poll();
    }

    public void iniciaThread(){
        if(thread == null){
            thread = new ThreadArmazenaAuditorias();
        }
        thread.start();
    }

    public void pararThread() {

        thread.setAtivo(false);
        try{
            thread.join(5000);
            if(thread.isAlive())
                thread.interrupt();
        }
        catch (InterruptedException erro){
            erro.printStackTrace();
        }
    }
}
