package com.fixapi.connection;

import quickfix.Application;
import quickfix.Message;
import quickfix.SessionID;
import quickfix.*;

public class FixApplication implements Application {
    private boolean isConnected = false;
    @Override
    public void fromAdmin(Message message, SessionID sessionId) {
        System.out.println("Received admin message: " + message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("Received application message: " + message);
    }

    @Override
    public void onCreate(SessionID sessionId) {
        System.out.println("Session created with ID: " + sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        isConnected = true;
        System.out.println("Logged on: " + sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        System.out.println("Logged out: " + sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("Sending admin message: " + message);
        System.out.println("SessionID: " + sessionId);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        System.out.println("Sending application message: " + message);
    }


    static public void isConnected() {

        System. out. println("Recepcion de paquetes de datos");
    }

}
