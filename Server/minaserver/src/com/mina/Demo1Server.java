package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class Demo1Server {
	
	//日志类的实现
    private static Logger logger = Logger.getLogger(Demo1Server.class+"");
    //端口号，要求客户端与服务器端一致
    private static int PORT = 9088;
    
    public static void main(String[] args){
        IoAcceptor acceptor = null;
        try{
           //创建一个非阻塞的server端的Socket
           acceptor = new NioSocketAcceptor();
           //设置过滤器（使用mina提供的文本换行符编解码器）(在bind前进行)
           acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
           //自定义的编解码器
           //acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CharsetCodecFactory()));
           //设置读取数据的换从区大小
           acceptor.getSessionConfig().setReadBufferSize(2048);
           //读写通道10秒内无操作进入空闲状态
           acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
           //为接收器设置管理服务
           acceptor.setHandler(new Demo1ServerHandler());
           
           
           
           //绑定端口
           acceptor.bind(new InetSocketAddress(PORT));
           
           logger.info("服务器启动成功...    端口号未："+PORT);
           
       }catch(Exception e){
           logger.info("服务器启动异常..."+e.getMessage().toString());
           e.printStackTrace();
       }
   }
}