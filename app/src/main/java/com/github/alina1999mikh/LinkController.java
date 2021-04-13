package com.github.alina1999mikh;

import com.github.alina1999mikh.linkService.GetFullLink;
import com.github.alina1999mikh.linkService.GetShortLink;
import com.github.alina1999mikh.linkService.LinksReprository.LinksMap;
import com.github.alina1999mikh.model.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.GenericArrayType;
import java.net.URI;

@RestController
public class LinkController {

    GetShortLink getShortLinkProcess=new GetShortLink();
    GetFullLink getFullLinkProcess=new GetFullLink();

  @RequestMapping(value = "/short", method = RequestMethod.GET)
    public ResponseEntity getFullLink(@RequestParam String q) {
        HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(getFullLinkProcess.get(new Link(q)).getLink()));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/full", method = RequestMethod.POST)
    public Link getShortLink(@RequestParam String Q) {
        return new Link("https://8080-white-parakeet-s5jnd202.ws-eu03.gitpod.io/short?q=" + getShortLinkProcess.get(new Link(Q)).getLink());
    }
}