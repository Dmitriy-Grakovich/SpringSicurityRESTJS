package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.forms.UserDTO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import java.util.HashSet;
import java.util.List;

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
   public void update(UserDTO userDTO, String roleUser) {
      User user = userRepository.findById(userDTO.getId()).orElseThrow();
      user.setAge(userDTO.getAge());
      user.setName(userDTO.getName());
      user.setLastName(userDTO.getLastName());
      user.setEmail(userDTO.getEmail());
      if(userDTO.getPassword()!=null) {
         user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
      }
      Role role = roleRepository.getRoleByName(roleUser);
         user.getRoles().clear();
         user.addRole(role);

      userRepository.save(user);
   }

   @Override
   public void delete(Long id) {
      User user = userRepository.findById(id).orElseThrow();
      user.getRoles().clear();
      userRepository.save(user);
      userRepository.delete(user);
   }

   @Override
   public User getUserByName(String name) {
      return userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
   }

   @Override
   public void save(UserDTO userDTO, String roleName) {
      User newUser = null;
      Role role = roleRepository.getRoleByName(roleName);

      if (newUser == null && userDTO.getAge()!=null && userDTO.getLastName()!=null && userDTO.getName()!=null && userDTO.getPassword()!=null && userDTO.getEmail()!=null) {
         newUser = User.builder()
                 .age(userDTO.getAge())
                 .lastName(userDTO.getLastName())
                 .name(userDTO.getName())
                 .password(passwordEncoder.encode(userDTO.getPassword()))
                 .email(userDTO.getEmail())
                 .roles(new HashSet<>())
                 .build();
      }
      newUser.addRole(role);
      userRepository.save(newUser);
   }

   @Override
   @Transactional(readOnly = true)
   public User getUserByEmail(String name) {
      return userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
   }

   @Override
   @Transactional(readOnly = true)
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
      return user;
   }
}
