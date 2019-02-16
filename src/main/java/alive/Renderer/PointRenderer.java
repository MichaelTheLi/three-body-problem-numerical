package alive.Renderer;

import alive.Geometry.TwoDimensions.Point;

import static org.lwjgl.opengl.GL11.*;

public class PointRenderer implements Renderer {
    private Point geometryPoint;
    private Color color;
    private float size;

    public PointRenderer(Point geometryPoint, Color color, float size) {
        this.geometryPoint = geometryPoint;
        this.color = color;
        this.size = size;
    }

    public void render()
    {
        glPointSize(size);

        glBegin(GL_POINTS);
        glColor4f(color.red, color.green, color.blue, color.alpha);
        glVertex3f(this.geometryPoint.x, this.geometryPoint.y, 0);
        glEnd();
    }
}
