package fo4.recordsearch;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class MakeHeader {

    private final String KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjgyMjMzNDQ5NiIsImF1dGhfaWQiOiIyIiwiZXhwIjoxNjk0OTMzMjUzLCJpYXQiOjE2NzkzODEyNTMsIm5iZiI6MTY3OTM4MTI1Mywic2VydmljZV9pZCI6IjQzMDAxMTQ4MSIsInRva2VuX3R5cGUiOiJBY2Nlc3NUb2tlbiJ9.d_tHmPiWXK0mtxIOUw8UU2swj_A7nn_Zxe2kzyawhI8";

    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", KEY);
        return httpHeaders;
    }
}
