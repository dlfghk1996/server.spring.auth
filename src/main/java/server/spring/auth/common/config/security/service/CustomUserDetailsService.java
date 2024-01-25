package server.spring.auth.common.config.security.service;


import jakarta.transaction.Transactional;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.spring.auth.common.config.exception.ServiceException;
import server.spring.auth.common.enums.ResponseCode;
import server.spring.auth.token.domain.PrincipalDetails;
import server.spring.auth.user.domain.User;
import server.spring.auth.user.enums.RoleType;
import server.spring.auth.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

     private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        User user = userRepository.findByUserId(userId)
            .orElseThrow(() -> new ServiceException(ResponseCode.MEMBER_INVALID_ACCOUNT));

        System.out.println(user.toString());
//        List<RoleType> memRoleList = new ArrayList<RoleType>();
//        List<RoleRelation> roleRelationList = roleRepository.findAllByMemId(
//            member.getId());
//
//        for (RoleRelation r : roleRelationList) {
//
//            Role role = repository.findById(r.getRoleId())
//                .orElseThrow(() -> new MommaOfficeException(ResponseCode.UNKNOWN));
//            System.out.println("해당 회원의 ROleType");
//            System.out.println(role.getRoleType());
//
//            memRoleList.add(role.getRoleType());
//        }

        return new PrincipalDetails(user, Arrays.asList(RoleType.USER));
    }
}
