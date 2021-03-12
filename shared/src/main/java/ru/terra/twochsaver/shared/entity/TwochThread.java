package ru.terra.twochsaver.shared.entity;

import ru.terra.twochsaver.shared.ThreadState;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TwochThread {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String board;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    private ThreadState threadState;
    private Integer updates;
    private Integer lastCount;

    public TwochThread() {
    }

    public TwochThread(Integer id, String board, ThreadState threadState) {
        this.id = id;
        this.board = board;
        this.threadState = threadState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public ThreadState getThreadState() {
        return threadState;
    }

    public void setThreadState(ThreadState threadState) {
        this.threadState = threadState;
    }

    public Integer getUpdates() {
        return updates;
    }

    public void setUpdates(Integer updates) {
        this.updates = updates;
    }

    public Integer getLastCount() {
        return lastCount;
    }

    public void setLastCount(Integer lastCount) {
        this.lastCount = lastCount;
    }
}
