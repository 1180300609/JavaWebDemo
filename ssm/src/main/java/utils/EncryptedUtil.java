package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class EncryptedUtil {
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    //盐的长度
    public static final int SALT_SIZE = 16;

    //生成密文的长度
    public static final int HASH_SIZE = 32;

    // 迭代次数
    public static final int PBKDF2_ITERATIONS = 1000;

    /**
     * 对输入的密码进行验证
     */
    public static boolean verify(String password, String salt, String key) {
        // 用相同的盐值对用户输入的密码进行加密
        String result = getPBKDF2(password, salt);
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return result.equals(key);
    }

    /**
     * 根据password和salt生成密文
     */
    public static String getPBKDF2(String password, String salt) {
        //将16进制字符串形式的salt转换成byte数组
        byte[] bytes = DatatypeConverter.parseHexBinary(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), bytes, PBKDF2_ITERATIONS, HASH_SIZE * 4);
        SecretKeyFactory secretKeyFactory = null;
        try {
            secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = new byte[0];
        try {
            if (secretKeyFactory != null) {
                hash = secretKeyFactory.generateSecret(spec).getEncoded();
            }
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        //将byte数组转换为16进制的字符串
        return DatatypeConverter.printHexBinary(hash);
    }


    /**
     * 生成随机盐值
     */
    public static String getSalt() {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[SALT_SIZE / 2];
        if (random != null) {
            random.nextBytes(bytes);
        }
        //将byte数组转换为16进制的字符串
        return DatatypeConverter.printHexBinary(bytes);
    }
}
