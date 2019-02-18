package alive.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    private float width;
    private float height;
    private double scale;
    private double timescale;
    List<Body> bodies;
    private double G;

    public World(float width, float height) {
        this.width = width;
        this.height = height;
        this.scale = 1;
        this.timescale = 1;
        this.G = 6.6740831e-11;
        this.bodies = new ArrayList<>();
    }

    public void addBody(Body body)
    {
        bodies.add(body);
    }

    public void update()
    {
        List<Body> gravityHolders = getGravityHolders();

        float minDistanceToSurvive = 0.01f;

        List<Body> toIterate = new ArrayList<>(bodies);

        toIterate.forEach((Body body) -> {
            gravityHolders.forEach((Body majorGravityHolder) -> {
                if (majorGravityHolder == body) {
                    return;
                }

//                if (Math.abs(body.position.distance(majorGravityHolder.position)) < (minDistanceToSurvive / scale)) {
//                    if (body.getMass() > majorGravityHolder.getMass()) {
//                        bodies.remove(majorGravityHolder);
//                    } else {
//                        bodies.remove(body);
//                    }
//                }

                majorGravityHolder.influence(body, timescale, G);
            });
        });


        toIterate.forEach((Body body) -> {
            body.update(timescale);

            if (body.position.x > width / 2 || body.position.x < -width / 2) {
                body.velocity.x *= -1;
                body.velocity.x *= 0.2;
                body.position.x *= 0.99;
            }
            if (body.position.y > height / 2 || body.position.y < -height / 2) {
                body.velocity.y *= -1;
                body.velocity.y *= 0.2;
                body.position.y *= 0.99;
            }
        });
    }

    public void render()
    {
        bodies.forEach((Body body) -> {
            body.renderOrbit();
            body.render();
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
        this.width /= scale;
        this.height /= scale;
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

    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }
}
