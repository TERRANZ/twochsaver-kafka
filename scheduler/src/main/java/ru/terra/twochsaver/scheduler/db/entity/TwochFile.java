package ru.terra.twochsaver.scheduler.db.entity;

import ru.terra.twochsaver.shared.DownloadState;

import javax.persistence.*;

@Entity
public class TwochFile {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private String id;

    private String fileName;
    private String hash;
    @Lob
    private String displayName;
    @Lob
    private String fullName;
    private Long size;
    private DownloadState state;
    private Integer threadId;

    public TwochFile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public DownloadState getState() {
        return state;
    }

    public void setState(DownloadState state) {
        this.state = state;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }
}
