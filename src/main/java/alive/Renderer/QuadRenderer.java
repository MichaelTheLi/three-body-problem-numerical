package alive.Renderer;

import alive.Geometry.TwoDimensions.Point;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class QuadRenderer implements Renderer {
    private Color color;
    private Point size;

    public QuadRenderer(Color color, Point size) {
        this.color = color;
        this.size = size;
    }

    public void render(Point position)
    {
        glBegin(GL_QUADS);
        glColor4f(color.red, color.green, color.blue, color.alpha);
        glVertex2d(position.x, position.y);
        glVertex2d(position.x + size.x, position.y);
        glVertex2d(position.x + size.x, position.y + size.y);
        glVertex2d(position.x, position.y + size.y);
        glEnd();
    }

    @Override
    public void renderArray(List<Point> position) {
        throw new RuntimeException("Not used. Yeah, that's an ugly one. SOLID, i'm sorry");
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
