package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Renderer;

import java.util.Random;

public class Body {
    private Renderer renderer;

    public Point position;
    public Point velocity;
    public float weight = 0.0f;

    public Body(Renderer renderer, Point position, Point velocity) {
        this.renderer = renderer;
        this.position = position;
        this.velocity = velocity;
    }

    public void update()
    {
        // So, vector math lib?
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public void applyForce(Point force)
    {
        velocity.x += force.x;
        velocity.y += force.y;
    }

    public void influence(Body body) {
        float bodyWeight = body.getWeight();
        if (bodyWeight > 0) {
            double force = gravitationalForce(
                    getWeight(),
                    body.getWeight(),
                    (float) position.distance(body.position)
            );

            Point forceVector = body.position.direction(position).normalized();
            body.applyForce(forceVector.scaled((float)force));
        }
    }

    protected double gravitationalForce(float mass1, float mass2, float distance)
    {
        double G = 1.0e-07;
        return G * (mass1 * mass2) / Math.max(distance, 0.001);
    }

    public void render()
    {
        // TODO Should it be here?
        renderer.render();
    }

    public static Body withRandomVelocity(Renderer renderer, Point position)
    {
        // Just for random random without singleton. Should be a better way
        long seed = renderer.hashCode();

        Random random = new Random(seed);
        Point velocity = new Point(random.nextFloat(), random.nextFloat());
        return new Body(renderer, position, velocity.scaled(0.2f));
    }

    public static Body withZeroVelocity(Renderer renderer, Point position)
    {
        Point velocity = new Point(0,0);
        return new Body(renderer, position, velocity);
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
