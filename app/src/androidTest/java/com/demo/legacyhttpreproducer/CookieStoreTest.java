package com.demo.legacyhttpreproducer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static java.util.Arrays.asList;

import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

public class CookieStoreTest {
    @Test
    public void shouldGetAllCookies() {
        CookieStore cookieStore = CookieStore.getInstance();

        BasicClientCookie cookie1 = new BasicClientCookie("cookie-key1", "cookie-val1");
        BasicClientCookie cookie2 = new BasicClientCookie("cookie-key2", "cookie-val2");
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);

        assertThat(cookieStore.getCookies().size(), is(2));
        assertTrue(cookieStore.getCookies().containsAll(asList(cookie1, cookie2)));
    }

    @Test
    public void shouldStoreValueInCookieStore() {
        String key = "cookie-key";
        String value = "cookie-val";
        CookieStore cookieStore = CookieStore.getInstance();
        BasicClientCookie cookie = new BasicClientCookie(key, value);

        cookieStore.addCookie(cookie);

        assertThat(cookieStore.getCookieValue(key), is(value));
    }

    @Test
    public void shouldGetCookieUsingKey() {
        CookieStore cookieStore = CookieStore.getInstance();

        BasicClientCookie cookie1 = new BasicClientCookie("cookie-key1", "cookie-val1");
        BasicClientCookie cookie2 = new BasicClientCookie("cookie-key2", "cookie-val2");
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);

        assertThat(cookieStore.getCookieValue("cookie-key2"), is("cookie-val2"));
    }

    @Test
    public void shouldReturnNullWhenCookieIsNotPresent() {
        CookieStore cookieStore = CookieStore.getInstance();
        BasicClientCookie cookie1 = new BasicClientCookie("cookie-key1", "cookie-val1");
        BasicClientCookie cookie2 = new BasicClientCookie("cookie-key2", "cookie-val2");
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);

        assertNull(cookieStore.getCookieValue("cookie-key3"));
    }

    @Test
    public void shouldRemoveCookies() {
        CookieStore cookieStore = CookieStore.getInstance();

        BasicClientCookie cookie1 = new BasicClientCookie("cookie-key1", "cookie-val1");
        BasicClientCookie cookie2 = new BasicClientCookie("cookie-key2", "cookie-val2");
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);

        cookieStore.removeCookies();

        assertTrue(cookieStore.getCookies().isEmpty());
    }
}
