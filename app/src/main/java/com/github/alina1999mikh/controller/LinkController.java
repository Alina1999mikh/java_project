package com.github.alina1999mikh.controller;

import com.github.alina1999mikh.model.Link;
import com.github.alina1999mikh.repository.LinksMapRepository;
import com.github.alina1999mikh.service.GetLink;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class LinkController {

    LinksMapRepository linksMap = new LinksMapRepository();
    GetLink getLinkProcess = new GetLink(linksMap);

    @RequestMapping(value = "/short", method = RequestMethod.GET)
    public ResponseEntity getFullLink(@RequestParam String q) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(getLinkProcess.getFullLink(new Link(q)).getLink()));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/full", method = RequestMethod.POST)
    public Link getShortLink(@RequestParam String Q) {
        return new Link("http://localhost:8080/short?q=" + getLinkProcess.getShortLink(new Link(Q)).getLink());
    }
}