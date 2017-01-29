package com.sp.allinone.common;

/**
 * Created by i82298 on 1/29/2017.
 */

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Cloner {
    private static Logger logger = LoggerFactory.getLogger(Cloner.class);

    /**
     * serializes the object
     *
     * @param obj
     *            Object to be serialized
     * @return serialized version of object
     */
    private static byte[] serialize(Object obj) {
        byte[] retArray = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
            bos.close();

            retArray = bos.toByteArray();

        } catch (IOException e) {
            logger.error("IOException occured in Cloner->serialize()", e);
        } catch (NullPointerException ne) {
            logger.error("NullPointerException occured in Cloner->serialize()",
                    ne);
        }

        return retArray;
    }

    /**
     * deserializes byte array to an object
     *
     * @param inArray
     *            serialized version of object
     * @return Object to be serialized
     */
    private static Object deserialize(byte[] inArray) {
        Object retValue = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(inArray);
            ObjectInputStream ois = new ObjectInputStream(bis);
            ois.close();
            bis.close();
            retValue = ois.readObject();

        } catch (IOException e) {
            logger.error("IOException occured in Cloner->deserialize()", e);
        } catch (ClassNotFoundException e) {
            logger.error(
                    "ClassNotFoundException occured in Cloner->deserialize()",
                    e);
        } catch (NullPointerException ne) {
            logger.error(
                    "NullPointerException occured in Cloner->deserialize()", ne);
        }
        return retValue;
    }

    public static <T> T cloneThis(T src) {
        return (T) deserialize(serialize(src));
    }

}
