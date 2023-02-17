package com.filezipper.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialization {
    public static Byte[] serialize(Object object)throws IOException {
        ByteArrayOutputStream bot = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bot);
        out.writeObject(object);
        byte[] binaryData = bot.toByteArray();

        bot.close();
        out.close();

        Byte[] data = new Byte[binaryData.length];
        int ind=0;
        for(Byte b: binaryData){
            data[ind++]=b;
        }
        return data;
    }
}
