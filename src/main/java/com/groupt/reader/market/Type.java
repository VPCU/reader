package com.groupt.reader.market;

import java.io.Serializable;

public class Type implements Serializable {
    String name;
    String url;
    String link;

    Type() {}

    Type(String name, String url, String link) {
        this.name = name;
        this.url = url;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
