package alive;

import alive.World.Factory.Example;
import alive.World.Factory.Real;
import alive.World.World;
import org.lwjgl.opengl.GL;

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

    public void run()
    {
        init();

        outputDiagnostics();
        loop();

        appWindow.destroy();
    }

    private void init()
    {
        appWindow = new AppWindow("3 body problem", 1000, 1000);
        appWindow.init();

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

//        Real factory = new Real();
//        world = factory.fillEarthMoonEccentric();

        Example factory = new Example();
        world = factory.chaosExample();
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
        glClearColor(0.1f, 0.1f, 0.1f, 0.0f);

        glMatrixMode(GL_PROJECTION_MATRIX);
        glLoadIdentity();
        glOrtho(
                -1 * world.getWidth() / 2, world.getWidth() / 2,
                world.getHeight() / 2, -1 * world.getHeight() / 2,
                1, -1
        );

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
