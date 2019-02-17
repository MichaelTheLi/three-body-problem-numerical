package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Color;
import alive.Renderer.PointRenderer;

import java.util.Random;

public class WorldFiller {
    private final double screenCenterPx;
    private final double screenCenterPy;

    private World world;

    public WorldFiller(World world) {
        this.world = world;
        this.screenCenterPx = 0;
        this.screenCenterPy = 0;
    }

    public World example()
    {
        world.setG(1);
        world.setScale(400);
        world.setTimescale(0.005);

        Body mainBody = addGravBody (
                "First",
                0,0,
                4,
                new Color(1f, 0f, 0f)
        );
        mainBody.velocity = new Point(0.0, -1.0 / Math.sqrt(2));

        Body second = addGravBody (
                "Second",
                1,0,
                4,
                new Color(0f, 1f, 0f)
        );
        second.velocity = new Point(0, 1.0 / Math.sqrt(2));

        Body third = addGravBody (
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
        world.setG(1);
        world.setScale(400);
        world.setTimescale(0.0005);

        fillRandomBodies(5, 50);

        return world;
    }

    public World chaosExample()
    {
        world.setG(1);
        world.setScale(400);
        world.setTimescale(0.01);

        Body mainBody = addGravBody (
                "First",
                0,0,
                4,
                new Color(1f, 0f, 0f)
        );
        mainBody.velocity = new Point(0.0, -1.0 / Math.sqrt(2));

        Body second = addGravBody (
                "Second",
                1,0,
                4,
                new Color(0f, 1f, 0f)
        );
        second.velocity = new Point(0, 1.0 / Math.sqrt(2));

        Body third = addGravBody (
                "Third",
                1.0/2.0 - 0.02, Math.sqrt(3) / 2,
                0.0001,
                new Color(0f, 0f, 1f)
        );
        third.velocity = new Point(-1 * Math.sqrt(3.0/2.0), 0);

        return world;
    }

    public World fillSunEarthVenus()
    {
        double AU = 149.6e9;

        world.setScale(250 / AU);
        world.setTimescale(24 * 3600);

        Body mainBody = addGravBody (
                "Sun",
                screenCenterPx,
                screenCenterPy,
                1.98892E30,
                new Color(0.8f, 0.8f, 0.2f)
        );

        Body earth = addGravBody (
                "Earth",
                screenCenterPx  - 1 * AU,
                screenCenterPy,
                5.9742E24,
                new Color(0.2f, 0.3f, 0.9f)
        );
        earth.velocity = new Point(0, 29.783 * 1000);

        Body venus = addGravBody (
                "Venus",
                screenCenterPx  + 0.723 * AU,
                screenCenterPy,
                4.8685E24,
                new Color(0.8f, 0.8f, 0.7f)
        );
        venus.velocity = new Point(0, -35.02 * 1000);

        return world;
    }

    public World fillSunEarthMoon()
    {
        double AU = 149.6e9;

        world.setScale(450 / AU);
        world.setTimescale(24 * 360);

        Body mainBody = addGravBody (
                "Sun",
                screenCenterPx,
                screenCenterPy,
                1.98892E30,
                new Color(0.8f, 0.8f, 0.2f)
        );

        Body earth = addGravBody (
                "Earth",
                screenCenterPx  - 1 * AU,
                screenCenterPy,
                5.9721986E24f,
                new Color(0.2f, 0.3f, 0.9f)
        );
        earth.velocity = new Point(0, 29.783 * 1000);

        Body moon = addGravBody (
                "Moon",
                screenCenterPx  - 1 * AU,
                screenCenterPy + 384.4e6,
                7.34767309e22f,
                new Color(0.8f, 0.8f, 0.8f)
        );
        moon.velocity = new Point(1.022e3, 29.783 * 1000);

        return world;
    }

    public World fillEarthMoon()
    {
        world.setScale(200 / 3.85e8);
        world.setTimescale(15 * 3600);

        Body mainBody = addGravBody (
                "Earth",
                screenCenterPx,
                screenCenterPy,
                5.9721986E24f,
                new Color(0.2f, 0.3f, 0.9f)
        );
        mainBody.velocity = new Point(-13.0f, 0);

        Body moon = addGravBody (
                "Moon",
                screenCenterPx,
                screenCenterPy + 384.4e6,
                7.34767309e22f,
                new Color(0.8f, 0.8f, 0.8f)
        );
        moon.velocity = new Point(1.022e3, 0);

        return world;
    }

    public World fillEarthMoonScaledDown()
    {
        world.setScale(100 / 3.85e2);
        world.setTimescale((1.0/500.0) * 3600.0);

        Body mainBody = addGravBody (
                "Earth",
                screenCenterPx,
                screenCenterPy,
                5.9721986E14f,
                new Color(0.2f, 0.3f, 0.9f)
        );
        mainBody.velocity = new Point(0, 0);

        Body moon = addGravBody (
                "Moon",
                screenCenterPx,
                screenCenterPy + 384.4,
                7.34767309e12f,
                new Color(0.8f, 0.8f, 0.8f)
        );
        moon.velocity = new Point(10.22f, 0);

        return world;
    }

    public World fillEarthMoonEccentric()
    {
        world.setScale(100 / 3.85e8);
        world.setTimescale(20 * 3600);

        Body mainBody = addGravBody (
                "Earth",
                screenCenterPx,
                screenCenterPy,
                5.972E24f
        );
        mainBody.velocity = new Point(-17.8, 0);


        Body moon = addGravBody (
                "Moon",
                screenCenterPx,
                screenCenterPy + 405.4e6,
                7.3477e22f
        );
        moon.velocity = new Point(0.9646e3, 0);

        return world;
    }

    public World fillRandomBodies(int count, float maxMass)
    {
        Random random = new Random();
        for(int i = 0; i < count; i++) {
            addBody (
                "Random",
                    (world.getWidth() / 2 - random.nextFloat() * world.getWidth()) / world.getScale(),
                    (world.getHeight() / 2 - random.nextFloat() * world.getHeight()) / world.getScale(),
                random.nextFloat() * maxMass,
                new Color(0.7f, 0.7f, 0.7f)
            );
        }

        return world;
    }

    private Body addGravBody(String name, double x, double y, double mass) {
        return addGravBody(name, x, y, mass, new Color(0.8f, 0.15f, 0.15f));
    }

    private Body addGravBody(String name, double x, double y, double mass, Color color)
    {
        Point position = new Point(x, y);
        Body body = Body.withZeroVelocity(name, new PointRenderer(color, 10.0f), position);
        body.setMass(mass);
        world.addBody(body);

        return body;
    }

    private Body addBody(String name, double x, double y, double mass, Color color)
    {
        Point position = new Point(x, y);
        Body body = Body.withRandomVelocity(name, new PointRenderer(color, (float)(5.0f + 0.5f * mass)), position);
        body.setMass(mass);
        world.addBody(body);

        return body;
    }

}
