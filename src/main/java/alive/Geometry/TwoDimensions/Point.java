package alive.Geometry.TwoDimensions;

public class Point {
    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point second)
    {
        float dx = second.x - x;
        float dy = second.y - y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double length()
    {
        return Math.sqrt(x * x + y * y);
    }

    public Point direction(Point second)
    {
        float dx = second.x - x;
        float dy = second.y - y;

        return new Point(dx, dy);
    }

    public Point normalized()
    {
        return scaled((float)(1 / length()));
    }

    public Point scaled(float scale)
    {
        return new Point(
            x * scale,
            y * scale
        );
    }
}
