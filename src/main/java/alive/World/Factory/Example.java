package alive.World.Factory;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Color;
import alive.World.Body;
import alive.World.World;

public class Example extends AbstractFactory {
    public Example() {
        createExampleWorld();
    }

    protected void createExampleWorld()
    {
        world = new World(1000, 1000);
        world.setG(1);
        world.setScale(400);
        world.setTimescale(0.005);
    }

    public World example()
    {
        Body mainBody = addBody (
                "First",
                0,0,
                4,
                new Color(1f, 0f, 0f)
        );
        mainBody.velocity = new Point(0.0, -1.0 / Math.sqrt(2));

        Body second = addBody (
                "Second",
                1,0,
                4,
                new Color(0f, 1f, 0f)
        );
        second.velocity = new Point(0, 1.0 / Math.sqrt(2));

        Body third = addBody (
                "Third",
                1.0/2.0, Math.sqrt(3) / 2,
                0.0001,
                new Color(0f, 0f, 1f)
        );
        third.velocity = new Point(-1 * Math.sqrt(3.0/2.0), 0);

        return world;
    }

    public World exampleRandom()
    {
        world.setTimescale(0.0005);

        fillRandomBodies(5, 50);

        return world;
    }

    public World chaosExample()
    {
        world.setTimescale(0.01);

        Body mainBody = addBody (
            "First",
            0,0,
            4,
            new Color(1f, 0f, 0f)
        );
        mainBody.velocity = new Point(0.0, -1.0 / Math.sqrt(2));

        Body second = addBody (
                "Second",
                1,0,
                4,
                new Color(0f, 1f, 0f)
        );
        second.velocity = new Point(0, 1.0 / Math.sqrt(2));

        Body third = addBody (
                "Third",
                1.0/2.0 - 0.02, Math.sqrt(3) / 2,
                0.0001,
                new Color(0f, 0f, 1f)
        );
        third.velocity = new Point(-1 * Math.sqrt(3.0/2.0), 0);

        return world;
    }
}
