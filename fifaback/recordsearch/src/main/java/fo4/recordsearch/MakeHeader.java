package fo4.recordsearch;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class MakeHeader {

    private final String KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiMjAwMDA6MTAiLCJhY2NvdW50X2lkIjoiNTIwNTI1MzA2IiwiYXV0aF9pZCI6IjQiLCJleHAiOjE3NDI0NjU5NzUsImlhdCI6MTY3OTM5Mzk3NSwibmJmIjoxNjc5MzkzOTc1LCJzZXJ2aWNlX2lkIjoiNDMwMDExNDgxIiwidG9rZW5fdHlwZSI6IkFjY2Vzc1Rva2VuIn0.jCTecd78i5sm9vCsHaVai9Y3Cr8k6lT4joMo0tfCINE";

    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", KEY);
        return httpHeaders;
    }
}
