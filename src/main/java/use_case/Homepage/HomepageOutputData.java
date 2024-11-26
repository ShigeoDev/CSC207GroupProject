package use_case.Homepage;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomepageOutputData {
    private String username;

    public HomepageOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
