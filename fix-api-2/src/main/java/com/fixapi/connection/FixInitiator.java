package com.fixapi.connection;
import quickfix.*;
import quickfix.field.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import quickfix.Message;
import quickfix.SessionID;
import quickfix.Session;
import quickfix.field.MsgType;
import quickfix.field.Username;
import quickfix.field.Password;


public class FixInitiator {
    private static final Logger logger = Logger.getLogger(FixInitiator.class);

    public static void main(String[] args) throws ConfigError {
        SessionSettings settings = new SessionSettings("config.cfg");

        System. out. println(settings);

        Application application = new FixApplication();
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(application, storeFactory, settings, logFactory, messageFactory);
        initiator.start();

        SessionID sessionID = new SessionID("FIX.4.4", "TEST80129", "QFXPRICES");

        // Verifica si la sesión está disponible
        if (Session.doesSessionExist(sessionID)) {
            try {
                // Crea un mensaje de logon utilizando la clase genérica Message
                Message logon = new Message();
                logon.getHeader().setField(new MsgType(MsgType.LOGON));
                logon.setField(new Username("test80129"));
                logon.setField(new Password("fd55ds64"));

                // Envía el mensaje utilizando la sesión
                Session.sendToTarget(logon, sessionID);
            } catch (SessionNotFound e) {
                // Maneja las excepciones si no se encuentra la sesión o no se encuentra el campo
                e.printStackTrace();
            }
        } else {
            // La sesión no existe
            System.out.println("La sesión no está disponible");
        }


        while (true) {
            try {
                Thread.sleep(1000);

                //Message logon = new Message();
                //logon.getHeader().setString(MsgType.FIELD, MsgType.LOGON);




            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*catch (SessionNotFound e) {
                throw new RuntimeException(e);
            }*/

            if(initiator.isLoggedOn()) {
                System. out. println("Recepcion de paquetes de datos");
            }else {
                System. out. println("no devuelve nada");
            }
        }
    }
}
