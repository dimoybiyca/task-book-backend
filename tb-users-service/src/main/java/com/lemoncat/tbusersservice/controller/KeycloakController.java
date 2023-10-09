package com.lemoncat.tbusersservice.controller;

import com.lemoncat.tbusersservice.DTO.ChangeUsernameRequest;
import com.lemoncat.tbusersservice.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping(path = "user")
@RequiredArgsConstructor
public class KeycloakController {

    private final KeycloakService service;

    @GetMapping()
    public Jwt getData(@AuthenticationPrincipal Jwt principal) {
        System.out.println(principal.getSubject());
        return principal;
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.OK)
    public void updateUsername(
            @AuthenticationPrincipal Jwt principal,
            @RequestBody ChangeUsernameRequest request){
        System.out.println(principal.getSubject());
        service.updateUsername(principal.getSubject(), request);
    }

    @DeleteMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout( @AuthenticationPrincipal Jwt principal) {
        service.logout(principal.getSubject());
    }

    @GetMapping(
            value = "avatar",
            produces = "image/webp")
    public @ResponseBody byte[] getUserAvatar(@AuthenticationPrincipal Jwt principal) {

        String avatar = principal.getClaims().get("avatar").toString();

        try (InputStream in = getClass().getResourceAsStream("/images/" + avatar.substring(0,3) + "/" + avatar)) {
            System.out.println("/images/" + avatar.substring(0,3) + "/" + avatar);
            return IOUtils.toByteArray(Objects.requireNonNull(in));
        } catch (IOException e) {
            throw new RuntimeException("/images/" + avatar.substring(0,3) + "/" + avatar);
        }
    }

    @PostMapping(
            value = "avatar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> setUserAvatar(
            @AuthenticationPrincipal Jwt principal,
            @RequestParam MultipartFile photo) {

        service.saveAvatar(principal.getSubject(), photo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
