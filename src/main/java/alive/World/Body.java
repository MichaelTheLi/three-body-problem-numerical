package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Renderer;

public class Body {
    private Renderer renderer;

    private Point position;

    public Body(Renderer renderer, Point position) {
        this.renderer = renderer;
        this.position = position;
    }

    public void update()
    {
        // TODO Update physics
    }

    public void render()
    {
        // TODO Should it be here?
        renderer.render();
    }
}
