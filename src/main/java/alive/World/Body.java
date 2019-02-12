package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Renderer;

import java.util.Random;

public class Body {
    private Renderer renderer;

    private Point position;
    private Point velocity;

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

        // TODO Update physics
        velocity.x *= 0.95;
        velocity.y *= 0.95;
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
        return new Body(renderer, position, velocity);
    }
}
