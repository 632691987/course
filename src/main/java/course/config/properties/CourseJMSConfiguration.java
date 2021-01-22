package course.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CourseJMSConfiguration.HORNETQ_CONFIG_PROPERTY_PREFIX)
public class CourseJMSConfiguration {
    final static String HORNETQ_CONFIG_PROPERTY_PREFIX = "hornetq.remoting";

    private String cluster;
    private String host;
    private String port;
    private String username;
    private String password;
    private long timeout;
    private boolean autostart;
    private String queueA;
    private String queueB;
    private String queueC;
    private String queueD;
    private String queueE;
    private String queueF;
    private String queueG;

    private String topicA;
    private String topicB;
    private String topicC;

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public void setAutostart(boolean autostart) {
        this.autostart = autostart;
    }

    public String getQueueA() {
        return queueA;
    }

    public void setQueueA(String queueA) {
        this.queueA = queueA;
    }

    public String getQueueB() {
        return queueB;
    }

    public void setQueueB(String queueB) {
        this.queueB = queueB;
    }

    public String getQueueC() {
        return queueC;
    }

    public void setQueueC(String queueC) {
        this.queueC = queueC;
    }

    public String getQueueD() {
        return queueD;
    }

    public void setQueueD(String queueD) {
        this.queueD = queueD;
    }

    public String getQueueE() {
        return queueE;
    }

    public void setQueueE(String queueE) {
        this.queueE = queueE;
    }

    public String getQueueF() {
        return queueF;
    }

    public void setQueueF(String queueF) {
        this.queueF = queueF;
    }

    public String getQueueG() {
        return queueG;
    }

    public void setQueueG(String queueG) {
        this.queueG = queueG;
    }

    public String getTopicA() {
        return topicA;
    }

    public void setTopicA(String topicA) {
        this.topicA = topicA;
    }

    public String getTopicB() {
        return topicB;
    }

    public void setTopicB(String topicB) {
        this.topicB = topicB;
    }

    public String getTopicC() {
        return topicC;
    }

    public void setTopicC(String topicC) {
        this.topicC = topicC;
    }
}