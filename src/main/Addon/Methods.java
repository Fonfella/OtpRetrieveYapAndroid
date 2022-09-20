package main.Addon;

public class Methods {


    public String getCorretFormat(String string) {

        String lower =  string.toLowerCase();

        //21 Ago 2022 - 20 Set 2022 inputParameter_3 da verificare

        String [] array_valore1;

        array_valore1 = lower.split("^.{0,3}");

        String string1 = array_valore1[1].replaceAll("'", "20").trim();

        String [] arrayPerConversione = string1.split(" ");



        // capitalize first letter
        String Capitalize = arrayPerConversione[1].substring(0, 1).toUpperCase()+ arrayPerConversione[1].substring(1);

        String finalFirst = arrayPerConversione[0]+" "+Capitalize+" "+arrayPerConversione[2];
        return finalFirst;
    }
}


