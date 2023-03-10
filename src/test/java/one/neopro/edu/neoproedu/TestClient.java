package one.neopro.edu.neoproedu;

import jakarta.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="Test_clients")
public class TestClient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long testId;

    @Column(name = "name")
    private String testName;

    public TestClient(String testName) {
        this.testName = testName;
    }

    public TestClient() {
    }

    public Long getTestId() {
        return testId;
    }


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
