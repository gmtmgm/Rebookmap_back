

package BookMap.PentaRim.Login.Controller;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j

public class SeverAccessController {

    /*

    String CLIENT_SECRET_FILE = "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.json";
    String REDIRECT_URI = "http://localhost:8080/login/oauth2/code/google";


    public HashMap<String, String> getAuthCode(@RequestBody @Valid HashMap<String, String> authCode, HttpServletRequest request) {

        if (request.getHeader("X-Requested-With") == null) {
            log.info("검증된 데이터가 아닙니다");
            return null;
        } else {
            return authCode;
        }

    }

    public List<String> getClientFileReader(String CLIENT_SECRET_FILE) {
        try(
                FileReader fileReader = new FileReader(CLIENT_SECRET_FILE);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException | java.io.IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public GoogleClientSecrets getClientSecret(FileReader fileReader) {
       try {
           return GoogleClientSecrets.load(
                   JacksonFactory.getDefaultInstance(), fileReader);
       } catch (java.io.IOException e) {
           log.info("구글 시크릿 관련 입출력 예외");
           throw new RuntimeException(e);
       }
    }


    public String getAccessToken(HashMap<String, String> authCode) {

        try {
            GoogleClientSecrets clientSecrets =
                    getClientSecret((FileReader)getClientFileReader(CLIENT_SECRET_FILE));
            GoogleTokenResponse tokenResponse =
                    new GoogleAuthorizationCodeTokenRequest(
                            new NetHttpTransport(),
                            JacksonFactory.getDefaultInstance(),
                            "https://oauth2.googleapis.com/token",
                            clientSecrets.getDetails().getClientId(),
                            clientSecrets.getDetails().getClientSecret(),
                            authCode.values().toString(),
                            REDIRECT_URI)  // Specify the same redirect URI that you use with your web
                            // app. If you don't have a web version of your app, you can
                            // specify an empty string.
                            .execute();

            return tokenResponse.getAccessToken();
        } catch (java.io.IOException e) {
            log.info("토큰 리퀘스트 입출력 예외");
            throw new RuntimeException(e);
        }


    }
    @PostMapping("/accessToken")
    public void callAPI(@RequestBody @Valid HashMap<String, String> authCode, HttpServletRequest request) {
        HashMap<String,String> authcode = getAuthCode(authCode, request);
        List<String> fileString = getClientFileReader(CLIENT_SECRET_FILE);
        String accessToken = getAccessToken(authcode);

        GoogleCredentials credentials = GoogleCredentials.create();
        Drive drive =
                new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                        .setApplicationName("Auth Code Exchange Demo")
                        .build();
        File file = drive.files().get("appfolder").execute();
    }

     */

    /*

    @PostMapping("/accessToken")
    public void callAPI(@RequestBody @Valid AccessToken accessToken, HttpServletRequest request) {
        GoogleCredentials googleCredentials =  GoogleCredentials.create(accessToken);

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredentials);

        try {
                NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            } catch (GeneralSecurityException e) {
                log.info("구글 크레덴셜 사용 도중 보안 에러");
                throw new RuntimeException(e);
            } catch (IOException e) {
                log.info("구글 크레덴셜 사용 도중 입출력 에러");
                throw new RuntimeException(e);
            }



    }

     */

}






