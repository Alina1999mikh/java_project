package com.github.alina1999mikh.model;

import lombok.Data;

@Data
public class Link {
    private String link;

    public Link(String link) {
        this.link = link;
    }
}