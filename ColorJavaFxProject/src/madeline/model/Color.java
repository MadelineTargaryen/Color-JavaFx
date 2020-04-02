package madeline.model;

import java.util.regex.Pattern;

public class Color {

    private int red;
    private int green;
    private int blue;
    private String hexValue;


    /**
     * Constructeur RGB
     *
     * @param red la valeur de rouge
     * @param green la valeur de vert
     * @param blue la valeur de bleu
     */
    public Color(Integer red, Integer green, Integer blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        updateHexa();
    }

    /**
     * Constructeur hexadecimal
     * @param hexValue la valeur hexadecimale d'une couleur
     */
    public Color(String hexValue) {
        setHexValue(hexValue);
        updateRgb();
    }

    public int getRed(){
        return red;
    }

    public void setRed(Integer red) {
        if (!isCorrectRGBValue(red))
            throw new IllegalArgumentException("La valeur de red doit être comprise entre 0 et 255");
        this.red = red;
        updateHexa();
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        if (!isCorrectRGBValue(green))
            throw new IllegalArgumentException("La valeur de green doit être comprise entre 0 et 255");
        this.green = green;
        updateHexa();
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(Integer blue){
        if (!isCorrectRGBValue(blue))
            throw new IllegalArgumentException("La valeur de blue doit être comprise entre 0 et 255");
        this.blue = blue;
        updateHexa();
    }

    public String getHexValue(){ return hexValue; };

    /**
     * Méthode de type set permettant de modifier la couleur avec une nouvelle valeur hexa
     * @param hexValue nouvelle valeur hexa
     */
    public void setHexValue(String hexValue) {
        if (hexValue == null || !isCorrectHexValue(hexValue)) {
            throw new IllegalArgumentException("Hex color is incorrect");
        }
        this.hexValue = hexValue;
        updateRgb();
    }

    /**
     * Test si la valeur hexa décimal est correcte (eg. #21A4F3).
     *
     * @param hexValue à tester
     * @return true si l'expression régulière correspondant à une valeur hexa est respéctée par la valeur à tester.
     */
    private boolean isCorrectHexValue(String hexValue) {
        return Pattern.matches("^#([0-9A-F]{6})$", hexValue);
    }

    /**
     * Convertir l'hexa en RGB
     */
    private void updateRgb() {
        this.red = Integer.valueOf(hexValue.substring( 1, 3 ), 16 );
        this.green = Integer.valueOf(hexValue.substring( 3, 5 ), 16 );
        this.blue = Integer.valueOf(hexValue.substring( 5, 7 ), 16 );
    }

    /**
     * Convertir l'hexa en RGB
     * @return
     */
    private void updateHexa() {
        this.hexValue = String.format("#%02X%02X%02X", this.getRed(), this.getGreen(), this.getBlue());
    }


    /**
     * Test une valeur RGB si elle est comprise entre 0 et 255
     *
     * @param RGBvalue à tester
     * @return true si elle est entre 0 et 255 inclus, false sinon
     */
    private boolean isCorrectRGBValue(int RGBvalue) {
        return 0 <= RGBvalue && RGBvalue <= 255;
    }


    public String toString(){
        System.out.println("value=" + getHexValue() +
                ", r=" + getRed() +
                ", g=" + getGreen() +
                ", b=" + getBlue()
        );
        return "[value=" + getHexValue() +
                ", r=" + getRed() +
                ", g=" + getGreen() +
                ", b=" + getBlue() +
                "]";

    }
}
