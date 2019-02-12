package alive;

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

    private int worldWidth = 100;
    private int worldHeight = 100;



    public void run()
    {
        appWindow = new AppWindow("3 body problem", 300, 300);
        appWindow.init();

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        outputDiagnostics();
        loop();

        appWindow.destroy();
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

            glBegin (GL_POINTS);

            glVertex3f (10, 10, 0);
            glVertex3f (10, worldHeight - 10, 0);
            glVertex3f (worldWidth - 10, 10, 0);
            glVertex3f (worldWidth - 10, worldHeight - 10, 0);

            // Center dot
            glVertex3f (50, 50, 0);

            glEnd ();

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
