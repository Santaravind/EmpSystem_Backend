package emp.EMSystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;
    private Long empId;
    private String fileName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    public Document(Date uploadDate, String fileName, Long empId, Long docId) {
        this.uploadDate = uploadDate;
        this.fileName = fileName;
        this.empId = empId;
        this.docId = docId;
    }

    public Document() {
    }

    public Document(String fileName, Long empId, Date date) {
        this.fileName = fileName;
        this.empId = empId;
        this.uploadDate = date;

    }

    public Long getId() {
        return docId;
    }

    public void setId(Long docId) {
        this.docId = docId;
    }

    public Long getEmployeeID() {
        return empId;
    }

    public void setEmployeeID(Long employeeID) {
        this.empId = employeeID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
