package com.login.authentication.service;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.model.*;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.exceptions.EmailValidator;
import com.login.authentication.repository.NotificationsRepository;
import com.login.authentication.repository.ProfileRepository;
import com.login.authentication.user.Rol;
import com.login.authentication.user.User;
import com.login.authentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request, BindingResult result) {
        if(!EmailValidator.validateEmail(request.getEmail())){
            throw new ApiRequestExceptionValid("Formato correo incorrecto");
        }
        else{
            if (result.hasErrors()) {
                if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                    throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());
                } else {
                    throw new ApiRequestExceptionValid("Datos no validos");
                }

            } else {
                String email = request.getEmail();
                Optional<User> person = repository.findByEmail(email);

                if (!person.isPresent()){
                    var user = User.builder()
                            .firstname(request.getFirstname())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .idUser(request.getIdUser())
                            .password((passwordEncoder.encode(request.getPassword())))
                            .role(Rol.USER)
                            .build();
                    repository.save(user);
                    var jwtToken = jwtService.generateToken(user);


                    return AuthenticationResponse.builder()
                            .token(jwtToken)
                            .build();
                } else {
                    throw new ApiRequestExceptionValid("Usuario ya existe");
                }
            }
        }

    }
    public AuthenticationResponse authenticate(AuthenticatonRequest request, BindingResult result) {
            if (result.hasErrors()) {
               if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                   throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());
               } else {
                   throw new ApiRequestExceptionValid("Datos no validos");
               }

           } else {
               String email = request.getEmail();
               Optional<User> person = repository.findByEmail(email);

               if (person.isPresent()){
                   authenticationManager.authenticate(
                           new UsernamePasswordAuthenticationToken(
                                   request.getEmail(),
                                   request.getPassword()
                           )
                   );
                   var user = repository.findByEmail(request.getEmail())
                           .orElseThrow();
                   var jwtToken = jwtService.generateToken(user);
                   return AuthenticationResponse.builder()
                           .token(jwtToken)
                           .idUser(user.getIdUser())
                           .email(user.getUsername())
                           .role(String.valueOf(user.getRole()))
                           .build();

               } else {
                   throw new ApiRequestExceptionValid("Usuario no registrado");
               }
           }

    }

    public ResponseEntity<String> updatePassword(PasswordModel data, BindingResult result) {

            if (result.hasErrors()) {
                if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                    throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());
                } else {
                    throw new ApiRequestExceptionValid("Datos no validos");
                }

            } else {
                Optional<User> person = repository.findByEmail(data.getEmail());

                if (person.isPresent()){

                    String oldPassword = person.get().getPassword();
                    String newPassword = data.getNewPassword();

                    boolean passwordsMatch = passwordEncoder.matches(newPassword,oldPassword);
                    String encodedPassword = passwordEncoder.encode(newPassword);

                    if(passwordsMatch){
                        throw new ApiRequestExceptionValid("La contraseña es igual a la anterior ");
                    }else{
                        person.get().setPassword(encodedPassword);
                        repository.save(person.get());
                        return ResponseEntity.ok().body("La contraseña se actualizo correctamente");
                    }
                } else {
                    throw new ApiRequestExceptionValid("Usuario ya existe");
                }
            }
    }

    @AllArgsConstructor
    @Service
    public static class NotificationService {
        //private final SimpMessagingTemplate messagingTemplate;
        @Autowired
        private final NotificationsRepository repositorio;

        @Autowired
        private final ProfileRepository profileRepository;

        @Autowired
        private SimpUserRegistry simpUserRegistry;

        private Map<String, WebSocketSession> activeSessions;
       /* @Autowired
        public NotificationService(SimpMessagingTemplate messagingTemplate) {
            this.messagingTemplate = messagingTemplate;
        }

        public void sendGlobalNotification() {
            ResponseMessage message = new ResponseMessage("Global Notification");

            messagingTemplate.convertAndSend("/topic/global-notifications", message);
        }*/
       public ResponseEntity<List<Notifications>> getByUser(List<Notifications> data){
           if(data.isEmpty() || data == null) {
               throw new ApiRequestException("No hay datos para el usuario");
           }else {
               return ResponseEntity.status(HttpStatus.OK).body(data);
           }
       }

        public ResponseEntity<Notifications> updateById(Notifications data, BindingResult result){
            if (result.hasErrors()) {
                if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                    throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());
                } else {
                    throw new ApiRequestExceptionValid("Datos no validos");
                }

            } else {
                    Optional<Notifications> notifications = repositorio.findByMessageId(data.getMessageId());
                    if (!notifications.isEmpty()){
                        Notifications notification = notifications.get();
                        Boolean state = notification.getState();
                    if(state){
                        return ResponseEntity.ok().body(notification);
                    }else{
                        notification.setState(true);
                        repositorio.save(notification);
                        return ResponseEntity.ok().body(notification);
                    }
                } else {
                    throw new ApiRequestExceptionValid("El registro ya existe");
                }
            }
        }

        public void sendPrivateNotification(Notifications notifications) {

                String email = notifications.getDestination();
                Optional<Profile> data = profileRepository.findByEmail(email);
                //System.out.println("notifications --->"+ notifications.getMessageId() + "notifications "+ notifications.getContent());
                if(!data.isEmpty()){
                    notifications.setMessageId(notifications.getMessageId().toString());
                    notifications.setEmail(email);
                    repositorio.save(notifications);
                }else{
                    throw new ApiRequestExceptionValid("Destino no existe");
                }
        }
    }
}
