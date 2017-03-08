package com.pengfei.minademo;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import static android.R.id.message;

/**
 * Created by pactera on //.
 */

public class MinaClientHandler extends IoHandlerAdapter {

    //接收到来自服务端的消息
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String msg = message.toString();
        Log.d("TEST", "客户端接收到的信息为:" + msg);
        super.messageReceived(session, message);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        //向服务器端发送消息
        session.write("this is client");
        super.sessionOpened(session);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        Log.d("TEST", "客户端发生异常");
        super.exceptionCaught(session, cause);
    }
}
