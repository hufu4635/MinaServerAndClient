package com.pengfei.minademo;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import static org.apache.mina.filter.codec.textline.LineDelimiter.WINDOWS;

/**
 * Created by pactera on //.
 */

public class MinaThread extends Thread {
    private IoSession session = null;

    @Override
    public void run() {
        // Create TCP/IP connector.
        IoConnector connector=new NioSocketConnector();
        //编写过滤器，设置编码
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(
                                Charset.forName("UTF-8"),
                                WINDOWS.getValue(),
                                WINDOWS.getValue()
                        )
                )
        );
        //设置超时
        connector.setConnectTimeoutMillis(30000);
        //为连接器设置管理服务
        connector.setHandler(new MinaClientHandler());

        //连接服务器
        connector.connect(new InetSocketAddress(ConstantUtil.IP_ADDRESS, ConstantUtil.PORT));

    }

}
