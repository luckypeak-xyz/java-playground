package netty.demo01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

// 服务器端的业务处理类
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

  // 读取数据事件，msg就是客戶端发过来的数据。
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    // System.out.println("Server:"+ctx);
    // 用缓冲区接受数据
    ByteBuf buf = (ByteBuf) msg;
    // 转成字符串
    // 必须编码和解码
    // 字符-->字节
    // 字节-->字符
    System.out.println("client msg：" + buf.toString(CharsetUtil.UTF_8));
  }

  // 数据读取完毕事件，读取完客户端数据后回复客户端
  public void channelReadComplete(ChannelHandlerContext ctx) {
    // Unpooled.copiedBuffer获取到缓冲区
    // 第一个参数是向客户端传的字符串
    String resp_msg = "宝塔镇河妖";
    ByteBuf byteBuf = Unpooled.copiedBuffer(resp_msg, CharsetUtil.UTF_8);
    ctx.writeAndFlush(byteBuf); // 当前handler出链表了
  }

  // 异常发生事件
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) {
    // 异常时关闭ctx，ctx是相关信息的汇总，关闭它其它的也就关闭了。
    ctx.close();
  }
}
