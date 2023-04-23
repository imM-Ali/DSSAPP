
  package com.web.dssapp.service;
  
  import com.web.dssapp.dto.UserDto; import com.web.dssapp.model.Role; import
  com.web.dssapp.model.User; import com.web.dssapp.repository.RoleRepository;
  import com.web.dssapp.repository.UserRepository;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.security.crypto.password.PasswordEncoder; import
  org.springframework.stereotype.Service;
  
  import java.util.Arrays; import java.util.List; import
  java.util.stream.Collectors;
  
  @Service public class UserServiceImpl implements UserService {
  
  @Autowired private UserRepository userRepository; private RoleRepository
  roleRepository; private PasswordEncoder passwordEncoder;
  
  public UserServiceImpl(UserRepository userRepository, RoleRepository
  roleRepository, PasswordEncoder passwordEncoder) { this.userRepository =
  userRepository; this.roleRepository = roleRepository; this.passwordEncoder =
  passwordEncoder; }
  
  @Override public void saveUser(UserDto userDto) { User user = new User();
  user.setName(userDto.getFirstName() + " " + userDto.getLastName());
  user.setEmail(userDto.getEmail());
  
  //encrypt the password once we integrate spring security
  //user.setPassword(userDto.getPassword());
  user.setPassword(passwordEncoder.encode(userDto.getPassword())); Role role =
  roleRepository.findRoleByName("ROLE_ADMIN"); if(role == null){ role =
  checkRoleExist(); } user.setRoles(Arrays.asList(role));
  userRepository.save(user); }
  
  @Override public User findByEmail(String email) { return
  userRepository.findUserByEmail(email); }
  
  @Override public List<UserDto> findAllUsers() { List<User> users =
  userRepository.findAll(); return users.stream().map((user) ->
  convertEntityToDto(user)) .collect(Collectors.toList()); }
  
  private UserDto convertEntityToDto(User user){ UserDto userDto = new
  UserDto(); String[] name = user.getName().split(" ");
  userDto.setFirstName(name[0]); userDto.setLastName(name[1]);
  userDto.setEmail(user.getEmail()); return userDto; }
  
  private Role checkRoleExist() { Role role = new Role();
  role.setName("ROLE_ADMIN"); return roleRepository.save(role); }
  
  
  }
 