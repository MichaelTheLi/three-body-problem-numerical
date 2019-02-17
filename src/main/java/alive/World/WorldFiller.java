package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Color;
import alive.Renderer.PointRenderer;

public class WorldFiller {
    private final double screenCenterPx;
    private final double screenCenterPy;

    private World world;

    public WorldFiller(World world) {
        this.world = world;
        this.screenCenterPx = 0;
        this.screenCenterPy = 0;
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
                1.98892E30
        );

        Body earth = addGravBody (
                "Earth",
                screenCenterPx  - 1 * AU,
                screenCenterPy,
                5.9742E24
        );
        earth.velocity = new Point(0, 29.783 * 1000);

        Body venus = addGravBody (
                "Venus",
                screenCenterPx  + 0.723 * AU,
                screenCenterPy,
                4.8685E24
        );
        venus.velocity = new Point(0, -35.02 * 1000);

        return world;
    }

    public World fillSunEarthMoon()
    {
        double AU = 149.6e9;

        world.setScale(550 / AU);
        world.setTimescale(24 * 3600);

        Body mainBody = addGravBody (
                "Sun",
                screenCenterPx,
                screenCenterPy,
                1.98892E30
        );

        Body earth = addGravBody (
                "Earth",
                screenCenterPx  - 1 * AU,
                screenCenterPy,
                5.9742E24
        );
        earth.velocity = new Point(0, 29.783 * 1000);

        Body moon = addGravBody (
                "Moon",
                screenCenterPx  - 1 * AU,
                screenCenterPy + 384.4e6,
                7.3477e22f
        );
        moon.velocity = new Point(1.022e3, 29.783 * 1000);

        return world;
    }

    public World fillEarthMoon()
    {
        world.setScale(100 / 3.85e8);
        world.setTimescale(20 * 3600);

        Body mainBody = addGravBody (
                "Earth",
                screenCenterPx,
                screenCenterPy,
                5.972E24f
        );
        mainBody.velocity = new Point(-20.8, 0);


        Body moon = addGravBody (
                "Moon",
                screenCenterPx,
                screenCenterPy + 384.4e6,
                7.3477e22f
        );
        moon.velocity = new Point(1.022e3, 0);

        return world;
    }

    private Body addGravBody(String name, double x, double y, double mass)
    {
        Color color = new Color(0.8f, 0.15f, 0.15f);
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
