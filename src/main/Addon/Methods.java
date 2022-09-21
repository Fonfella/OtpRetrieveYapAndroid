package main.Addon;

public class Methods {


    public String getCorretFormat(String string) {

        String lower =  string.toLowerCase();
        String [] array_valore1;
        array_valore1 = lower.split("^.{0,3}");
        String string1 = array_valore1[1].replaceAll("'", "20").trim();
        String [] arrayPerConversione = string1.split(" ");
        // capitalize first letter
        String Capitalize = arrayPerConversione[1].substring(0, 1).toUpperCase()+ arrayPerConversione[1].substring(1);
        String finalFirst = arrayPerConversione[0]+" "+Capitalize+" "+arrayPerConversione[2];
        return finalFirst;
    }

    public String createXpathGiornoDaSelezionare(String string) {
        String xpath = "//*[@text='sost']";
        String xpathFinale = xpath.replaceAll("sost", string);

        return xpathFinale;
    }
}


