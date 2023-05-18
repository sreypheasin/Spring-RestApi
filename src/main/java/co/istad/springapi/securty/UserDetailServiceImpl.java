package co.istad.springapi.securty;

import co.istad.springapi.api.auth.AuthMapper;
import co.istad.springapi.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final AuthMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.loadUserByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User is not valid!"));
        CustomerUserDetail customerUserDetail = new CustomerUserDetail();
        customerUserDetail.setUser(user);
        return customerUserDetail;
    }
}
