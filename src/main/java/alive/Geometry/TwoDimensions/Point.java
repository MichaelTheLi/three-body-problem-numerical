package alive.Geometry.TwoDimensions;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point second)
    {
        double dx2 = Math.pow(second.x - x, 2);
        double dy2 = Math.pow(second.y - y, 2);

        return Math.sqrt(dx2 + dy2);
    }

    public double length()
    {
        return Point.zero().distance(this);
    }

    public Point direction(Point second)
    {
        return second.subtract(this);
    }

    public Point normalized()
    {
        return multiply(1.0 / length());
    }

    public Point add(Point other)
    {
        return new Point(
            x + other.x,
            y + other.y
        );
    }

    public Point subtract(Point other)
    {
        return new Point(
            x - other.x,
            y - other.y
        );
    }

    public Point multiply(double scale)
    {
        return new Point(
            x * scale,
            y * scale
        );
    }

    public Point divide(double scale)
    {
        return new Point(
            x / scale,
            y / scale
        );
    }

    public static Point zero()
    {
        return new Point(0, 0);
    }
}
