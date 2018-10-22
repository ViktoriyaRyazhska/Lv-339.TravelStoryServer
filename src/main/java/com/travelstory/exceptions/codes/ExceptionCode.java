package com.travelstory.exceptions.codes;

/**
 * ExceptionCode contains all exception codes which are used in TravelStoryApp API
 */
public enum ExceptionCode {

    /*-
     * +----------------------------------------------------------------------
     * ||
     * || Code template: 999*
     * ||
     * || Purpose: Unexpected exceptions
     * ||
     * || Inherits From: None ||
     * ||
     * ++-----------------------------------------------------------------------
     */

    MISSING_SERVLET_REQUEST_PARAMETER(9991),

    UNSUPPORTED_MEDIA_TYPE(9992),

    JSON_IS_MALFORMED(1993),

    NO_EXCEPTION_HANDLER(1994),

    UNEXPECTED_EXCEPTION(9999),

    /*-
     * +----------------------------------------------------------------------
     * ||
     * || Code template: 1***
     * ||
     * || Purpose: Validation
     * ||
     * || Inherits From: None
     * ||
     * ++-----------------------------------------------------------------------
     */

    VALIDATION_FAILED(1000),

    /*-
     * Code template: 108*
     * Purpose: String validation Inherits
     * From: Validation
     */
    STRING_TOO_LONG(1801),

    STRING_TOO_SHORT(1802),

    INCORRECT_STRING(1803),

    EMPTY_STRING(1804),

    STRING_NOT_APPROPRIATE(1805),

    /*-
     * Code template: 105*
     * Purpose: Email validation Inherits
     * From: Validation
     */
    EMAIL_TOO_LONG(1051),

    INCORRECT_EMAIL(1052),

    UNSUPPORTED_EMAIL(1053),

    NO_EMAIL(1054),

    /*-
     * Code template: 107*
     * Purpose: Date/time validation Inherits
     * From: Validation //
     */
    INCORRECT_DATE_TIME(1071),

    PAST_DATE_TIME(1072),

    FUTURE_DATE_TIME(1073),

    NO_DATE_TIME(1074),

    /*-
     * Code template: 109*
     * Purpose: Message validation
     * Inherits From: Validation
     */
    INCORRECT_MESSAGE_TYPE(1090),

    INCORRECT_MESSAGE_CONTENT(1091),

    EMPTY_MESSAGE_CONTENT(1092),

    TOO_LONG_MESSAGE_CONTENT(1093),

    /*-
     * +----------------------------------------------------------------------
     * ||
     * || Code template: 7***
     * ||
     * || Purpose: Security
     * ||
     * || Inherits From: None
     * ||
     * ++-----------------------------------------------------------------------
     */

    /*-
     * +----------------------------------------------------------------------
     * ||
     * || Code template: 20**
     * ||
     * || Purpose: Resource not found
     * ||
     * || Inherits From: None
     * ||
     * ++-----------------------------------------------------------------------
     */

    USER_NOT_FOUND(2010),

    USER_PIC_NOT_FOUND(2011),

    COMMENT_NOT_FOUND(2020),

    MEDIA_NOT_FOUND(2030),

    CHAT_NOT_FOUND(2090),

    MESSAGE_NOT_FOUND(2060),

    TRAVELSTORY_NOT_FOUND(2050);

    /**
     * Exception Code
     */
    public final int exceptionCode;

    private ExceptionCode(int code) {
        exceptionCode = code;
    }

}
