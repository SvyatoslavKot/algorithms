package ru.kotovsvyatoslav.algorithms.util.parser;

public class StringToIntegerArray {

    public Integer[] stringToArray (String string) {
        String[] stringArray =  string.split(",");
        Integer[] intArray = new Integer[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            String s = stringArray[i];
            if (s.contains(" ")){
               String[] spaceString = s.split(" ");
               if (!spaceString[0].isEmpty()) {
                   intArray[i] = Integer.parseInt(spaceString[0]);
               }else {
                   intArray[i] = Integer.parseInt(spaceString[1]);
               }
            }else {
                intArray[i] = Integer.parseInt(stringArray[i]);
            }
        }
        return intArray;
    }

    public String arrayToString ( Integer[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : array) {
            stringBuilder.append(i);
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }
}
