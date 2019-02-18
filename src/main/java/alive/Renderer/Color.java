package alive.Renderer;

public class Color {
    float red;
    float green;
    float blue;
    float alpha;

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1);
    }

    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color cloneWithAnotherAlpha(float alpha) {
        return new Color(
            red,
            green,
            blue,
            alpha
        );
    }

    public static Color gray() {
        return gray(0.5f);
    }

    public static Color gray(float value) {
        return gray(value, 1);
    }

    public static Color gray(float value, float alpha)
    {
        return new Color(value, value, value, alpha);
    }
}
