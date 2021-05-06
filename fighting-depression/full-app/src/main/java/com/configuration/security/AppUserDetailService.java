package com.configuration.security;

import com.configuration.security.dto.UserVO;
import com.dao.CommonRepository;
import com.model.common.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author avinash.a.mishra
 */

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private CommonRepository commonRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = commonRepository.getUserByUserName(s);
        if(user==null){
            throw new UsernameNotFoundException("Not found :"+s);
        }
        UserVO u= new UserVO(user);
        return u;
    }
}
