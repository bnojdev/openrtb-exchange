package org.example.publisher;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final String id;
    private final String name;
    private final List<Placement> placements;

    public Publisher(String id, String name) {
        this.id = id;
        this.name = name;
        this.placements = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Placement> getPlacements() {
        return placements;
    }

    public void addPlacement(Placement placement) {

        placement.setPublisher(this);

        placements.add(placement);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", placements=" + placements +
                '}';
    }
}
