package course.integration.entity;

import com.google.common.base.Objects;

import java.util.List;

/**
 * Created by yan on 2016/4/2.
 */
public class CourseMessage {

    private String from;

    private List<String> to;

    private String subject;

    private List<String> cc;

    private List<String> bcc;

    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        CourseMessage courseMessage = (CourseMessage)obj;
        return Objects.equal(from, this.from)
                && Objects.equal(to, this.to)
                && Objects.equal(subject, this.subject)
                && Objects.equal(cc, this.cc)
                && Objects.equal(bcc, this.bcc);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
