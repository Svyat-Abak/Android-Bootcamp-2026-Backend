package ru.sicampus.bootcamp2026.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sicampus.bootcamp2026.entity.Authority;
import ru.sicampus.bootcamp2026.repository.AuthorityRepository;
import ru.sicampus.bootcamp2026.service.AuthorityService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority add(Authority authority) {

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority.getAuthority());

        //return optionalAuthority.orElseGet(() -> authorityRepository.save(authority));

        if (optionalAuthority.isPresent()) return optionalAuthority.get();
        else return authorityRepository.save(authority);
    }

    @Override
    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }
}
