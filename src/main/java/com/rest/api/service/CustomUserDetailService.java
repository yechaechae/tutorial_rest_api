package com.rest.api.service;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
        private final UserJpaRepo userJpaRepo;

        public UserDetails loadUserByUsername(String userPk){
            //return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
            return userJpaRepo.findByUid(userPk).orElseThrow((CUserNotFoundException::new));
            //return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
        }
}
