import java.io.*;
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
            
            byte[] id0 = new byte[0x10];
            byte[] tmp = new byte[4];
            
            for(int i = 0; i < 4; i++) {
                System.arraycopy(hash, i * 4, tmp, 0, 4);
                
                for(int j = 3; j >= 0; j--)
                    System.arraycopy(tmp, j, id0, i * 4 + 3 - j, 1);
            }
            
            for(byte b : id0) {
                System.out.print(String.format("%02X", b));
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}