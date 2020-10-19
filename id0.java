import java.io.*;
import java.nio.*;
import java.util.*;
import java.security.*;

class ID0 {
    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("movable.sed", "r");
            byte[] bytes = new byte[0x10];
            raf.seek(0x110);
            raf.read(bytes);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(bytes);
            byte[] tmp = new byte[4];
            
            for(int i = 0; i < 4; i++) {
                System.arraycopy(hash, i * 4, tmp, 0, 4);
                ByteBuffer buf = ByteBuffer.wrap(tmp);
                buf.order(ByteOrder.LITTLE_ENDIAN);
                System.out.printf("%08X", buf.getInt(0));
            }
        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch(IOException e) {e.printStackTrace();}
        catch(NoSuchAlgorithmException e) {e.printStackTrace();}
    }
}