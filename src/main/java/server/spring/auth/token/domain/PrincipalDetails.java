package server.spring.auth.token.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import server.spring.auth.user.domain.User;
import server.spring.auth.user.enums.RoleType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrincipalDetails implements UserDetails, CredentialsContainer {

    private User user;

    private List<RoleType> role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (!(this.role.isEmpty()) && this.role != null) {

            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (RoleType currentRole : this.role) {
                authorities.add(new SimpleGrantedAuthority(currentRole.getCode()));
            }
            return authorities;
        }
        return null;
    }

    // 일반 시큐리티 로그인시 사용
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {return user.getUserId();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // ?
    @Override
    public void eraseCredentials() {}

    public void setRoleWithList(List<String> roleList) {
        this.role =
            roleList.stream().map(role->RoleType.valueOfCode(role)).toList();
    }

    public void setUserWithId(Long userId) {
        User user = new User();
        user.setId(userId);
        this.user = user;
    }
}
