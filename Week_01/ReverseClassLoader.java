import java.io.*;
import java.lang.reflect.Method;

public class ReverseClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class<?> Hello = new ReverseClassLoader().findClass("Hello");
            Method hello = Hello.getMethod("hello");
            hello.invoke(Hello.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String reverseClassFile = "Hello.xlass";
            byte[] bytes = decode(reverseClassFile);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            // return null;
        }

        return null;
    }

    public byte[] decode(String reverseClassFile) throws Exception {
        // 字节反转 255-byte
        DataInputStream in = new DataInputStream(new FileInputStream(reverseClassFile));
        int len = in.available(); // length in bytes
        byte[] rBytes = new byte[len];
        for (int i = 0; i < len; i++) {
            byte b = in.readByte();
            rBytes[i] = (byte)(255 - b);
        }
        return rBytes;
    }
}
