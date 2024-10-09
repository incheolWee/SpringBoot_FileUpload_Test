package com.example.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/{userId}/sign")
@RequiredArgsConstructor
public class SignController {

    @Autowired
    private  SignService signService;

    // Create a new sign
    @PostMapping("/create")
    public ResponseEntity<SignDto> createSign(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        SignDto signDto = signService.createSign(userId, file);
        return ResponseEntity.ok(signDto);
    }

    // Update an existing sign
    @PutMapping("/update/{signId}")
    public ResponseEntity<SignDto> updateSign(
            @PathVariable Long userId,
            @PathVariable Long signId,
            @RequestParam("file") MultipartFile file) {
        SignDto updatedSign = signService.updateSign(userId, signId, file);
        return ResponseEntity.ok(updatedSign);
    }

    // Retrieve all signs for the user where deleted is false
    @GetMapping("/list")
    public ResponseEntity<List<SignDto>> getSignsForUser(@PathVariable Long userId) {
        List<SignDto> signs = signService.getSignsForUser(userId);
        return ResponseEntity.ok(signs);
    }

    // Retrieve a specific sign for the user
    @GetMapping("/list/{signId}")
    public ResponseEntity<SignDto> getSignById(@PathVariable Long userId, @PathVariable Long signId) {
        SignDto signDto = signService.getSignById(userId, signId);
        return ResponseEntity.ok(signDto);
    }

    // Mark a sign as deleted
    @DeleteMapping("/list/{signId}")
    public ResponseEntity<String> deleteSign(@PathVariable Long userId, @PathVariable Long signId) {
        signService.deleteSign(userId, signId);
        return ResponseEntity.ok("Sign successfully deleted.");
    }
}