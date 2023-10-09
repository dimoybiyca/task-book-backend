package com.lemoncat.tbusersservice.service;

import com.lemoncat.tbusersservice.DTO.ChangeUsernameRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final Keycloak keycloak;
    private final WebpService webpService;

    public void updateUsername(String userId, ChangeUsernameRequest request) {
        UserResource userResource = this.getUserResource(userId);

        UserRepresentation user = userResource.toRepresentation();
        user.setUsername(request.username());
        userResource.update(user);

        this.logout(userId);
    }

    public void saveAvatar(String userId, MultipartFile file) {
        try {
            String filename = webpService.convertToWebp(file);

            UserResource userResource = this.getUserResource(userId);
            UserRepresentation user = userResource.toRepresentation();

            Map<String, List<String>> attributes = user.getAttributes();
            this.deleteOldAvatar(attributes);
            attributes.put("avatar", Collections.singletonList(filename));
            user.setAttributes(attributes);
            userResource.update(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(String userId) {
        this.getUserResource(userId).logout();
    }

    private void deleteOldAvatar(Map<String, List<String>> attributes) {
        if(attributes.containsKey("avatar")) {
            this.webpService.deleteImage(attributes.get("avatar").get(0));
        }
    }

    private UserResource getUserResource(String userId) {
        return keycloak.realm("tasksbook").users().get(userId);
    }
}
