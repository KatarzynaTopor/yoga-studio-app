package com.example.yoga_app.exception;

import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;

public class ProblemDetailExt extends ProblemDetail {

    public static ProblemDetail forStatusDetailAndErrors(final HttpStatusCode status, @Nullable final String detail, final Map<String, String> errors) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

}
