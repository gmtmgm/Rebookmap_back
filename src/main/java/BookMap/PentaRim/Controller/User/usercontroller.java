package BookMap.PentaRim.Controller.User;

import BookMap.PentaRim.User.UserRequestDto;
import BookMap.PentaRim.User.UserResponseDto;
import BookMap.PentaRim.User.UserUpdateRequestDto;
import BookMap.PentaRim.service.UserService;
import BookMap.PentaRim.service.UserServiceImpl;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class usercontroller {

    private final UserService userService;

    /*
    @PostMapping("/user/login")
    public Long login(@RequestBody String token) throws FirebaseAuthException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseToken decodedToken = auth.verifyIdToken(token);
        String uid = decodedToken.getUid();
        return userService.findByUID(uid);
    }

     */


    @PostMapping("/user/register")
    public Long save(@RequestBody UserRequestDto userRequestDto){
        /*

        FileInputStream serviceAccount = new FileInputStream("src/main/resources/fentarim-c479e-firebase-adminsdk-dxw3c-88ed7da0d2.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("firebase-adminsdk-dxw3c@fentarim-c479e.iam.gserviceaccount.com")
                .build();
        FirebaseApp.initializeApp(options);

        String uid = "OG20itL7JcamcaMLzobggBHKtV02";  //임의로 가져온 uid

        //String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
        /**
         *uid 가져오면 이렇게 사용가능함!!!!!
        FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(uid);
        String email = firebaseToken.getEmail();



        FirebaseAuth auth = FirebaseAuth.getInstance();
        UserRecord userRecord = auth.getUser(uid);

        String name = userRecord.getEmail();

        //String userid = FirebaseAuth.getInstance().getUser
        //UserRecord userRecord = FirebaseAuth.getInstance().getUser("V--);
        userRequestDto.setUid(uid);
        userRequestDto.setNickname(name);

        */
        return userService.save(userRequestDto);
    }

    @PutMapping("/user/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return userService.update(id, userUpdateRequestDto);
    }

    @GetMapping("/user/{id}")
    public UserResponseDto findById(@PathVariable Long id){
        return userService.findByID(id);
    }




}
