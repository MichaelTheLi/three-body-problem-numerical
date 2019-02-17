package alive.Renderer;

import alive.Geometry.TwoDimensions.Point;

import java.util.List;

public interface Renderer {
    public void render(Point position);
    public void renderArray(List<Point> position);
}
