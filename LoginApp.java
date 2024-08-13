import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


public class LoginApp {
    // private dict
    static HashMap<String, byte[]> all_credentials = new HashMap<>();

    public static void main(String[] args) {
        // filling hashmap
        all_credentials.put("username", null);
        all_credentials.put("rohith", null);
        all_credentials.put("abe", null);

        try {
            String tempstr = "password";
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash1 = digest.digest(tempstr.getBytes(StandardCharsets.UTF_8));
            byte[] hash2 = digest.digest("secret".getBytes(StandardCharsets.UTF_8));
            byte[] hash3 = digest.digest("helloworld".getBytes(StandardCharsets.UTF_8));
            System.out.println("HASHED!");
            all_credentials.put("username", hash1);
            all_credentials.put("rohith", hash2);
            all_credentials.put("abe", hash3);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("NOT ABLE TO HASH PASS.");
            String temp = String.format("ERROR: %s\n", e);
            System.out.println(temp);

        }

        // variables
        String username;
        String password;
        boolean result;

        // get user
        Scanner in_params = new Scanner(System.in, "UTF-8");
        System.out.println("Input Username:");
        username = in_params.nextLine();

        // get pass
        System.out.println("Input Password:");
        password = in_params.nextLine();

        // validate and return result
        result = validate_credentials(username, password);
        in_params.close();
        String temp = String.format("return value is: %b\n", result);
        System.out.println(temp);

    }

    public static boolean validate_credentials(String user, String pass) {
        // create hash of pass

        try {
            byte[] temp_hash;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            temp_hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
            String final_hash = new String(temp_hash);
            if (final_hash.equals(new String(all_credentials.get(user)))) {
                System.out.println("HASH PASS MATCHES!");
                return true;
            } else {
                System.out.println("USER/PASS DOES NOT MATCH.");
            }
        } catch (NoSuchAlgorithmException e) {
            String error = String.format("ERROR: %s\n", e);
            System.out.println(error);
        }

        return false;
    }

}