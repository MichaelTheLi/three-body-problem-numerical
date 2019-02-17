package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Renderer;

import java.util.Random;

public class Body {
    private static final double G = 6.67408e-11;
    private Renderer renderer;

    public String name;
    public Point position;
    public Point velocity;
    public double mass;

    public Body(String name, Renderer renderer, Point position, Point velocity) {
        this.name = name;
        this.renderer = renderer;
        this.position = position;
        this.velocity = velocity;
    }

    public void update(double timescale)
    {
        // So, vector math lib? Nah
        position = position.add(velocity.multiply(timescale));
    }

    public void applyForce(Point force)
    {
        Point acceleration = force.divide(getMass());
        velocity = velocity.add(acceleration);
    }

    public void influence(Body body, double timescale) {
        double bodyWeight = body.getMass();
        if (bodyWeight > 0) {
            double force = gravitationalForce(
                getMass(),
                body.getMass(),
                position.distance(body.position)
            );

            Point forceVector = body.position.direction(position).normalized();
            body.applyForce(forceVector.multiply(force * timescale));
        }
    }

    protected double gravitationalForce(double mass1, double mass2, double distance)
    {
        return (G * mass1) * (mass2 / Math.pow(distance, 2));
    }

    public void render(double scale)
    {
        // TODO Should it be here?
        Point scaled = position.multiply(scale);
        renderer.render(scaled);
    }

    public static Body withRandomVelocity(String name, Renderer renderer, Point position)
    {
        // Just for random random without singleton. Should be a better way
        long seed = renderer.hashCode();

        Random random = new Random(seed);
        Point velocity = new Point(
            0.5f - random.nextFloat(),
            0.5f - random.nextFloat()
        );
        return new Body(name, renderer, position, velocity);
    }

    public static Body withZeroVelocity(String name, Renderer renderer, Point position)
    {
        return new Body(name, renderer, position, Point.zero());
    }

    public void setMass(double weight) {
        this.mass = weight;
    }

    public double getMass() {
        return mass;
    }
}
