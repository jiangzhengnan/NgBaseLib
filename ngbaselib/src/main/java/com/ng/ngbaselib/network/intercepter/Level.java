package com.ng.ngbaselib.network.intercepter;

public enum Level {
    /**
     * No logs.
     */
    NONE,
    /**
     * <p>Example:
     * <pre>{@code
     *  - URL
     *  - Method
     *  - Headers
     *  - Body
     * }</pre>
     */
    BASIC,
    /**
     * <p>Example:
     * <pre>{@code
     *  - URL
     *  - Method
     *  - Headers
     * }</pre>
     */
    HEADERS,
    /**
     * <p>Example:
     * <pre>{@code
     *  - URL
     *  - Method
     *  - Body
     * }</pre>
     */
    BODY
}
