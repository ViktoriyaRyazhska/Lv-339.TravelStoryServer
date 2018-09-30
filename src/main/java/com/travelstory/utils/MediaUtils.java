package com.travelstory.utils;

import com.cloudinary.Cloudinary;
import lombok.experimental.UtilityClass;
import org.modelmapper.internal.bytebuddy.utility.RandomString;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class MediaUtils {

    public static final String CLOUDINARY_URL = "cloudinary://792535656257531:m7XFfepEhHMDNvT7-pJleAr2Kbg@travelstory";

    /**
     * @param imageInBase64
     * @param folderOnCloud
     *            existing folders "media", "profile". If enter other folder name, it will be created automatically.
     *            Example of creating subfolder "media/video"
     * @return url of uploaded image
     * @throws IOException
     */

    public static String uploadMediaOnCloud(String imageInBase64, String folderOnCloud) throws IOException {
        byte[] decodedImg = decodeMedia(imageInBase64);

        Map<String, String> result = new HashMap();

        String nameOnCloud = RandomString.make(15);

        result.put("public_id", folderOnCloud + "/" + nameOnCloud);

        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        Map uploadResult = cloudinary.uploader().upload(decodedImg, result);
        return uploadResult.get("url").toString();
    }

    /**
     * @param imageInBase64
     * @param values
     *            existing specifications:
     *            https://cloudinary.com/documentation/java_integration#java_getting_started_guide
     * @return url of uploaded image
     * @throws IOException
     */

    @SuppressWarnings("unchecked")
    public static String uploadMediaOnCloud(String imageInBase64, String... values) throws IOException {

        if (values.length % 2 != 0) {
            throw new RuntimeException("Usage - (key, value, key, value, ...)");
        }
        byte[] decodedImg = decodeMedia(imageInBase64);
        Map result = new HashMap(values.length / 2);
        for (int i = 0; i < values.length; i += 2) {
            result.put(values[i], values[i + 1]);
        }
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        Map uploadResult = cloudinary.uploader().upload(decodedImg, result);
        return uploadResult.get("url").toString();
    }

    /**
     * @param media
     *            media file in base64
     * @return encoded file as array of bytes
     */
    public static byte[] decodeMedia(String media) {
        return Base64.getDecoder().decode(media);
    }
}
