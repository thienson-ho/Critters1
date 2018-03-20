package assignment4;

import java.util.ArrayList;

public class CritterWorld extends Critter{
    private int width;
    private int height;
    private ArrayList<Critter> world;

    public CritterWorld() {
        width = Params.world_width;
        height = Params.world_height;

    }
}
