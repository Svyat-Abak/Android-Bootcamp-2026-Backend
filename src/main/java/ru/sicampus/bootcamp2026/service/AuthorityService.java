package ru.sicampus.bootcamp2026.service;

import ru.sicampus.bootcamp2026.entity.Authority;

import java.util.List;

public interface AuthorityService {
    Authority add(Authority authority);

    List<Authority> getAll();
}