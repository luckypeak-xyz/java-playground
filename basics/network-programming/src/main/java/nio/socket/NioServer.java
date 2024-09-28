package nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NioServer {
  public static final int port = 9999;
  public static final int timeout = 1000;
  public static final int bufferSize = 1024;

  public static void main(String[] args) throws Exception {

    Selector selector = Selector.open();

    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress(port));
    serverSocketChannel.configureBlocking(false);
    log.info("初始化端口：{}", port);
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      if (selector.select(timeout) == 0) {
        log.info("select timeout");
        continue;
      }
      Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
      while (keyIterator.hasNext()) {
        SelectionKey key = keyIterator.next();
        if (key.isAcceptable()) {
          log.info("新增客户端");
          SocketChannel socketChannel = serverSocketChannel.accept();
          socketChannel.configureBlocking(false);
          socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }
        if (key.isReadable()) {
          SocketChannel channel = (SocketChannel) key.channel();
          ByteBuffer buffer = (ByteBuffer) key.attachment();
          channel.read(buffer);
          log.info("收到客户端消息：{}", new String(buffer.array()));
        }
        keyIterator.remove();
      }
    }
  }
}
