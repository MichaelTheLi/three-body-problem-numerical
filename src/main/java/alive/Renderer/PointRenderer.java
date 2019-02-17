package alive.Renderer;

import alive.Geometry.TwoDimensions.Point;

import java.util.List;

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
        glVertex2d(position.x, position.y);
        glEnd();
    }

    @Override
    public void renderArray(List<Point> position) {
        throw new RuntimeException("Not used. Yeah, that's an ugly one. SOLID, i'm sorry");
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public float getSize() {
        return size;
    }
}
