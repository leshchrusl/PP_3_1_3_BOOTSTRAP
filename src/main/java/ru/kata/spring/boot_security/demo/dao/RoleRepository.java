package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;


public interface RoleRepository {

    public List<Role> getAllRoles();
    public List<Role> findRolesById(List<Long> id);
    public void saveRole(Role role);
}
