package com.txt;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @Description :
 * @Date : 2022-01-02
 */
public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Object o = new HelloClassLoader().findClass("Hello").newInstance();
            Method method = o.getClass().getDeclaredMethod("hello");
            method.invoke(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = Thread.currentThread().getContextClassLoader().getResource(name + ".xlass").getPath();
        File file = new File(path);
        byte[] bytes = decode(toByteArray(file));
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * @Description: 将文件转换成字节数组
     *
     * @param file
     * @return byte[]
     * @Date: 2022/1/2
     */
    public static byte[] toByteArray(File file) {
        if(!file.exists()) {
            return null;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            int bufSize = 256;
            byte[] bytes = new byte[bufSize];
            int len = 0;

            while(-1 != (len = in.read(bytes, 0, bufSize))) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                in.close();
                bos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return bos.toByteArray();
    }

    public static byte[] decode(byte[] bytes) {
        byte[] targetBytes = new byte[bytes.length];

        for(int i = 0; i < bytes.length; i++) {
            targetBytes[i] = (byte) (255 - bytes[i]);
        }

        return targetBytes;
    }
}
