package alive.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    List<Body> bodies;

    public World() {
        this.bodies = new ArrayList<>();
    }

    public void addBody(Body body)
    {
        bodies.add(body);
    }

    public void update()
    {
        List<Body> gravityHolders = getGravityHolders();

        float minDistanceToSurvive = 1.0f;

        List<Body> toIterate = new ArrayList<>(bodies);

        toIterate.forEach((Body body) -> {
            gravityHolders.forEach((Body majorGravityHolder) -> {
                if (majorGravityHolder == body) {
                    return;
                }
//                if (isMajorGravityInfluencer(body)) {
//                    return;
//                }

                if (body.position.distance(majorGravityHolder.position) < minDistanceToSurvive
                    || body.velocity.length() > 500f
                ) {
                    bodies.remove(body);
                }

                majorGravityHolder.influence(body);
            });

            body.update();

            // TODO Hack, doen't belongs here
            if (body.position.x > 1400 || body.position.x < 0) {
                bodies.remove(body);
//                body.velocity.x *= -1;
            }

            if (body.position.y > 1000 || body.position.y < 0) {
                bodies.remove(body);
//                body.velocity.y *= -1;
            }
        });
    }

    public void render()
    {
        bodies.forEach((Body body) -> {
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
        // No pun intended
        return body.getWeight() > 1000;
    }

}
