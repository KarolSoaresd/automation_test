package provider;

import lombok.Getter;
import lombok.Setter;

public class GeradorLogin {

public static Login obterLoginPadrao() {
    String username = "kminchelle";
    String password = "0lelplR";


    return new Login (username, password);
}

    @Getter
    @Setter
    private static String token;
}
