package com.filezipper.utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialization {
    public static Object deserialize(Byte[] data) throws IOException, ClassNotFoundException {
        byte[] binaryData = new byte[data.length];
        int ind=0;
        for(byte b: data){
            binaryData[ind++]=b;
        }
        ByteArrayInputStream bit = new ByteArrayInputStream(binaryData);
        ObjectInputStream oit = new ObjectInputStream(bit);
        Object object = oit.readObject();
        bit.close();
        oit.close();

        return object;
    }
}
