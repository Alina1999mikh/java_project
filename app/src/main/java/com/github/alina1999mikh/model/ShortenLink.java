package com.github.alina1999mikh.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShortenLink {
    @Setter
    private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
    @Getter
    private LinkMap map = new LinkMap();
    private ArrayList<Integer> list;
    private int lengthShortUrl = 1;

    public ShortenLink() {
        this.list = initializeList();
    }

    public Link connectToMap(Link fullUrl) { //возвращает shortUrl с которым добавили
        Link shortUrl = createShortUrl(fullUrl);
        map.put(shortUrl, fullUrl);
        return shortUrl;
    }

    private ArrayList<Integer> initializeList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < lengthShortUrl; i++) list.add(-1);
        return list;
    }

    private Link getShortUrl(Link value) {
        return map.getKey(value);
    }

    private void doIncreaseUrl(int index) {

        int thisIndex = list.size() - index;
        if (list.get(thisIndex) == alphabet.length - 1)//если индекс юрл равен последнему индексу (букве) алфавита и требует замены ранее стоящего
        {
            list.set(thisIndex, 0);
            if (thisIndex == 0 && list.get(thisIndex) == alphabet[alphabet.length - 1]) {  //проверка требует ли увеличения длины ссылк
                lengthShortUrl++;
                list.add(0);
                return;
            }
            doIncreaseUrl(index + 1);
        } else {
            list.set(thisIndex, list.get(thisIndex) + 1);
        }
    }

    private Link createNewUrl() {
        doIncreaseUrl(1);
        char[] chars = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            chars[i] = alphabet[list.get(i)];
        }
        return new Link(new String(chars));
    }

    private Link createShortUrl(Link fullUrl) {  //создаем урл короткий
        Link shortUrl = getShortUrl(fullUrl);      // проверяем есть ли такая ссылка уже, чтобы выдать готовый урл а не новый
        if (shortUrl == null) {
            return createNewUrl();
        } else return shortUrl;
    }
}