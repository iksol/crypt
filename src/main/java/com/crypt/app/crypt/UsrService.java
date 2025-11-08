package com.crypt.app.crypt;

import com.crypt.app.crypt.request.UserRequest;

public interface UsrService {
    public void registerUser(UserRequest userRequest);
    public void removeUser(UserRequest userRequest);
}
