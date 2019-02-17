package alive.Renderer;

import alive.Geometry.TwoDimensions.Point;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class LineRenderer implements Renderer {
    private Color color;
    private float size;

    public LineRenderer(Color color, float size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public void render(Point position) {
        throw new RuntimeException("Not used. Yeah, that's an ugly one.  SOLID, i'm sorry");
    }

    public void renderArray(List<Point> lines)
    {
        glPointSize(1.0f);

        glBegin(GL_LINE_STRIP);
        glColor4f(color.red / 3, color.green / 3, color.blue / 3, color.alpha);

        // TODO Should it be here?
        lines.forEach((Point point) -> {
            glVertex2d(point.x, point.y);
        });

        glEnd();
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
