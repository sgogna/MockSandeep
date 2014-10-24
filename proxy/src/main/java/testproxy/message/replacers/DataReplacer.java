package testproxy.message.replacers;

/**
 * DataReplacer
 */
public interface DataReplacer {
    String replace(String sessionId, String originalResponse);
}
