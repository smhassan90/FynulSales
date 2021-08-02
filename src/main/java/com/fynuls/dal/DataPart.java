package com.fynuls.dal;

/**
 * @author Syed Muhammad Hassan
 * 5th November, 2019
 */
public class DataPart {
    private String fileName;
    private byte[] content;
    private String type;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
