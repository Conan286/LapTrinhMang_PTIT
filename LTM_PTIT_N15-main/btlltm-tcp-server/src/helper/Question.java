/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author admin
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {
    public static String renQuestion() {
        List<String> listAnswer = new ArrayList<>();
        
        // Đội bóng Anh
        listAnswer.add("MANCHESTERUNITED1878");
        listAnswer.add("LIVERPOOL1892");
        listAnswer.add("CHELSEA1905");
        listAnswer.add("ARSENAL1886");
        listAnswer.add("MANCHESTERCITY1880");
        listAnswer.add("TOTTENHAMHOTSPUR1882");
        listAnswer.add("EVERTON1878");
        listAnswer.add("ASTONVILLA1874");
        listAnswer.add("LEICESTERCITY1884");
        listAnswer.add("WESTHAMUNITED1895");
        
        // Đội bóng Đức
        listAnswer.add("BAYERNMUNICH1900");
        listAnswer.add("BORUSSIADORTMUND1909");
        listAnswer.add("SCHALKE1904");
        listAnswer.add("RBLEIPZIG2009");
        listAnswer.add("STUTTGART1893");
        listAnswer.add("HAMBURG1887");
        listAnswer.add("WERDERBREMEN1899");
        listAnswer.add("HERTHABERLIN1892");
        
        // Đội bóng Pháp
        listAnswer.add("PARISSAINTGERMAIN1970");
        listAnswer.add("MARSEILLE1899");
        listAnswer.add("LYON1950");
        listAnswer.add("MONACO1924");
        listAnswer.add("LILLE1944");
        listAnswer.add("NANTES1943");
        
        // Đội bóng Tây Ban Nha
        listAnswer.add("REALMADRID1902");
        listAnswer.add("BARCELONA1899");
        listAnswer.add("ATLETICOMADRID1903");
        listAnswer.add("SEVILLA1890");
        listAnswer.add("VALENCIA1919");
        listAnswer.add("VILLARREAL1923");
        
        // Đội bóng Hà Lan
        listAnswer.add("AJAX1900");
        listAnswer.add("PSVEINDHOVEN1913");
        listAnswer.add("FEYENOORD1908");

        // Đội bóng Ý
        listAnswer.add("JUVENTUS1897");
        listAnswer.add("ACMILAN1899");
        listAnswer.add("INTERMILAN1908");
        listAnswer.add("ROMA1927");
        listAnswer.add("NAPOLI1926");
        listAnswer.add("LAZIO1900");
        listAnswer.add("FIORENTINA1926");

        // Tạo đối tượng Random để sinh ngẫu nhiên
        Random rand = new Random();

        // Sinh chỉ số ngẫu nhiên từ 0 đến kích thước của danh sách
        int randomIndex = rand.nextInt(listAnswer.size());

        // Trả về chuỗi ngẫu nhiên
        return listAnswer.get(randomIndex);
    }

    public static void main(String[] args) {
        // In chuỗi ngẫu nhiên ra màn hình
        System.out.println(renQuestion());
    }
}