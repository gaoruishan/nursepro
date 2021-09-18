package com.base.commlibs.voiceUtils;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 *
 */

public class PcmToWavUtil {
    private static byte[] RIFF = "RIFF".getBytes();
    private static byte[] RIFF_SIZE = new byte[4];
    private static byte[] RIFF_TYPE = "WAVE".getBytes();

    private static byte[] FORMAT = "fmt ".getBytes();
    private static byte[] FORMAT_SIZE = new byte[4];
    private static byte[] FORMATTAG = new byte[2];
    private static byte[] CHANNELS = new byte[2];
    private static byte[] SamplesPerSec = new byte[4];     //采样率
    private static byte[] AvgBytesPerSec = new byte[4];
    private static byte[] BlockAlign = new byte[2];
    private static byte[] BitsPerSample = new byte[2];     //比特率

    private static byte[] DataChunkID = "data".getBytes();
    private static byte[] DataSize = new byte[4];

    /**
     *
     * @param partsPaths  需要合成的文件路径列表
     * @param unitedFilePath   合成文件的输出路径
     */
    public static void uniteFiles(String partsPaths, String unitedFilePath) {
        try {
            File unitedFile = new File(unitedFilePath);
            FileOutputStream fos = new FileOutputStream(unitedFile);
            RandomAccessFile ra = null;
            ra = new RandomAccessFile(partsPaths, "r");
            ra.seek(6);
            byte[] buffer = new byte[1024 * 8];
            int len = 0;
            while ((len = ra.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            ra.close();
            fos.close();
            Log.e("1111111111", "uniteFiles: " );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static File pcmToWavFile(File file, byte[] bytes) throws Exception {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            int totalAudioLen = bytes.length;
            int totalDataLen = totalAudioLen + 36;
            DataSize = revers(intToBytes(totalAudioLen));
            RIFF_SIZE = revers(intToBytes(totalDataLen));
            FORMAT_SIZE = new byte[] { (byte) 16, (byte) 0, (byte) 0, (byte) 0 };
            byte[] tmp = revers(intToBytes(1));
            FORMATTAG = new byte[] { tmp[0], tmp[1] };
            CHANNELS = new byte[] { tmp[0], tmp[1] };
            SamplesPerSec = revers(intToBytes(16000));
            AvgBytesPerSec = revers(intToBytes(32000));
            tmp = revers(intToBytes(2));
            BlockAlign = new byte[] { tmp[0], tmp[1] };
            tmp = revers(intToBytes(16));
            BitsPerSample = new byte[] { tmp[0], tmp[1] };
            bos.write(RIFF);
            bos.write(RIFF_SIZE);
            bos.write(RIFF_TYPE);
            bos.write(FORMAT);
            bos.write(FORMAT_SIZE);
            bos.write(FORMATTAG);
            bos.write(CHANNELS);
            bos.write(SamplesPerSec);
            bos.write(AvgBytesPerSec);
            bos.write(BlockAlign);
            bos.write(BitsPerSample);
            bos.write(DataChunkID);
            bos.write(DataSize);
            bos.flush();
            bos.write(bytes);
            byte[] data = new byte[2];
            int len;
//            for(int i=0;i<bytes.length;i+=2){
//                data = new byte[2];
//                System.arraycopy(bytes,i,data,0,data.length);
//                byte temp=data[0];
//                data[0]=data[1];
//                data[1]=temp;
//                bos.write(data, 0, data.length);
//            }
//            while ((len = bis.read(data)) != -1) {
//                byte temp=data[0];
//                data[0]=data[1];
//                data[1]=temp;
//                bos.write(data, 0, len);
//            }
            bos.flush();
            fos.getFD().sync();
        } catch (Exception e) {
            throw e;
        } finally {
            if (fos != null){
                fos.close();
                fos = null;
            }
            if (bos != null){
                bos.close();
                bos = null;
            }
//            String filepath ="/sdcard/" + "aatest.wav";
//            File file1  = new File(filepath);
//            uniteFiles(filepath,file.getPath());
//            uniteFiles(file.getPath(),path);
            return file;
        }
    }

    /**
     * pcm文件转wav文件
     *
     * @param bytes pcm流
     */
    public static byte[] pcmToWav(byte[] bytes) {
        byte[] wavBytes=null;
        try {
            int totalAudioLen = bytes.length;
            int totalDataLen = totalAudioLen + 36;
            wavBytes=new byte[totalAudioLen+44];
            DataSize = revers(intToBytes(totalAudioLen));
            RIFF_SIZE = revers(intToBytes(totalDataLen));
            FORMAT_SIZE = new byte[] { (byte) 16, (byte) 0, (byte) 0, (byte) 0 };
            byte[] tmp = revers(intToBytes(1));
            FORMATTAG = new byte[] { tmp[0], tmp[1] };
            CHANNELS = new byte[] { tmp[0], tmp[1] };
            SamplesPerSec = revers(intToBytes(16000));
            AvgBytesPerSec = revers(intToBytes(32000));
            tmp = revers(intToBytes(2));
            BlockAlign = new byte[] { tmp[0], tmp[1] };
            tmp = revers(intToBytes(16));
            BitsPerSample = new byte[] { tmp[0], tmp[1] };
            int index=0;
            System.arraycopy(RIFF,0,wavBytes,index,RIFF.length);
            index+=RIFF.length;
            System.arraycopy(RIFF_SIZE,0,wavBytes,index,RIFF_SIZE.length);
            index+=RIFF_SIZE.length;
            System.arraycopy(RIFF_TYPE,0,wavBytes,index,RIFF_TYPE.length);
            index+=RIFF_TYPE.length;
            System.arraycopy(FORMAT,0,wavBytes,index,FORMAT.length);
            index+=FORMAT.length;
            System.arraycopy(FORMAT_SIZE,0,wavBytes,index,FORMAT_SIZE.length);
            index+=FORMAT_SIZE.length;
            System.arraycopy(FORMATTAG,0,wavBytes,index,FORMATTAG.length);
            index+=FORMATTAG.length;
            System.arraycopy(CHANNELS,0,wavBytes,index,CHANNELS.length);
            index+=CHANNELS.length;
            System.arraycopy(SamplesPerSec,0,wavBytes,index,SamplesPerSec.length);
            index+=SamplesPerSec.length;
            System.arraycopy(AvgBytesPerSec,0,wavBytes,index,AvgBytesPerSec.length);
            index+=AvgBytesPerSec.length;
            System.arraycopy(BlockAlign,0,wavBytes,index,BlockAlign.length);
            index+=BlockAlign.length;
            System.arraycopy(BitsPerSample,0,wavBytes,index,BitsPerSample.length);
            index+=BitsPerSample.length;
            System.arraycopy(DataChunkID,0,wavBytes,index,DataChunkID.length);
            index+=DataChunkID.length;
            System.arraycopy(DataSize,0,wavBytes,index,DataSize.length);
            index+=DataSize.length;
            System.arraycopy(bytes,0,wavBytes,index,bytes.length);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            return wavBytes;
        }
    }

    public static byte[] revers(byte[] tmp) {
        byte[] reversed = new byte[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            reversed[i] = tmp[tmp.length - i - 1];
        }
        return reversed;
    }

    public static byte[] intToBytes(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (num >> 24);
        bytes[1] = (byte) ((num >> 16) & 0x000000FF);
        bytes[2] = (byte) ((num >> 8) & 0x000000FF);
        bytes[3] = (byte) (num & 0x000000FF);
        return bytes;
    }

}
