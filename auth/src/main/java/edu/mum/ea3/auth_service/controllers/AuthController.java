package edu.mum.ea3.auth_service.controllers;

import edu.mum.ea3.auth_service.configs.JwtTokenUtil;
import edu.mum.ea3.auth_service.configs.JwtUserService;
import edu.mum.ea3.auth_service.entities.UserEntity;
//import edu.mum.ea3.auth_service.models.Messages;
import edu.mum.ea3.auth_service.models.SignInCredentials;
import edu.mum.ea3.auth_service.repos.UsersRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	UsersRepo usersRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserService userDetailsService;

//	@Autowired
//	private KafkaTemplate<String, Messages> kafkaTemplate;
//	private static final String TOPIC = "Carpool_Token";


	@GetMapping("/")
	public ResponseEntity<?> index() {
		String host = "Unknown host";
		try {
			host = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("auth-service. Host: " + host, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody UserEntity user) throws Exception {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		usersRepo.save(user);
		return ResponseEntity.ok("User added successfully");
	}

	@GetMapping("/getAllUsers")
	public List<UserEntity> getAllUsers(){
		Iterable<UserEntity> result = usersRepo.findAll();
		List<UserEntity> userEntityList = new ArrayList<>();
		result.forEach(userEntityList::add);
		return userEntityList;
	}

	@PostMapping(value = "/sign-in")
	public ResponseEntity<String> singIn(@RequestBody SignInCredentials signInCredentials) throws Exception {
		try {
			log.info("==sign-in=={}{}",signInCredentials.getEmail(), signInCredentials.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInCredentials.getEmail(), signInCredentials.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		} catch (Exception ex){
			log.error(ex.getMessage());
		}

		log.info("Garaad irlee");

		final UserDetails userDetails = userDetailsService.loadUserByUsername(signInCredentials.getEmail());
		log.info("userDetails = {}", userDetails);
		final String token = jwtTokenUtil.generateToken(userDetails);

//		Messages messages = new Messages();
//		messages.setToken(token);
//		messages.setUserId(usersRepo.findByEmail(signInCredentials.getEmail()).getId());
//		kafkaTemplate.send(TOPIC, messages);

		//kafkaTemplate.send(TOPIC, new Messages(token, usersRepo.findByEmail(signInCredentials.getEmail()).getId())

		return ResponseEntity.ok(token);
	}



//	@GetMapping("/publish")
//	public String post(@PathVariable("userId") final Messages messages){
//
//		//kafkaTemplate.send(TOPIC, new Messages(msg.getToken(), msg.getUserId()));
//		kafkaTemplate.send(TOPIC, messages);
//		return "Published Successfully";
//	}




	@PutMapping("/update/{id}")
	public Optional<UserEntity> updateUser(@RequestBody UserEntity newUser, @PathVariable int id){
		Optional<UserEntity> optionalUser = Optional.ofNullable(usersRepo.findById(id));
		if(optionalUser.isPresent()){
			UserEntity user = optionalUser.get();
			user.setId(newUser.getId());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setPassword(newUser.getPassword());
			user.setEmail(newUser.getEmail());
			user.setAdrStreet(newUser.getAdrStreet());
			user.setAdrCity(newUser.getAdrCity());
			user.setAdrState(newUser.getAdrState());
			user.setAdrZip(newUser.getAdrZip());
		}
		return optionalUser;
	}

	@GetMapping("/getUserById/{id}")
	public Optional<UserEntity> getUserById(@PathVariable int id){
		Optional<UserEntity> userOptional = Optional.ofNullable(usersRepo.findById(id));
		return userOptional;
	}

	@DeleteMapping(value = "/delete/{id}"/*, produces = "application/json; charset=utf-8"*/)
	public String deleteUser(@PathVariable int id){
		boolean result = usersRepo.existsById(id);
		usersRepo.deleteById(id);
		return "{ \"success\" : "+ (result ? "true" : "false") +" }";
	}

//	@DeleteMapping(value = "/delete/{email}"/*, produces = "application/json; charset=utf-8"*/)
//	public String deleteUser(@PathVariable String email){
//		//boolean result = usersRepo.existsById(email);
//		usersRepo.deleteByEmail(email);
//		//return "{ \"success\" : "+ (result ? "true" : "false") +" }";
//		return "success deleted";
//	}
}
