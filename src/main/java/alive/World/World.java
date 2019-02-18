package alive.World;

import alive.Geometry.TwoDimensions.Point;
import alive.Renderer.Color;
import alive.Renderer.PointRenderer;
import alive.Renderer.QuadRenderer;

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

                majorGravityHolder.gravitationInteraction(body, timescale, G);
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
        renderGravityOverlay();
        renderBodies();
    }

    protected void renderBodies()
    {
        bodies.forEach((Body body) -> {
            body.renderOrbit();
            body.render();
        });
    }

    public void renderGravityOverlay()
    {
        int gridSizeX = 200;
        int gridSizeY = 200;

        float gridStepX = width / gridSizeX;
        float gridStepY = height / gridSizeY;

        double gravityOverlayScale = 1.0;
        ArrayList<Point[]> list = new ArrayList<>();
        for(float i = -width/2; i < width/2; i+=gridStepX) {
            for (float j = -height/2; j < height/2; j+=gridStepY) {
                Point position = new Point(i, j);
                Point vector = calculateForceVectorInSomePoint(position);

                double newLength = vector.length();
                if (newLength > gravityOverlayScale) {
                    gravityOverlayScale = newLength;
                }
                list.add(new Point[]{
                    position,
                    vector,
                });
            }
        }

        QuadRenderer renderer = new QuadRenderer(new Color(1,1,1), new Point(gridStepX, gridStepY));

        double finalGravityOverlayScale = Math.log10(gravityOverlayScale);
        list.forEach((Point[] points) -> {
            double length = points[1].length();
            float scaledValue = 0.5f * (float)(Math.log10(length));
            renderer.setColor(Color.gray(scaledValue, 0.25f));
            renderer.render(points[0]);
        });
    }

    protected Point calculateForceVectorInSomePoint(Point point)
    {
        Body dummyBody = new Body("Dummy", null, point, Point.zero());
        dummyBody.setMass(1.0);
        final Point[] vector = {Point.zero()};
        bodies.forEach((Body body) -> {
            vector[0] = vector[0].add(
                body.gravitationalForceVector(dummyBody, timescale, G)
            );
        });

        return vector[0];
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
