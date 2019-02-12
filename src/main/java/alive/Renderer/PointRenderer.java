package alive.Renderer;

import alive.Geometry.TwoDimensions.Point;

import static org.lwjgl.opengl.GL11.*;

public class PointRenderer implements Renderer {
    private Point geometryPoint;

    public PointRenderer(Point geometryPoint) {
        this.geometryPoint = geometryPoint;
    }

    public void render()
    {
        glBegin (GL_POINTS);
        glVertex3f (this.geometryPoint.x, this.geometryPoint.y, 0);
        glEnd ();
    }
}
