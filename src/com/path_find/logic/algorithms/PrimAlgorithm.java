package com.path_find.logic.algorithms;

import java.util.LinkedList;
import java.util.Random;

public class PrimAlgorithm {
    private int _height;
    private int _width;
    private boolean[][] map;

    public PrimAlgorithm(int height, int width) {
        _height = height;
        _width = width;
        map = new boolean[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                map[i][j] = true;
            }
        }
    }

    public void Execute() {
        final boolean WALL = true;
        final boolean PASSAGE = !WALL;
        final LinkedList<int[]> frontiers = new LinkedList<>();
        final Random random = new Random();
        int x = random.nextInt(_width);
        int y = random.nextInt(_height);
        frontiers.add(new int[]{x,y,x,y});

        while ( !frontiers.isEmpty() ){
            final int[] f = frontiers.remove( random.nextInt( frontiers.size() ) );
            x = f[2];
            y = f[3];
            if ( map[x][y] == WALL )
            {
                map[f[0]][f[1]] = map[x][y] = PASSAGE;
                if ( x >= 2 && map[x-2][y] == WALL )
                    frontiers.add( new int[]{x-1,y,x-2,y} );
                if ( y >= 2 && map[x][y-2] == WALL )
                    frontiers.add( new int[]{x,y-1,x,y-2} );
                if ( x < _width-2&& map[x+2][y] == WALL )
                    frontiers.add( new int[]{x+1,y,x+2,y} );
                if ( y < _height-2 && map[x][y+2] == WALL )
                    frontiers.add( new int[]{x,y+1,x,y+2} );
            }
        }
    }

    public boolean[][] GetMap() {
        return map;
    }
}
