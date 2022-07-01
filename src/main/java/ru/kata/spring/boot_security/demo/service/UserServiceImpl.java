package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.forms.UserForms;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import javax.persistence.SecondaryTable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

   private final RoleRepository roleRepository;
   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;

   @Override
   @Transactional(readOnly = true)
   public List<User> allUser() {
      return userRepository.findAll();
   }

   @Override
   @Transactional(readOnly = true)
   public User getUserById(long id) {
      return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
   }

   @Override
   public void update(Long id, String name, String lastName, Integer age) {
      User user = userRepository.findById(id).orElseThrow();
      user.setAge(age);
      user.setName(name);
      user.setLastName(lastName);
      userRepository.save(user);
   }

   @Override
   public void delete(Long id) {
      userRepository.delete(getUserById(id));
   }

   @Override
   public User getUserByName(String name) {
      return userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
   }

   @Override
   public void save(UserForms userForms) {
      User newUser = null;
      Role role = roleRepository.getRoleByName("ROLE_USER");

      if( newUser == null) {
         newUser = User.builder()
                 .age(userForms.getAge())
                 .lastName(userForms.getLastName())
                 .name(userForms.getName())
                 .password(passwordEncoder.encode(userForms.getPassword()))
                 .roles(new HashSet<>())
                 .build();
      }
      newUser.addRole(role);
      userRepository.save(newUser);
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
      return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),mapToRoles(user.getRoles()));
   }
   private Collection<? extends GrantedAuthority> mapToRoles(Collection<Role> roles){
      return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
   }

}
