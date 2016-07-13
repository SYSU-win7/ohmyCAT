package website.ohmyCat.services;

import java.util.HashSet;

public class ActiveUsers {
    private static HashSet<String> activeUsers = new HashSet<String>();
    
    public static boolean addUser(String username)
    {
    	return activeUsers.add(username);
    }
    
    public static boolean removeUser(String object)
    {
    	return activeUsers.remove(object);
    }
    
    public static void printUsers()
    {
    	System.out.println(activeUsers.toString());
    }
    
    public static String basePath = "D:\\CatData\\";
}
