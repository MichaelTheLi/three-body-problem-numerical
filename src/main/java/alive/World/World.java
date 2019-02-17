package alive.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    private final float width;
    private final float height;
    private double scale;
    private double timescale;
    List<Body> bodies;

    public World(float width, float height) {
        this.width = width;
        this.height = height;
        this.scale = 1;
        this.timescale = 1;
        this.bodies = new ArrayList<>();
    }

    public void addBody(Body body)
    {
        bodies.add(body);
    }

    public void update()
    {
        List<Body> gravityHolders = getGravityHolders();

        float minDistanceToSurvive = 1000.0f;

        List<Body> toIterate = new ArrayList<>(bodies);

        toIterate.forEach((Body body) -> {
            gravityHolders.forEach((Body majorGravityHolder) -> {
                if (majorGravityHolder == body) {
                    return;
                }
//                if (isMajorGravityInfluencer(body)) {
//                    return;
//                }

                if (Math.abs(body.position.distance(majorGravityHolder.position)) < minDistanceToSurvive
//                    || body.velocity.length() > 500f
                ) {
                    if (body.getMass() > majorGravityHolder.getMass()) {
                        bodies.remove(majorGravityHolder);
                    } else {
                        bodies.remove(body);
                    }
                }

                majorGravityHolder.influence(body, timescale);
            });
//            if (body.name == "Earth") {
//                return;
//            }
            body.update(timescale);
        });
    }

    public void render()
    {
        bodies.forEach((Body body) -> {
            body.render(scale);
        });
    }

    public List<Body> getBodies()
    {
        return bodies;
    }

    public List<Body> getGravityHolders()
    {
        return bodies
            .stream()
            .filter(this::isMajorGravityInfluencer)
            .collect(Collectors.toList());
    }

    protected boolean isMajorGravityInfluencer(Body body)
    {
        return body.getMass() > 0;
    }

    public void setTimescale(double timescale) {
        this.timescale = timescale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getScale() {
        return scale;
    }

    public double getTimescale() {
        return timescale;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
