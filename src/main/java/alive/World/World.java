package alive.World;

import java.util.ArrayList;
import java.util.List;

public class World {
    List<Body> bodies;

    public World() {
        this.bodies = new ArrayList<Body>();
    }

    public void addBody(Body body)
    {
        bodies.add(body);
    }

    public void update()
    {
        bodies.forEach((Body body) -> {
            body.update();
        });
    }

    public void render()
    {
        bodies.forEach((Body body) -> {
            body.render();
        });
    }
}
