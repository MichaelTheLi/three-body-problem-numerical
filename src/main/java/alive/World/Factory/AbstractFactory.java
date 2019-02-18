package alive.World.Factory;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Color;
import alive.Renderer.PointRenderer;
import alive.World.Body;
import alive.World.World;

import java.util.Random;

public class AbstractFactory {
    protected World world;

    public World fillRandomBodies(int count, float maxMass)
    {
        Random random = new Random();
        for(int i = 0; i < count; i++) {
            addBodyWithRandomVelocity(
                "Random",
                (world.getWidth() / 2 - random.nextFloat() * world.getWidth()) / world.getScale(),
                (world.getHeight() / 2 - random.nextFloat() * world.getHeight()) / world.getScale(),
                random.nextFloat() * maxMass,
                new Color(0.7f, 0.7f, 0.7f)
            );
        }

        return world;
    }

    protected Body addBody(String name, double x, double y, double mass) {
        return addBody(name, x, y, mass, new Color(0.8f, 0.15f, 0.15f));
    }

    protected Body addBodyWithRandomVelocity(String name, double x, double y, double mass, Color color) {
        return addBody(name, x, y, mass, color);
    }

    protected Body addBody(String name, double x, double y, double mass, Color color)
    {
        Point position = new Point(x, y);
        Body body = Body.withZeroVelocity(name, new PointRenderer(color, 10.0f), position);
        body.setMass(mass);
        world.addBody(body);

        return body;
    }
}
