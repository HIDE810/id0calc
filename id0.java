import java.io.*;
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
            
            for(byte b : hash) {
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