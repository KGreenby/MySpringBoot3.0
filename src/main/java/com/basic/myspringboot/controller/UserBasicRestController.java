package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserBasicRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
//        if (optianlUser.isPresent()) {
//            User user = optionalUser.get();
//            return user;
//        }

        // orElseThrow(Supplier) Supplier의 추상메서드 T get()
        User user = optionalUser.orElseThrow(() -> new BusinessException("user not found", HttpStatus.NOT_FOUND));
        return user;

    }

    @GetMapping("/email/{email}")
    public User getUserByEamil(@PathVariable String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("요청하신 이메일에 해당하는 유저가 없습니다.", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
        //return ResponseEntity.ok(user);
        return ResponseEntity.ok(id + " User가 삭제 되었습니다.");
    }
}
