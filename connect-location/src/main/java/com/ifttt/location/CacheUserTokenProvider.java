package com.ifttt.location;

import androidx.annotation.Nullable;
import com.ifttt.connect.api.UserTokenProvider;

class CacheUserTokenProvider implements UserTokenProvider {

    private final Cache<String> cache;
    @Nullable private final UserTokenProvider delegate;

    CacheUserTokenProvider(Cache<String> cache, @Nullable UserTokenProvider delegate) {
        this.cache = cache;
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public String getUserToken() {
        String token;
        if (delegate != null) {
            token = delegate.getUserToken();
            if (token != null && token.length() > 0) {
                cache.write(token);
            }
        } else {
            token = cache.read();
        }

        return token;
    }
}
