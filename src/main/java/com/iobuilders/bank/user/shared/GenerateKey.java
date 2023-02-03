package com.iobuilders.bank.user.shared;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class GenerateKey {
        public static String generateRandomIdWithSeed(int length) {
            byte [] randomBytes = new byte[0];
            try {
                randomBytes = generateRandomIdBytesWithSeed(length);
            } catch (NoSuchAlgorithmException e) {
                throw new SecurityException("Cannot generate security token: " + e.getMessage());
            }
            return Base64.getEncoder().withoutPadding().encodeToString(randomBytes).replace("+", "a").replace("/", "b")
                    .replace(" ", "c");
        }

        private static byte[] generateRandomIdBytesWithSeed(int length) throws NoSuchAlgorithmException {
            String os = System.getProperty("os.name");
            String algorithm = "NativePRNGNonBlocking";
            if (os.startsWith("Windows") || os.startsWith("Mac OS X"))
                algorithm = "SHA1PRNG";
            SecureRandom sha1Random = SecureRandom.getInstance(algorithm);
            byte [] seed = sha1Random.generateSeed(8);
            sha1Random.setSeed(seed);
            byte [] values = new byte[length];
            sha1Random.nextBytes(values); // SHA1PRNG, seeded properly
            return values;
        }

}
