package BookMap.PentaRim.User;
;
import BookMap.PentaRim.User.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private Map<String, Object> attributes;




    @Override
    public String getPassword() { return user.getEmail(); }

    @Override
    public String getUsername() { return user.getUsername();}

    //계정 만료 여부 true:만료 안 됨 false:만료됨

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부 true안잠김 false잠김
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비번 만료 여부 true 만료 안 됨 false 만료
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //사용자 활성화 여부 true 만료 안됨 false만료됨
    @Override
    public boolean isEnabled() {
        return true;
    }

    //유저 권한 목록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(()-> "ROLE_"+user.getRole());

        return collectors;
    }

}
