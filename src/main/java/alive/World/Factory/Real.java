package alive.World.Factory;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Color;
import alive.World.Body;
import alive.World.World;

public class Real extends AbstractFactory {
    static final double AU = 149.6e9;

    static final double SUN_MASS = 1.98892E30;
    static final double EARTH_MASS = 5.9742E24;
    static final double MOON_MASS = 7.34767309e22;
    static final double VENUS_MASS = 4.8685E24;

    static final double EARTH_ORBIT_RADIUS = AU;
    static final double VENUS_ORBIT_RADIUS = 0.723 * AU;
    static final double MOON_ORBIT_RADIUS = 384.4e6;

    static final double EARTH_ORBIT_VELOCITY = 29.783e3;
    static final double VENUS_ORBIT_VELOCITY = 35.02e3;
    static final double MOON_ORBIT_VELOCITY = 1.022e3;

    public Real() {
        world = new World(1000, 1000);
    }

    protected void makeGeocentric()
    {
        world.setScale(200 / 3.85e8);
        world.setTimescale(15 * 3600);
    }

    protected void makeHeliocentric()
    {
        world.setScale(250 / AU);
        world.setTimescale(24 * 3600);

        addBody(
            "Sun",
            0, 0,
            SUN_MASS,
            new Color(0.8f, 0.8f, 0.2f)
        );
    }

    public World fillSunEarthVenus()
    {
        makeHeliocentric();

        Body earth = addBody (
                "Earth",
                - 1 * EARTH_ORBIT_RADIUS, 0,
                EARTH_MASS,
                new Color(0.2f, 0.3f, 0.9f)
        );
        earth.velocity = new Point(0, EARTH_ORBIT_VELOCITY);

        Body venus = addBody (
                "Venus",
                VENUS_ORBIT_RADIUS, 0,
                VENUS_MASS,
                new Color(0.8f, 0.8f, 0.7f)
        );
        venus.velocity = new Point(0, -1 * VENUS_ORBIT_VELOCITY);

        return world;
    }

    public World fillSunEarthMoon()
    {
        makeHeliocentric();

        Body earth = addBody (
                "Earth",
                - 1 * AU, 0,
                EARTH_MASS,
                new Color(0.2f, 0.3f, 0.9f)
        );
        earth.velocity = new Point(0, EARTH_ORBIT_VELOCITY);

        Body moon = addBody (
                "Moon",
                - 1 * AU, MOON_ORBIT_RADIUS,
                MOON_MASS,
                new Color(0.8f, 0.8f, 0.8f)
        );
        moon.velocity = new Point(MOON_ORBIT_VELOCITY, EARTH_ORBIT_VELOCITY);

        return world;
    }

    public World fillEarthMoon()
    {
        makeGeocentric();

        Body mainBody = addBody (
                "Earth",
                0, 0,
                EARTH_MASS,
                new Color(0.2f, 0.3f, 0.9f)
        );
        mainBody.velocity = new Point(-13.0f, 0);

        Body moon = addBody (
                "Moon",
                0, MOON_ORBIT_RADIUS,
                MOON_MASS,
                new Color(0.8f, 0.8f, 0.8f)
        );
        moon.velocity = new Point(MOON_ORBIT_VELOCITY, 0);

        return world;
    }

    public World fillEarthMoonEccentric()
    {
        makeGeocentric();

        Body mainBody = addBody (
                "Earth",
                0, 0,
                EARTH_MASS,
                new Color(0.2f, 0.3f, 0.9f)
        );
        mainBody.velocity = new Point(-13.0f, 0);

        Body moon = addBody (
                "Moon",
                0, MOON_ORBIT_RADIUS + 21.0e6,
                MOON_MASS,
                new Color(0.8f, 0.8f, 0.8f)
        );
        moon.velocity = new Point(MOON_ORBIT_VELOCITY - 57.4, 0);

        return world;
    }
}
