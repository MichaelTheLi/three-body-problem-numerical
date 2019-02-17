package alive.Renderer;

import alive.Geometry.TwoDimensions.Point;

import static org.lwjgl.opengl.GL11.*;

public class PointRenderer implements Renderer {
    private Color color;
    private float size;

    public PointRenderer(Color color, float size) {
        this.color = color;
        this.size = size;
    }

    public void render(Point position)
    {
        glPointSize(size);

        glBegin(GL_POINTS);
        glColor4f(color.red, color.green, color.blue, color.alpha);
        glVertex3d(position.x, position.y, 0);
        glEnd();
    }
}
