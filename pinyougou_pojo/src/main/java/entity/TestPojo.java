package entity;

/**
 * pinyougou_parent
 * robin
 */
public class TestPojo {

    private Integer testId;
    private String testMessage;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestMessage() {
        return testMessage;
    }

    public void setTestMessage(String testMessage) {
        this.testMessage = testMessage;
    }

    @Override
    public String toString() {
        return "TestPojo{" +
                "testId=" + testId +
                ", testMessage='" + testMessage + '\'' +
                '}';
    }
}
