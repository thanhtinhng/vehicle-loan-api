/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

import Model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Windows 10
 */
public class TokenManager {

    //dung static vi mac dinh chi co 1 TokenManager duy nhat, khi su dung thi khong can khoi tao
    private static Map<String, User> tokenStore = new HashMap<>();
    private static Map<String, String> emailToTokenMap = new HashMap<>();

    public static String saveToken(User user) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, user);
        emailToTokenMap.put(user.getEmail(), token);
        return token;
    }
    
    public static String findUserToken(String email) {
        if (emailToTokenMap.containsKey(email)) {
            return emailToTokenMap.get(email);
        }
        return null;
    }

    public static User getUser(String token) {
        return tokenStore.get(token);
    }

    public static void setTokenStore(Map<String, User> tokenStore) {
        TokenManager.tokenStore = tokenStore; //vi static nen khong dung this
    }

}
