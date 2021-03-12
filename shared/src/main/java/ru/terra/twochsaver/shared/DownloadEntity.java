package ru.terra.twochsaver.shared;

public class DownloadEntity {
    private String url, board, thread;

    public DownloadEntity() {
    }

    public DownloadEntity(String url, String board, String thread) {
        this.url = url;
        this.board = board;
        this.thread = thread;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }
}
