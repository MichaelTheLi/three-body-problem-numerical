package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.LineRenderer;
import alive.Renderer.PointRenderer;
import alive.Renderer.Renderer;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Body {
    private Renderer renderer;

    public String name;
    public Point position;
    public ArrayList<Point> lastPoints;
    public Point velocity;
    public double mass;

    public Body(String name, Renderer renderer, Point position, Point velocity) {
        this.name = name;
        this.renderer = renderer;
        this.position = position;
        this.velocity = velocity;
        this.lastPoints = new ArrayList<>();
    }

    public void update(double timescale)
    {
        int maxLastPoints = 100;
        if (lastPoints.size() > maxLastPoints) {
            // TODO Should be better
//            lastPoints = (ArrayList<Point>)lastPoints.subList(1, lastPoints.size() - 1);
        }
        lastPoints.add(position);
        // So, vector math lib? Nah
        position = position.add(velocity.multiply(timescale));
    }

    public void applyForce(Point force)
    {
        Point acceleration = force.divide(getMass());
        addAcceleration(acceleration);
    }

    public void addAcceleration(Point acceleration)
    {
        velocity = velocity.add(acceleration);
    }

    public void influence2(Body body, double timescale, double G) {
        double bodyWeight = body.getMass();
        if (bodyWeight > 0) {
            Point diff = position.subtract(body.position);
            double part = (Math.pow(diff.x, 2) + Math.pow(diff.y, 2));
            double accelPart1 = G * getMass() / part;
            double accelPart2x = diff.x / Math.sqrt(part);
            double accelPart2y = diff.y / Math.sqrt(part);

            body.addAcceleration(new Point(
                    accelPart1 *accelPart2x * timescale,
                    accelPart1 *accelPart2y * timescale
            ));
        }
    }

    public void influence(Body body, double timescale, double G) {
        double bodyWeight = body.getMass();
        if (bodyWeight > 0) {
            double force = gravitationalForce(
                getMass(),
                body.getMass(),
                position.distance(body.position),
                G
            );

            Point forceVector = body.position.direction(position).normalized();
            body.applyForce(forceVector.multiply(force * timescale));
        }
    }

    protected double gravitationalForce(double mass1, double mass2, double distance, double G)
    {
        return (G * mass1) * (mass2 / Math.pow(distance, 2));
    }

    public void render()
    {
        // TODO Should it be here?
        renderer.render(position);
    }

    public void renderOrbit()
    {
        PointRenderer pointRenderer = (PointRenderer)renderer;
        LineRenderer orbitRenderer = new LineRenderer(
                pointRenderer.getColor().cloneWithAnotherAlpha(0.1f),
                pointRenderer.getSize()
        );
        orbitRenderer.setSize(2f);
        orbitRenderer.renderArray(lastPoints);
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
