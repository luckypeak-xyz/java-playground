package nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioClient {
  private static final Logger log = LoggerFactory.getLogger(NioClient.class);

  public static void main(String[] args) throws Exception {
    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.configureBlocking(false);
    InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);

    if (!socketChannel.connect(inetSocketAddress)) { // 非阻塞
      while (!socketChannel.finishConnect()) {
        log.info("正在连接");
      }
    }
    ByteBuffer writeBuf = ByteBuffer.wrap("hello".getBytes());
    socketChannel.write(writeBuf);
    System.in.read();
  }
}
