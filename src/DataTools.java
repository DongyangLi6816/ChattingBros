public class DataTools {
    public static void bytesArrayToByteArr(byte[] numArrBytes, byte[] bytes){
        for (int i = 0; i < numArrBytes.length/4; i++) {
            for (int j = 0; j < 4; j++) {
                numArrBytes[i*4+j] = bytes[j];
            }
        }
    }

    public static byte[] intToByte(int num){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((num >> 24) & 0xff);
        bytes[1] = (byte) ((num >> 16) & 0xff);
        bytes[2] = (byte) ((num >> 8) & 0xff);
        bytes[3] = (byte) (num & 0xff);
        return bytes;
    }
    
}
