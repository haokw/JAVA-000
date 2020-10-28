package io.netty.client;

public class NettyClientApplication {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int port = 8808;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch(NumberFormatException e) {
                // 采用默认值
            }
        }
        new HttpClient().connect(port, "localhost");
    }
}
