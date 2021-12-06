package by.epam.task6003.serverPart.service.impl;

public class PasswordEncryptor {
    public String decryptPassword(String encryptedPassword) {
        char[] encryptedPasswordChars = encryptedPassword.toCharArray();
        char[] symbols = "abcdefghijklmnopqrstuvwxyzABCDEGGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] decryptedPasswordChars = new char[encryptedPasswordChars.length];
        String decryptedPassword;

        for (int i = 0; i < encryptedPassword.length(); i++) {
            for (int j = 0; j < 62; j++) {
                if (symbols[j] == encryptedPasswordChars[i]) {
                    decryptedPasswordChars[i] = symbols[(j + 7) % 62];
                }
            }
        }

        decryptedPassword = String.valueOf(decryptedPasswordChars);
        return decryptedPassword;
    }

    public String encryptPassword(String decryptedPassword) {
        char[] decryptedPasswordChars = decryptedPassword.toCharArray();
        char[] symbols = "abcdefghijklmnopqrstuvwxyzABCDEGGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] encryptedPasswordChars = new char[decryptedPasswordChars.length];
        String encryptedPassword;

        for (int i = 0; i < decryptedPassword.length(); i++) {
            for (int j = 0; j < 62; j++) {
                if (symbols[j] == decryptedPasswordChars[i]) {
                    encryptedPasswordChars[i] = ((j >= 7) ? symbols[j - 7] : symbols[62 + j - 7]);
                }
            }
        }

        encryptedPassword = String.valueOf(encryptedPasswordChars);
        return encryptedPassword;
    }
}
