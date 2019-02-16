package alive;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Color;
import alive.Renderer.PointRenderer;
import alive.World.Body;
import alive.World.World;
import org.lwjgl.opengl.GL;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

/**
 * Hello world!
 *
 */
public class App 
{
    private AppWindow appWindow;
    private World world;

    private int worldWidth = 1400;
    private int worldHeight = 1000;



    public void run()
    {
        init();

        outputDiagnostics();
        loop();

        appWindow.destroy();
    }

    private void init()
    {
        appWindow = new AppWindow("3 body problem", 1200, 1000);
        appWindow.init();

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        world = new World();

        float baseHugeMass = 1000 * 10;
        addGravBody (700, 500, baseHugeMass * 10);

        addGravBody (200, 400, baseHugeMass * 0.5f);
        addGravBody (300, worldHeight - 300, baseHugeMass * 0.5f);

        Random random = new Random();
        for(int i = 0; i < 1000; i++) {
            addBody (random.nextFloat() * 1400, random.nextFloat() * 1000, random.nextFloat() * 100);
        }
    }

    private void addGravBody(float x, float y, float weight)
    {
        Color color = new Color(1, 0, 0);
        Point position = new Point(x, y);
        Body body = Body.withZeroVelocity(new PointRenderer(position, color, 10.0f), position);
        body.setWeight(weight);
        world.addBody(body);
    }

    private void addBody(float x, float y, float weight)
    {
        Color color = new Color(1, 1, 1);
        Point position = new Point(x, y);
        Body body = Body.withRandomVelocity(new PointRenderer(position, color, 1.0f + 0.1f * weight), position);
        body.setWeight(weight);
        world.addBody(body);
    }

    public void outputDiagnostics()
    {
        System.err.println("GL_VENDOR: " + glGetString(GL_VENDOR));
        System.err.println("GL_RENDERER: " + glGetString(GL_RENDERER));
        System.err.println("GL_VERSION: " + glGetString(GL_VERSION));
    }

    public void loop()
    {
        // Set the clear color
        glClearColor(0.5f, 0.5f, 0.5f, 0.0f);

        glMatrixMode(GL_PROJECTION_MATRIX);
        glLoadIdentity();
        glOrtho(0, worldWidth, worldHeight, 0, 1, -1);

        glPointSize(2.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !appWindow.shouldClose() ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            world.update();
            world.render();

            appWindow.loopHandler();
        }
    }

    public static void main( String[] args )
    {
        System.out.println("You guessed it, started");

        new App().run();

        System.out.println("So long, and thanks for all the fish");
    }
}
