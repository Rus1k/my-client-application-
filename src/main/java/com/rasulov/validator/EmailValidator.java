package com.rasulov.validator;

import com.rasulov.exception.InvalidEmailException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String email) {
        boolean result;

        matcher = pattern.matcher(email);
        if (matcher.matches()) {
            result = true;
            log.info("successful validation");
            new InvalidEmailException();
        } else {
            result = false;
            log.info("not successful validation");
        }
        return result;
    }
}
