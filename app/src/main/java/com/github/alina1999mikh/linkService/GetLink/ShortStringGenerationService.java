package com.github.alina1999mikh.linkService.GetLink;

import com.github.alina1999mikh.model.Alphabet;
import com.github.alina1999mikh.model.Link;
import lombok.Setter;

import java.util.ArrayList;

public class ShortStringGenerationService {
    @Setter
    private char[] alphabet = Alphabet.getAlphabet();

    private static ArrayList<Integer> list;  //храним номера букв из ссылок-для увеличения
    private static int lengthShortUrl = 1;

    public ShortStringGenerationService() {
        list = initializeList();
    }

    protected Link createNewUrl() {
        doIncreaseUrl(1);
        char[] chars = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            chars[i] = alphabet[list.get(i)];
        }
        return new Link(new String(chars));
    }

    private ArrayList<Integer> initializeList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < lengthShortUrl; i++) list.add(-1);
        return list;
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
}